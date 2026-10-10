package net.pastek.chemicalscience.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.pastek.chemicalscience.ChemicalScience;
import net.pastek.chemicalscience.common.inventory.container.ContainerChemicalBench;
import net.pastek.chemicalscience.prefab.screen.component.ScreenComponentFluidInfo;
import net.pastek.chemicalscience.prefab.screen.component.ScreenComponentGasInfo;
import voltaic.prefab.screen.component.types.ScreenComponentProgress;
import voltaic.prefab.screen.component.types.ScreenComponentProgress.ProgressBars;
import voltaic.prefab.screen.component.types.ScreenComponentSimpleLabel;
import voltaic.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import voltaic.prefab.screen.component.types.wrapper.WrapperCyclableFluidGauge;
import voltaic.prefab.screen.component.types.wrapper.WrapperCyclableGasGauge;
import voltaic.prefab.screen.component.types.wrapper.WrapperInventoryIO;
import voltaic.prefab.screen.component.utils.AbstractScreenComponentInfo;
import voltaic.prefab.screen.types.GenericMaterialScreen;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentFluidHandlerMulti;
import voltaic.prefab.tile.components.type.ComponentGasHandlerMulti;
import voltaic.prefab.tile.components.type.ComponentProcessor;
import voltaic.prefab.utilities.math.Color;

@OnlyIn(Dist.CLIENT)
public class ScreenChemicalBench extends GenericMaterialScreen<ContainerChemicalBench> {

    public ScreenChemicalBench(ContainerChemicalBench container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);

        this.defaultResource = ChemicalScience.rl("textures/screen/gui/chemicalbench_gui.png");

        imageHeight += 70;
        inventoryLabelY += 70;

        this.addComponent(new ScreenComponentProgress(ProgressBars.PROGRESS_ARROW_RIGHT_BIG, () -> container.getSafeHost()
                .flatMap(tile -> tile.<ComponentProcessor>getComponent(IComponentType.Processor)).filter(processor -> processor.isActive(0) && processor.requiredTicks.getValue()[0] > 0).map(processor -> (float) (processor.operatingTicks.getValue()[0] / processor.requiredTicks.getValue()[0])).orElse(0.0F), 56, 49));

        WrapperCyclableFluidGauge fluidOutput = new WrapperCyclableFluidGauge(135, 24, container, this, false);
        WrapperCyclableFluidGauge fluidInput = new WrapperCyclableFluidGauge(9, 24, container, this, true);
        WrapperCyclableGasGauge gasOutput = new WrapperCyclableGasGauge(153, 24, container, this, false);
        WrapperCyclableGasGauge gasInput = new WrapperCyclableGasGauge(27, 24, container, this, true);

        new WrapperInventoryIO(this, -AbstractScreenComponentInfo.SIZE + 1, AbstractScreenComponentInfo.SIZE + 2, 75, 152, 8, 142)
                .hideAdditional(show -> {
                    fluidInput.getComponents().forEach(component -> {
                        component.setActive(show);
                        component.setVisible(show);
                    });
                    fluidOutput.getComponents().forEach(component -> {
                        component.setActive(show);
                        component.setVisible(show);
                    });
                    gasInput.getComponents().forEach(component -> {
                        component.setActive(show);
                        component.setVisible(show);
                    });
                    gasOutput.getComponents().forEach(component -> {
                        component.setActive(show);
                        component.setVisible(show);
                    });
                });

        this.addComponent(new ScreenComponentSimpleLabel(-36, 66, 10, new Color(175, 186, 196, 255), Component.translatable("container.chemicalbench.inputfluids")));

        for (int i = 0; i < 4; i++) {
            final int index = i;
            this.addComponent(new ScreenComponentFluidInfo(() -> container.getSafeHost()
                    .flatMap(tile -> tile.<ComponentFluidHandlerMulti>getComponent(IComponentType.FluidHandler))
                    .map(handler -> handler.getInputTanks().length > index ? handler.getInputTanks()[index] : null)
                    .orElse(null), -39, 76 + i * 10));

            this.addComponent(new ScreenComponentGasInfo(() -> container.getSafeHost()
                    .flatMap(tile -> tile.<ComponentGasHandlerMulti>getComponent(IComponentType.GasHandler))
                    .map(handler -> handler.getInputTanks().length > index ? handler.getInputTanks()[index] : null)
                    .orElse(null), -20, 76 + i * 10));
        }

        this.addComponent(new ScreenComponentSimpleLabel(-36, 118, 10, new Color(175, 186, 196, 255), Component.translatable("container.chemicalbench.outputfluids")));

        for (int i = 0; i < 4; i++) {
            final int index = i;
            this.addComponent(new ScreenComponentFluidInfo(() -> container.getSafeHost()
                    .flatMap(tile -> tile.<ComponentFluidHandlerMulti>getComponent(IComponentType.FluidHandler))
                    .map(handler -> handler.getOutputTanks().length > index ? handler.getOutputTanks()[index] : null)
                    .orElse(null), -39, 128 + i * 10));

            this.addComponent(new ScreenComponentGasInfo(() -> container.getSafeHost()
                    .flatMap(tile -> tile.<ComponentGasHandlerMulti>getComponent(IComponentType.GasHandler))
                    .map(handler -> handler.getOutputTanks().length > index ? handler.getOutputTanks()[index] : null)
                    .orElse(null), -20, 128 + i * 10));
        }

        this.addComponent(new ScreenComponentElectricInfo(-AbstractScreenComponentInfo.SIZE + 1, 2));
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int left = this.leftPos;
        int top = this.topPos;

        graphics.blit(
                this.defaultResource,
                left,
                top,
                0,
                0,
                197,
                236
        );
        graphics.blit(
                ChemicalScience.rl("textures/screen/gui/chemicalbench_window_gui.png"),
                left - 41,
                top + 64,
                0,
                0,
                40,
                107
        );
    }
}