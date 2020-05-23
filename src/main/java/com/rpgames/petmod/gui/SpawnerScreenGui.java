package com.rpgames.petmod.gui;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.network.Networking;
import com.rpgames.petmod.network.PacketSpawn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class SpawnerScreenGui extends Screen {

    // Background texture
    private final ResourceLocation GUI = new ResourceLocation(PetMod.MOD_ID, "textures/gui/spawner_gui.png");
    //private static final int WIDTH = 179;
    //private static final int HEIGHT = 151;

    private static final int WIDTH = 180;
    private static final int HEIGHT = 148;

    private final String summonVillager = "/summon villager ~ ~1 ~ {VillagerData:{profession:\"rpgpetmod:pet_villager\",level:5}}";
    private final String cleanEntities = "/kill @e[distance=..20, type=!minecraft:player]";
    private final String herobrinePrank = "/tellraw @a {\"text\":\"Herobrine joined the game\",\"color\":\"yellow\"}";

    public SpawnerScreenGui() {
        super(new StringTextComponent("SpawnSomething"));
    }

    @Override
    protected void init() {
        //super.init();
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(new Button(relX + 130  + 20, relY + 10, 20, 20, "X", button -> hideGui()));
        addButton(new Button(relX + 10, relY + 10, 135, 20, "Clean", button -> executeCommand(cleanEntities)));

        addButton(new Button(relX + 10,  relY + 118, 160,20, "Raccoon", button -> spawn(PetMod.MOD_ID, "raccoon_entity")));
        addButton(new Button(relX + 10, relY + 37, 160, 20, "Pet keeper", button -> executeCommand(summonVillager)));
        addButton(new Button(relX + 10, relY + 64, 160, 20, "Cow", button -> spawn("minecraft:cow")));
        addButton(new Button(relX + 10, relY + 91, 160, 20, "Sheep", button -> spawn("minecraft:sheep")));
    }

    /*
     Commands
     */

    // Example arguments: ("minecraft:zombie")
    private void spawn(String id) {
        if (minecraft != null) {
            assert minecraft.player != null;
            Networking.INSTANCE.sendToServer(new PacketSpawn(id, minecraft.player.dimension, minecraft.player.getPosition()));
        }
    }

    // Example arguments: ("minecraft", "zombie")
    private void spawn(String modId, String id) {
        if (minecraft != null) {
            assert minecraft.player != null;
            StringBuilder sb = new StringBuilder();
            String mobId = sb.append(modId).append(':').append(id).toString();
            Networking.INSTANCE.sendToServer(new PacketSpawn(mobId, minecraft.player.dimension, minecraft.player.getPosition()));
        }
    }

    private void executeCommand(String command) {
        if (minecraft != null) {
            assert minecraft.player != null;
            minecraft.player.sendChatMessage(command);
        }
    }

    private void hideGui() {
        assert minecraft != null;
        minecraft.displayGuiScreen(null);
    }
     /*
      --Commands
      */

    // Bind background picture
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(GUI);
            int relX = (this.width + WIDTH) / 2;
            int relY = (this.height - HEIGHT) / 2;
            this.blit(relX - WIDTH, relY, 0,0, WIDTH, HEIGHT);
            super.render(mouseX, mouseY, partialTicks);
        }
    }

    // Opens gui
    public static void open() {
        Minecraft.getInstance().displayGuiScreen(new SpawnerScreenGui());
    }

    public boolean isPauseScreen() {
        return false;
    }
}
