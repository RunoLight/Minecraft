package com.rpgames.petmod;

import com.rpgames.petmod.client.renders.RaccoonEntityRender;
import com.rpgames.petmod.init.ForgeEventHandlers;
import com.rpgames.petmod.network.Networking;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rpgames.petmod.init.RegistryHandler;

@Mod("rpgpetmod")
public class PetMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "rpgpetmod";
    public static PetMod instance;

    public static final ItemGroup petModItemGroup = new ItemGroup("rpg_pets") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.GUIDE_BOOK.get());
        }
    };

    public PetMod() {
        instance = this;

       // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
       // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
       // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        RegistryHandler.registerAll();

        // Mod event bus contains life-cycle events (setup -> pre-init -> init -> post-init)
        // Forge event bus contains run-time events!
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());

        FMLJavaModLoadingContext.get().getModEventBus().register(ForgeEventHandlers.class);


        Networking.registerMessages();
    }


    //private void doClientStuff(final FMLClientSetupEvent event) {
    //    // do something that can only be done on the client
    //    RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.RACCOON_ENTITY.get(), RaccoonEntityRender::new);
    //    LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    //}

    // Using for other mods compability

}
