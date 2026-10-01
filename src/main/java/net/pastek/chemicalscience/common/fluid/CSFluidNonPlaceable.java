package net.pastek.chemicalscience.common.fluid;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import voltaic.common.fluid.FluidNonPlaceable;

public class CSFluidNonPlaceable extends FluidNonPlaceable {

    public CSFluidNonPlaceable(Holder<Item> bucket, FluidType type) {
        super(bucket, type);
    }

    @Override
    public boolean isSource(@NotNull FluidState state) {
        return true;
    }

    @Override
    public int getAmount(@NotNull FluidState state) {
        return 8;
    }
}