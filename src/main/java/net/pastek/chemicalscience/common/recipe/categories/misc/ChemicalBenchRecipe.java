package net.pastek.chemicalscience.common.recipe.categories.misc;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import net.pastek.chemicalscience.ChemicalScience;
import net.pastek.chemicalscience.common.recipe.CSCountableIngredient;
import net.pastek.chemicalscience.registers.CSRecipies;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.AbstractMaterialRecipe;
import voltaic.common.recipe.recipeutils.CountableIngredient;
import voltaic.common.recipe.recipeutils.FluidIngredient;
import voltaic.common.recipe.recipeutils.GasIngredient;
import voltaic.common.recipe.recipeutils.ProbableFluid;
import voltaic.common.recipe.recipeutils.ProbableGas;
import voltaic.common.recipe.recipeutils.ProbableItem;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentFluidHandlerMulti;
import voltaic.prefab.tile.components.type.ComponentGasHandlerMulti;
import voltaic.prefab.tile.components.type.ComponentInventory;
import voltaic.prefab.tile.components.type.ComponentProcessor;

public class ChemicalBenchRecipe extends AbstractMaterialRecipe {

    public static final String RECIPE_GROUP = "chemical_bench_recipe";

    private List<CSCountableIngredient> itemIngredients;
    private List<FluidIngredient> fluidIngredients;
    private List<GasIngredient> gasIngredients;
    private ItemStack itemOutput;
    private FluidStack fluidOutput;
    private GasStack gasOutput;

    private boolean validationChecked = false;
    private boolean isRecipeValid = true;

    public ChemicalBenchRecipe(String recipeGroup, List<CSCountableIngredient> inputItems,
	    List<FluidIngredient> inputFluids, List<GasIngredient> inputGases, ItemStack itemOutput,
	    FluidStack fluidOutput, GasStack gasOutput, double experience, int ticks, double usagePerTick,
	    List<ProbableItem> itemBiproducts, List<ProbableFluid> fluidBiproducts, List<ProbableGas> gasBiproducts) {
	super(recipeGroup, experience, ticks, usagePerTick, itemBiproducts, fluidBiproducts, gasBiproducts);

	this.itemIngredients = inputItems;
	this.fluidIngredients = inputFluids;
	this.gasIngredients = inputGases;
	this.itemOutput = itemOutput;
	this.fluidOutput = fluidOutput;
	this.gasOutput = gasOutput;
    }

    private boolean performLazyValidation() {
	boolean hasInput = !itemIngredients.isEmpty() || !fluidIngredients.isEmpty() || !gasIngredients.isEmpty();
	boolean hasOutput = !itemOutput.isEmpty() || !fluidOutput.isEmpty() || !gasOutput.isEmpty();

	if (!hasInput || !hasOutput) {
	    logError(!hasInput ? "No inputs defined" : "No outputs defined");
	    return false;
	}

	try {
	    for (FluidIngredient fluidIng : fluidIngredients) {
		if (fluidIng.getMatchingFluids().isEmpty() || fluidIng.getMatchingFluids().getFirst().isEmpty()) {
		    logError("Fluid input is invalid (Empty Tag or Unknown Fluid).");
		    return false;
		}
	    }

	    for (GasIngredient gasIng : gasIngredients) {
		if (gasIng.getMatchingGases().isEmpty() || gasIng.getMatchingGases().getFirst().isEmpty()) {
		    logError("Gas input is invalid (Empty Tag or Unknown Gas).");
		    return false;
		}
	    }
	} catch (Exception e) {
	    logError("Exception during validation: " + e.getMessage());
	    return false;
	}

	return true;
    }

    private void logError(String issue) {
	StringBuilder sb = new StringBuilder();
	sb.append("\n[Chemical Science] BROKEN RECIPE DETECTED");
	sb.append("\n  Group: ").append(this.getGroup());
	sb.append("\n  Error: ").append(issue);

	if (!itemIngredients.isEmpty()) {
	    try {
		ItemStack hintStack = itemIngredients.getFirst().ingredient().getItemsArray()[0];
		sb.append("\n  Recipe uses item [").append(hintStack.getItem()).append("]");
	    } catch (Exception ignored) {
		sb.append("\n  (Could not identify item input)");
	    }
	}
	sb.append("\nDisabling this recipe.");

	ChemicalScience.LOGGER.error(sb.toString());
    }

