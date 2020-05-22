package com.rpgames.petmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.rpgames.petmod.PetMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class RegisterCommands {

    public static String COMMAND_PREFIX = PetMod.MOD_ID;

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> cmdNode = dispatcher.register(
                Commands.literal(COMMAND_PREFIX)
                    .then(CommandHelloWorld.register(dispatcher))
                    .then(CommandSpawner.register(dispatcher))
        );

        CheckPP.register(dispatcher);
    }
}
