package net.pastek.chemicalscience.common.packet;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.pastek.chemicalscience.ChemicalScience;

@EventBusSubscriber(modid = ChemicalScience.MOD_ID)
public class CSNetwork {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                PacketToggleNightVisionMode.TYPE,
                PacketToggleNightVisionMode.STREAM_CODEC,
                PacketToggleNightVisionMode::handle
        );

        registrar.playToServer(
                PacketTransparencyTogglePayload.TYPE,
                PacketTransparencyTogglePayload.STREAM_CODEC,
                PacketTransparencyTogglePayload::handleData
        );

        registrar.playToServer(
               ServerboundCSFluidGaugeClickPayload.TYPE,
               ServerboundCSFluidGaugeClickPayload.STREAM_CODEC,
               ServerboundCSFluidGaugeClickPayload::handle
        );
    }
}