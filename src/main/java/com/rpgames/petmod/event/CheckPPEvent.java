package com.rpgames.petmod.event;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.command.CheckPP;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = PetMod.MOD_ID)
public class CheckPPEvent {

    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event) {
        CheckPP.register(event.getCommandDispatcher());
    }
}
