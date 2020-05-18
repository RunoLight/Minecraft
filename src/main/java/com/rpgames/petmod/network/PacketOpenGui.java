package com.rpgames.petmod.network;

import com.rpgames.petmod.gui.SpawnerScreenGui;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketOpenGui {

    public PacketOpenGui(PacketBuffer buf) {

    }

    public void toBytes(PacketBuffer buf) {

    }

    public PacketOpenGui() {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(SpawnerScreenGui::open);
        ctx.get().setPacketHandled(true);
    }
}
