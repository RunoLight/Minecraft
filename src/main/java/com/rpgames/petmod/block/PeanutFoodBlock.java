package com.rpgames.petmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class PeanutFoodBlock extends Block {
    public PeanutFoodBlock() {
        super(Block.Properties.create(Material.SPONGE)
                .harvestTool(ToolType.AXE)
                .harvestLevel(1)//0-wooden shovel
                .sound(SoundType.SNOW)
                .hardnessAndResistance(1.0f,2.0f));
    }
}
