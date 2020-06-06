package com.rpgames.petmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.rpgames.petmod.network.Networking;
import com.rpgames.petmod.network.PacketOpenGui;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class CommandSpawner implements Command<CommandSource> {

    public static final CommandSpawner CMD = new CommandSpawner();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("Spawn")
                .requires(cs -> cs.hasPermissionLevel(4)) // Permission 4 is creative/op
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        Networking.INSTANCE.sendTo(new PacketOpenGui(), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        return 0;
    }
}
