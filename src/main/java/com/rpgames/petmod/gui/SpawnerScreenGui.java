package com.rpgames.petmod.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.network.Networking;
import com.rpgames.petmod.network.PacketSpawn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class SpawnerScreenGui extends Screen {

    // size of spawner_gui.png
    private static final int WIDTH = 179;
    private static final int HEIGHT = 151;

    private final ResourceLocation GUI = new ResourceLocation(PetMod.MOD_ID, "textures/gui/spawner_gui.png");

    public SpawnerScreenGui() {
        super(new StringTextComponent("SpawnSomething"));
    }

    @Override
    protected void init() {
        //super.init();
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(new Button(relX + 10, relY + 10, 160, 20, "Raccoon", button -> spawn(PetMod.MOD_ID + ":" + "raccoon_entity")));
        addButton(new Button(relX + 10, relY + 37, 160, 20, "Zombie", button -> spawn("minecraft:zombie")));
        addButton(new Button(relX + 10, relY + 64, 160, 20, "Cow", button -> spawn("minecraft:cow")));
        addButton(new Button(relX + 10, relY + 91, 160, 20, "Sheep", button -> spawn("minecraft:sheep")));
        addButton(new Button(relX + 10, relY + 118, 160, 20, "Chicken", button -> spawn("minecraft:chicken")));
    }

    public boolean isPauseScreen() {
        return false;
    }

    private void spawn(String id) {
        if (minecraft != null) {
            Networking.INSTANCE.sendToServer(new PacketSpawn(id, minecraft.player.dimension, minecraft.player.getPosition()));
            minecraft.displayGuiScreen(null);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(GUI);
            int relX = (this.width + WIDTH) / 2;
            int relY = (this.height - HEIGHT) / 2;
            this.blit(relX, relY, 0,0,WIDTH, HEIGHT);
            super.render(mouseX, mouseY, partialTicks);
        }
    }

    public static void open() {
        Minecraft.getInstance().displayGuiScreen(new SpawnerScreenGui());
    }
}
