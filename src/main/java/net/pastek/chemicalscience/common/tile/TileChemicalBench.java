package net.pastek.chemicalscience.common.tile;

import java.util.List;
import java.util.function.BiConsumer;

import javax.annotation.Nullable;

import electrodynamics.registers.ElectrodynamicsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.pastek.chemicalscience.ChemicalScience;
import net.pastek.chemicalscience.common.block.subtype.SubtypeChemicalMachine;
import net.pastek.chemicalscience.common.inventory.container.ContainerChemicalBench;
import net.pastek.chemicalscience.common.recipe.categories.misc.ChemicalBenchRecipe;
import net.pastek.chemicalscience.registers.CSRecipies;
import net.pastek.chemicalscience.registers.CSTiles;
import voltaic.api.IWrenchItem;
import voltaic.api.electricity.ICapabilityElectrodynamic;
import voltaic.api.gas.GasStack;
import voltaic.api.gas.GasTank;
import voltaic.api.multiblock.assemblybased.Multiblock;
import voltaic.api.multiblock.assemblybased.MultiblockSlaveNode;
import voltaic.api.multiblock.assemblybased.TileMultiblockController;
import voltaic.api.multiblock.assemblybased.TileMultiblockSlave;
import voltaic.common.block.states.VoltaicBlockStates;
import voltaic.common.network.utils.FluidUtilities;
import voltaic.common.network.utils.GasUtilities;
import voltaic.prefab.properties.types.PropertyTypes;
import voltaic.prefab.properties.variant.SingleProperty;
import voltaic.prefab.sound.ITickableSound;
import voltaic.prefab.sound.SoundBarrierMethods;
import voltaic.prefab.tile.GenericTile;
import voltaic.prefab.tile.components.CapabilityInputType;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentContainerProvider;
import voltaic.prefab.tile.components.type.ComponentElectrodynamic;
import voltaic.prefab.tile.components.type.ComponentFluidHandlerMulti;
import voltaic.prefab.tile.components.type.ComponentGasHandlerMulti;
import voltaic.prefab.tile.components.type.ComponentInventory;
import voltaic.prefab.tile.components.type.ComponentProcessor;
import voltaic.prefab.tile.components.type.ComponentTickable;
import voltaic.prefab.utilities.BlockEntityUtils;
import voltaic.registers.VoltaicCapabilities;

public class TileChemicalBench extends TileMultiblockController implements ITickableSound {
    public static final ResourceLocation ID = ChemicalScience.rl("chemicalbench");
    public static final ResourceKey<Multiblock> RESOURCE_KEY = Multiblock.makeKey(ID);
    public static final int GAS_TANK_CAPACITY = 5000, FLUID_TANK_CAPACITY = 5000;
    public final SingleProperty<FluidStack> condensedFluidFromGas;

    private boolean isSoundPlaying = false;
    public final SingleProperty<Double> operatingTicks = property(new SingleProperty<>(getPropertyManager(), PropertyTypes.DOUBLE, "operatingticks", 0.0));
    public final SingleProperty<Boolean> isActive = property(new SingleProperty<>(getPropertyManager(), PropertyTypes.BOOLEAN, "isactive", false));