    @Override
    public boolean matchesRecipe(ComponentProcessor pr, int index) {
	if (!validationChecked) {
	    isRecipeValid = performLazyValidation();
	    validationChecked = true;
	}

	if (!isRecipeValid) {
	    return false;
	}

	int valid = 0b000;
	ComponentInventory inv = pr.getHolder().requireComponent(IComponentType.Inventory);

	List<ItemStack> availableInputs = new ArrayList<>(inv.getInputsForProcessor(index));

	List<CSCountableIngredient> catalystReqs = itemIngredients.stream().filter(CSCountableIngredient::isCatalyst)
		.toList();

	List<CountableIngredient> consumableReqs = itemIngredients.stream().filter(i -> !i.isCatalyst())
		.map(CSCountableIngredient::ingredient).toList();

        List<Integer> matchedSlotIndices = new ArrayList<>();

        for (CSCountableIngredient req : catalystReqs) {
            boolean matchFound = false;
            for (int i = 0; i < availableInputs.size(); i++) {
                ItemStack stackInSlot = availableInputs.get(i);

                if (!stackInSlot.isEmpty() && req.ingredient().test(stackInSlot)) {
                    matchedSlotIndices.add(i);
                    availableInputs.set(i, ItemStack.EMPTY);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                return false;
            }
        }

        if (!consumableReqs.isEmpty()) {
            Pair<List<Integer>, Boolean> itemPair = areItemsValid(consumableReqs, availableInputs);
            if (itemPair.getSecond()) {
                matchedSlotIndices.addAll(itemPair.getFirst());
                setItemArrangement(index, matchedSlotIndices);
                valid |= 1 << 2;
            } else {
                return false;
            }
        } else {
            setItemArrangement(index, matchedSlotIndices);
            valid |= 1 << 2;
        }

	if (hasFluidInputs()) {
	    Pair<List<Integer>, Boolean> fluidPair = areFluidsValid(getFluidIngredients(), pr.getHolder()
		    .<ComponentFluidHandlerMulti>requireComponent(IComponentType.FluidHandler).getInputTanks());
	    if (fluidPair.getSecond()) {
		setFluidArrangement(fluidPair.getFirst());
		valid |= 1 << 1;
	    } else {
		return false;
	    }
	}

	if (hasGasInputs()) {
	    Pair<List<Integer>, Boolean> gasPair = areGasesValid(getGasIngredients(), pr.getHolder()
		    .<ComponentGasHandlerMulti>requireComponent(IComponentType.GasHandler).getInputTanks());
	    if (gasPair.getSecond()) {
		setGasArrangement(gasPair.getFirst());
		valid |= 1 << 0;
	    } else {
		return false;
	    }
	}

	return valid > 0;
    }

    public boolean hasItemInputs() {
	return getCountedIngredients().size() > 0;
    }

    public boolean hasFluidInputs() {
	return getFluidIngredients().size() > 0;
    }

    public boolean hasGasInputs() {
	return getGasIngredients().size() > 0;
    }

    public List<CSCountableIngredient> getCSCountedIngredients() {
	return itemIngredients;
    }

    @Override
    public List<CountableIngredient> getCountedIngredients() {
	return itemIngredients.stream().map(CSCountableIngredient::ingredient).toList();
    }

    @Override
    public List<FluidIngredient> getFluidIngredients() {
	return fluidIngredients;
    }

    @Override
    public List<GasIngredient> getGasIngredients() {
	return gasIngredients;
    }

    public boolean hasItemOutput() {
	return !getItemRecipeOutput().isEmpty();
    }

    public boolean hasFluidOutput() {
	return !getFluidRecipeOutput().isEmpty();
    }

    public boolean hasGasOutput() {
	return !getGasRecipeOutput().isEmpty();
    }

    @Override
    public ItemStack getItemRecipeOutput() {
	return itemOutput;
    }

    @Override
    public FluidStack getFluidRecipeOutput() {
	return fluidOutput;
    }

    @Override
    public GasStack getGasRecipeOutput() {
	return gasOutput;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
	return CSRecipies.CHEMICAL_BENCH_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
	return CSRecipies.CHEMICAL_BENCH_TYPE.get();
    }

    public String CSgetGroup() {
	return this.getGroup();
    }

    @Override
    public boolean shouldConsumeItemIngredient(int ingredientIndex) {
	return !itemIngredients.get(ingredientIndex).isCatalyst();
    }
}