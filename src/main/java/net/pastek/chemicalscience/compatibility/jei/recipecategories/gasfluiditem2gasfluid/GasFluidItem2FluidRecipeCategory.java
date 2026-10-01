package net.pastek.chemicalscience.compatibility.jei.recipecategories.gasfluiditem2gasfluid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.pastek.chemicalscience.common.recipe.categories.gasfluiditem2gasfluid.GasFluidItem2FluidRecipe;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.CountableIngredient;
import voltaic.common.recipe.recipeutils.FluidIngredient;
import voltaic.common.recipe.recipeutils.GasIngredient;
import voltaic.compatibility.jei.recipecategories.AbstractRecipeCategory;
import voltaic.compatibility.jei.utils.gui.types.BackgroundObject;

public abstract class GasFluidItem2FluidRecipeCategory<T extends GasFluidItem2FluidRecipe> extends AbstractRecipeCategory<T> {
    public GasFluidItem2FluidRecipeCategory(IGuiHelper guiHelper, Component title, ItemStack inputMachine, BackgroundObject bWrap, RecipeType<T> recipeType, int animTime) {
        super(guiHelper, title, inputMachine, bWrap, recipeType, animTime);
    }

    @Override
    public List<List<GasStack>> getGasInputs(GasFluidItem2FluidRecipe recipe) {
        List<List<GasStack>> ingredients = new ArrayList<>();

        for(GasIngredient ing : recipe.getGasIngredients()) {

            List<GasStack> gas = new ArrayList<>(ing.getMatchingGases());

            ingredients.add(gas);
        }

        return ingredients;
    }

    @Override
    public List<List<FluidStack>> getFluidInputs(GasFluidItem2FluidRecipe recipe) {
        List<List<FluidStack>> ingredients = new ArrayList<>();

        for(FluidIngredient ing : recipe.getFluidIngredients()) {
            List<FluidStack> fluids = new ArrayList<>();

            for(FluidStack stack : ing.getMatchingFluids()) {
                if (!BuiltInRegistries.FLUID.getKey(stack.getFluid()).toString().toLowerCase(Locale.ROOT).contains("flow")) {
                    fluids.add(stack);
                }
            }

            ingredients.add(fluids);
        }

        return ingredients;
    }

    @Override
    public List<List<ItemStack>> getItemInputs(GasFluidItem2FluidRecipe recipe) {
        List<List<ItemStack>> ingredients = new ArrayList<>();

        for(CountableIngredient ing : recipe.getCountedIngredients()) {
            ingredients.add(Arrays.asList(ing.getItemsArray()));
        }

        return ingredients;
    }

    @Override
    public List<FluidStack> getFluidOutputs(GasFluidItem2FluidRecipe recipe) {
        List<FluidStack> outputFluids = new ArrayList<>();
        outputFluids.add(recipe.getFluidRecipeOutput());
        if (recipe.hasFluidBiproducts()) {
            outputFluids.addAll(Arrays.asList(recipe.getFullFluidBiStacks()));
        }
        return outputFluids;
    }
}