    public TileChemicalBench(BlockPos pos, BlockState state) {
        super(CSTiles.TILE_CHEMICAL_BENCH.get(), pos, state);

        getComponent(IComponentType.Tickable).ifPresent(component -> {
            ComponentTickable tickable = (ComponentTickable) component;
            tickable.tickServer(this::tickServer);
            tickable.tickClient(this::tickClient);
        });

        addComponent(new ComponentElectrodynamic(this, false, true)
                .setInputDirections(BlockEntityUtils.MachineDirection.BOTTOM)
                .voltage(VoltaicCapabilities.DEFAULT_VOLTAGE * 2));

        addComponent(new ComponentFluidHandlerMulti(this)
                .setInputDirections(BlockEntityUtils.MachineDirection.RIGHT)
                .setInputTanks(4, FLUID_TANK_CAPACITY, FLUID_TANK_CAPACITY, FLUID_TANK_CAPACITY, FLUID_TANK_CAPACITY)
                .setOutputTanks(4, FLUID_TANK_CAPACITY, FLUID_TANK_CAPACITY, FLUID_TANK_CAPACITY, FLUID_TANK_CAPACITY)
                .setRecipeType(CSRecipies.CHEMICAL_BENCH_TYPE.get()));

        addComponent(new ComponentGasHandlerMulti(this)
                .setInputDirections(BlockEntityUtils.MachineDirection.RIGHT)
                .setInputTanks(4, new int[]{GAS_TANK_CAPACITY, GAS_TANK_CAPACITY, GAS_TANK_CAPACITY, GAS_TANK_CAPACITY}, new int[]{1000, 1000, 1000, 1000}, new int[]{1024, 1024, 1024, 1024})
                .setOutputTanks(4, new int[]{GAS_TANK_CAPACITY, GAS_TANK_CAPACITY, GAS_TANK_CAPACITY, GAS_TANK_CAPACITY}, new int[]{1000, 1000, 1000, 1000}, new int[]{1024, 1024, 1024, 1024})
                .setCondensedHandler(getCondensedHandler()));

        addComponent(new ComponentInventory(this, ComponentInventory.InventoryBuilder.newInv()
                .processors(1, 6, 1, 5)
                .bucketInputs(4).bucketOutputs(4)
                .gasInputs(4).gasOutputs(4)
                .upgrades(3))
                .validUpgrades(ContainerChemicalBench.VALID_UPGRADES)
                .valid(machineValidator()));

        addComponent(new ComponentContainerProvider(SubtypeChemicalMachine.chemicalbench.tag(), this)
                .createMenu((id, player) -> new ContainerChemicalBench(id, player,
                        this.<ComponentInventory>getComponent(IComponentType.Inventory)
                                .orElseThrow(() -> new IllegalStateException("Missing Inventory Component")),
                        getCoordsArray())));

        addComponent(new ComponentProcessor(this)
                .canProcess(this::canProcessChemicalBench)
                .process(this::process));

        condensedFluidFromGas = this.property(new SingleProperty<>(getPropertyManager(), PropertyTypes.FLUID_STACK, "condensedfluidfromgas", FluidStack.EMPTY));
    }

    public boolean canProcessChemicalBench(ComponentProcessor pr, Level level, int procNumber) {
        boolean canProcess = canProcess(pr, level, procNumber);
        if (BlockEntityUtils.isLit(this) ^ canProcess) {
            BlockEntityUtils.updateLit(this, canProcess);
        }
        return canProcess;
    }

    private boolean canProcess(ComponentProcessor pr, Level level, int procNumber) {
        if (!this.isFormed.getValue()) {
            return false;
        }

        ChemicalBenchRecipe recipe = pr.prepareRecipe(procNumber, CSRecipies.CHEMICAL_BENCH_TYPE.get(), ChemicalBenchRecipe.class);
        if (recipe == null) {
            return false;
        }

        return pr.canProcessMaterialRecipe(recipe, procNumber, 1, 1);
    }

    private void process(ComponentProcessor pr, Level level, int procNumber) {
        pr.processMaterialRecipe(procNumber, ChemicalBenchRecipe.class, 1, 1);
    }

    public void tickServer(Level level, ComponentTickable tickable) {
        getComponent(IComponentType.FluidHandler).ifPresent(fluidHandler -> {
            if (fluidHandler instanceof ComponentFluidHandlerMulti multi) {
                FluidUtilities.drainItem(this, multi.getInputTanks());
                FluidUtilities.fillItem(this, multi.getOutputTanks());
            }
        });

        getComponent(IComponentType.GasHandler).ifPresent(gasHandler -> {
            if (gasHandler instanceof ComponentGasHandlerMulti multi) {
                GasUtilities.drainItem(this, multi.getInputTanks());
                GasUtilities.fillItem(this, multi.getOutputTanks());
            }
        });
    }

    public void tickClient(Level level, ComponentTickable tickable) {
        if (this.shouldPlaySound()) {
            if (!this.isSoundPlaying) {
                this.isSoundPlaying = true;
                SoundBarrierMethods.playTileSound(ElectrodynamicsSounds.SOUND_HUM.get(), this, true);
            }
        }
    }

