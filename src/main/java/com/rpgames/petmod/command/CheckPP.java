package com.rpgames.petmod.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class CheckPP {

    private static final SuggestionProvider<CommandSource> SUGGEST_COLOR = (source, builder) -> {
        return ISuggestionProvider.suggest(TextFormatting.getValidValues(true,false).stream(), builder);
    };

    public static void register(com.mojang.brigadier.CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("isPPworking?").executes(source -> {
            return ppCommand(source.getSource(), source.getSource().asPlayer());
        }).then(Commands.argument("target", EntityArgument.player()).executes(source -> {
            return ppCommand(source.getSource(), EntityArgument.getPlayer(source, "target"));
        })).then(Commands.argument("color", StringArgumentType.string()).suggests(SUGGEST_COLOR).executes(source -> {
            return ppCommand(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "color"));
        })));
    }

    private static int ppCommand(CommandSource source, PlayerEntity player) {
        source.sendFeedback(new TranslationTextComponent("commands.pp", player.getDisplayName()), true);
        return 1;
    }

    private static int ppCommand(CommandSource source, PlayerEntity player, String color) {
        source.sendFeedback(new TranslationTextComponent("commands.pp.color", TextFormatting.getValueByName(color).toString(), player.getDisplayName()), true);
        return 1;
    }
}
