package com.rpgames.petmod;

//import com.rpgames.petmod.event.PetVillagerPiece;

import com.rpgames.petmod.event.PetVillagerSetup;
import com.rpgames.petmod.init.ForgeEventHandlers;
import com.rpgames.petmod.init.RegistryHandler;
import com.rpgames.petmod.network.Networking;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("rpgpetmod")
public class PetMod
{
    // Use DebugLog instead
    private static final Logger LOGGER = LogManager.getLogger();

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
        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.registerAll();

        // Mod event bus contains life-cycle events (setup -> pre-init -> init -> post-init)
        FMLJavaModLoadingContext.get().getModEventBus().register(ForgeEventHandlers.class);

        // Forge event bus contains run-time events!
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());

        MinecraftForge.EVENT_BUS.register(this);

        Networking.registerMessages();

        PetVillagerSetup.init();

        // TODO:
        //  GLOBAL TO DO
        //  SET POI OF VILLAGER TO VILLAGER BLOCK
        //  ADD TRADES VILLAGER
        //  ADD VILLAGER BUILDING
    }

    public static void DebugLog(String s) {
        StringBuilder sb = new StringBuilder().append(MOD_ID).append(" ---------- ").append(s);
        System.out.println(sb);
    }
}