    public BiConsumer<GasTank, GenericTile> getCondensedHandler() {
        return (tank, tile) -> {
            GasStack tankGas = tank.getGas().copy();
            tank.setGas(GasStack.EMPTY);
            if (!tankGas.isEmpty()) {
                Fluid condensedFluid = tankGas.getGas().getCondensedFluid();
                if (!condensedFluid.isSame(Fluids.EMPTY)) {
                    tankGas.bringPressureTo(1);
                    FluidStack currentCondensate = this.condensedFluidFromGas.getValue();
                    if (currentCondensate.getFluid().isSame(condensedFluid)) {
                        int room = Math.max(0, 10000 - currentCondensate.getAmount());
                        int taken = Math.min(room, tankGas.getAmount());
                        currentCondensate.setAmount(currentCondensate.getAmount() + taken);
                        this.condensedFluidFromGas.setValue(currentCondensate);
                    } else {
                        FluidStack newFluid = new FluidStack(condensedFluid, Math.min(tankGas.getAmount(), 10000));
                        this.condensedFluidFromGas.setValue(newFluid);
                    }
                }
            }
        };
    }

    @Override
    public void setNotPlaying() {
        this.isSoundPlaying = false;
    }

    @Override
    public boolean shouldPlaySound() {
        return getComponent(IComponentType.Processor)
                .map(p -> ((ComponentProcessor) p).isActive(0))
                .orElse(false);
    }

    @Override public @Nullable IFluidHandler getFluidHandlerCapability(@Nullable Direction side){ return null; }
    @Override public @Nullable ICapabilityElectrodynamic getElectrodynamicCapability(@Nullable Direction side){ return null; }

    @Nullable
    @Override
    public ICapabilityElectrodynamic getSlaveCapabilityElectrodynamic(TileMultiblockSlave slave, @Nullable Direction side) {
        if (slave.index.getValue() != 1) {
            return null;
        }
        return getComponent(IComponentType.Electrodynamic)
                .map(electro -> ((ComponentElectrodynamic) electro).getCapability(side, CapabilityInputType.NONE))
                .orElse(null);
    }

    @Override public @Nullable IItemHandler getItemHandlerCapability(@Nullable Direction side){ return null; }

    @Override
    public ItemInteractionResult useWithItem(Level level, ItemStack used, Player player, InteractionHand hand, BlockHitResult hit) {
        if(!level.isClientSide() && hit.getBlockPos().equals(getBlockPos()) && used.getItem() instanceof IWrenchItem){
            checkFormed();
            if(isFormed.getValue()) formMultiblock(level); else destroyMultiblock(level);
            return ItemInteractionResult.CONSUME;
        }
        return super.useWithItem(level, used, player, hand, hit);
    }

    @Override
    public InteractionResult useWithoutItem(Level level, Player player, BlockHitResult hit){
        return isFormed.getValue() ? super.useWithoutItem(level, player, hit) : InteractionResult.FAIL;
    }

    @Override
    public void formMultiblock(Level level) {
        Direction facing = this.getFacing().getOpposite();
        List<MultiblockSlaveNode> nodes = Multiblock.getNodes(level, this.getResourceKey(), facing);
        int index = 0;

        for (MultiblockSlaveNode node : nodes) {
            BlockPos nodePos = this.getBlockPos().offset(node.offset());
            this.slavePositions.addValue(nodePos, index);

            BlockState placedState = node.placeState().setValue(VoltaicBlockStates.FACING, this.getFacing());
            level.setBlockAndUpdate(nodePos, placedState);

            TileMultiblockSlave slave = (TileMultiblockSlave) level.getBlockEntity(nodePos);
            if (slave != null) {
                BlockState disguise = node.replaceState();
                slave.setDisguise(disguise);

                slave.controller.setValue(this.getBlockPos());
                slave.index.setValue(index);
                slave.renderModel.setValue(node.model());
                this.slaveList.add(slave);
            } else {
                ChemicalScience.LOGGER.warn("Failed to get TileMultiblockSlave at {}", nodePos);
            }

            ++index;
        }

        level.playSound(null, this.getBlockPos(), SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Override public ResourceLocation getMultiblockId(){ return ID; }
    @Override public ResourceKey<Multiblock> getResourceKey(){ return RESOURCE_KEY; }
}