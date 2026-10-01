package net.pastek.chemicalscience.client.screen.component;

import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import voltaic.prefab.inventory.container.types.GenericContainerBlockEntity;
import voltaic.prefab.screen.GenericScreen;
import voltaic.prefab.screen.component.button.type.ButtonTankSlider;
import voltaic.prefab.screen.component.types.ScreenComponentSimpleLabel;
import voltaic.prefab.screen.component.utils.AbstractScreenComponent;
import voltaic.prefab.tile.GenericTile;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.utils.IComponentFluidHandler;
import voltaic.prefab.utilities.math.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class CSWrapperCyclableFluidGauge {

    private int gauge = 0;
    private final List<AbstractScreenComponent> components = new ArrayList<>();

    public CSWrapperCyclableFluidGauge(int x, int y, GenericContainerBlockEntity<? extends GenericTile> container,
                                      GenericScreen<?> screen, boolean inputTanks) {
        Supplier<Optional<IComponentFluidHandler>> handlerSupplier = () -> container.getSafeHost()
                .flatMap(tile -> tile.<IComponentFluidHandler>getComponent(IComponentType.FluidHandler));

        IntConsumer cycleGauge = change -> handlerSupplier.get().ifPresent(handler -> {
            int size = inputTanks ? handler.getInputTanks().length : handler.getOutputTanks().length;
            gauge = size == 0 ? 0 : Math.floorMod(gauge + change, size);
        });

        int yOffset = 0;
        components.add(screen.addComponent(new ScreenComponentSimpleLabel(x + 4, y + yOffset, 7, Color.WHITE,
                () -> Component.literal(Integer.toString(gauge + 1)))));
        yOffset += 8;

        // Uses CSScreenComponentFluidGauge instead of Voltaic's broken gauge component
        components.add(screen.addComponent(new CSScreenComponentFluidGauge(
                () -> handlerSupplier.get()
                        .map(handler -> inputTanks ? handler.getInputTanks() : handler.getOutputTanks())
                        .filter(tanks -> gauge >= 0 && gauge < tanks.length)
                        .map(tanks -> tanks[gauge])
                        .orElse(null),
                x, y + yOffset,
                () -> gauge,
                inputTanks
        )));

        yOffset += 50;
        components.add(screen.addComponent(new ButtonTankSlider(ButtonTankSlider.TankSliderPair.LEFT, x, y + yOffset)
                .setOnPress(button -> cycleGauge.accept(-1))));
        components.add(screen.addComponent(new ButtonTankSlider(ButtonTankSlider.TankSliderPair.RIGHT, x + 8, y + yOffset)
                .setOnPress(button -> cycleGauge.accept(1))));
    }

    public List<AbstractScreenComponent> getComponents() {
        return components;
    }
}