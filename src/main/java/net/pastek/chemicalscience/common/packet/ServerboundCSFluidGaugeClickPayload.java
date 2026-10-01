package net.pastek.chemicalscience.common.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.pastek.chemicalscience.ChemicalScience;
import net.pastek.chemicalscience.common.inventory.container.ContainerChemicalBench;
import voltaic.api.fluid.PropertyFluidTank;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentFluidHandlerMulti;

public record ServerboundCSFluidGaugeClickPayload(BlockPos pos, int gaugeIndex, boolean isInput) implements CustomPacketPayload {

    public static final Type<ServerboundCSFluidGaugeClickPayload> TYPE = 
            new Type<>(ChemicalScience.rl("fluid_gauge_click"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundCSFluidGaugeClickPayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, ServerboundCSFluidGaugeClickPayload::pos,
            ByteBufCodecs.VAR_INT, ServerboundCSFluidGaugeClickPayload::gaugeIndex,
            ByteBufCodecs.BOOL, ServerboundCSFluidGaugeClickPayload::isInput,
            ServerboundCSFluidGaugeClickPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ServerboundCSFluidGaugeClickPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player && player.containerMenu instanceof ContainerChemicalBench container) {
                container.getSafeHost().ifPresent(tile -> {
                    if (!tile.getBlockPos().equals(payload.pos())) return;

                    tile.getComponent(IComponentType.FluidHandler).ifPresent(handler -> {
                        if (handler instanceof ComponentFluidHandlerMulti multi) {
                            PropertyFluidTank[] tanks = payload.isInput() ? multi.getInputTanks() : multi.getOutputTanks();
                            if (payload.gaugeIndex() >= 0 && payload.gaugeIndex() < tanks.length) {
                                PropertyFluidTank tank = tanks[payload.gaugeIndex()];
                                ItemStack carried = container.getCarried();
                                if (carried.isEmpty()) return;

                                IFluidHandlerItem itemHandler = carried.getCapability(Capabilities.FluidHandler.ITEM);
                                if (itemHandler == null) return;

                                FluidStack tankFluid = tank.getFluid().copy();
                                int filledIntoItem = itemHandler.fill(tankFluid, IFluidHandler.FluidAction.EXECUTE);
                                if (filledIntoItem > 0) {
                                    tank.drain(filledIntoItem, IFluidHandler.FluidAction.EXECUTE);
                                    container.setCarried(itemHandler.getContainer());
                                    tile.setChanged();
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                                            SoundEvents.BUCKET_FILL, SoundSource.PLAYERS, 1.0F, 1.0F);
                                    return;
                                }

                                for (int i = 0; i < itemHandler.getTanks(); i++) {
                                    FluidStack itemFluid = itemHandler.getFluidInTank(i);
                                    int filledIntoTank = tank.fill(itemFluid, IFluidHandler.FluidAction.EXECUTE);
                                    if (filledIntoTank > 0) {
                                        itemHandler.drain(filledIntoTank, IFluidHandler.FluidAction.EXECUTE);
                                        container.setCarried(itemHandler.getContainer());
                                        tile.setChanged();
                                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                                                SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.0F);
                                        return;
                                    }
                                }
                            }
                        }
                    });
                });
            }
        });
    }
}