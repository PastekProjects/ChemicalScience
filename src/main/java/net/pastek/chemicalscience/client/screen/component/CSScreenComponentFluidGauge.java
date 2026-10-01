package net.pastek.chemicalscience.client.screen.component;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.network.PacketDistributor;
import net.pastek.chemicalscience.common.packet.ServerboundCSFluidGaugeClickPayload;
import voltaic.api.screen.component.FluidTankSupplier;
import voltaic.prefab.inventory.container.types.GenericContainerBlockEntity;
import voltaic.prefab.screen.GenericScreen;
import voltaic.prefab.screen.component.types.gauges.ScreenComponentFluidGauge;
import voltaic.prefab.tile.GenericTile;

import java.util.function.IntSupplier;

@OnlyIn(Dist.CLIENT)
public class CSScreenComponentFluidGauge extends ScreenComponentFluidGauge {

    private final IntSupplier gaugeIndexSupplier;
    private final boolean isInput;

    public CSScreenComponentFluidGauge(FluidTankSupplier fluidInfoHandler, int x, int y, IntSupplier gaugeIndexSupplier, boolean isInput) {
        super(fluidInfoHandler, x, y);
        this.gaugeIndexSupplier = gaugeIndexSupplier;
        this.isInput = isInput;
    }

    @Override
    public void onMouseClick(double mouseX, double mouseY) {
        GenericScreen<?> screen = (GenericScreen<?>) requireScreen();
        GenericContainerBlockEntity<?> menu = (GenericContainerBlockEntity<?>) screen.getMenu();
        GenericTile owner = (GenericTile) menu.getSafeHost().orElse(null);
        if (owner == null) return;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        ItemStack stack = menu.getCarried();
        if (stack.isEmpty()) return;

        if (stack.getCapability(Capabilities.FluidHandler.ITEM) == null) return;

        PacketDistributor.sendToServer(new ServerboundCSFluidGaugeClickPayload(
                owner.getBlockPos(),
                gaugeIndexSupplier.getAsInt(),
                isInput
        ));
    }
}