package com.rpgames.petmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class KeeperFurnace extends Block {
    public KeeperFurnace() {
        super(Block.Properties.create(Material.SAND)
        .harvestTool(ToolType.SHOVEL)
        .harvestLevel(0)//0-wooden shovel
        .sound(SoundType.SNOW)
        .hardnessAndResistance(1.0f,2.0f)
        );
    }
}
