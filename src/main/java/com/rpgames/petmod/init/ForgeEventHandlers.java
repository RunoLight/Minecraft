package com.rpgames.petmod.init;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.client.renders.RaccoonEntityRender;
import com.rpgames.petmod.command.RegisterCommands;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class ForgeEventHandlers {

    //
    // Class to subscribe all the events here
    // Methods should be public static void + SubscribeEvent Annotation
    //

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {
        RegisterCommands.register(event.getCommandDispatcher());
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        PetMod.LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.RACCOON_ENTITY.get(), RaccoonEntityRender::new);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        //some common setup
    }
}
