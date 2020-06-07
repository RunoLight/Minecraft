package com.rpgames.petmod.init;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.client.renders.RaccoonEntityRender;
import com.rpgames.petmod.command.RegisterCommands;
import com.rpgames.petmod.event.PetVillagerSetup;
import com.rpgames.petmod.item.RaccoonEntityEgg;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

    /**
     * Pre-init
     */
    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        //some common setup
        RegistryHandler.fixPOITypeBlockStates(RegistryHandler.POI.get());
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        PetMod.DebugLog("Got game settings " + event.getMinecraftSupplier().get().gameSettings);

        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.RACCOON_ENTITY.get(), RaccoonEntityRender::new);
    }

    /**
     * Init
     */
    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {
        RegisterCommands.register(event.getCommandDispatcher());
    }

    /**
     * Post-init
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        RaccoonEntityEgg.initUnaddedEggs();
    }
    /**
     * Run-time
     */
    @SubscribeEvent
    public void villagerTrades(VillagerTradesEvent event) { PetVillagerSetup.addTrades(event); }
}
