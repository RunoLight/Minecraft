package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class PeanutFood extends Item {
    public PeanutFood() {
        super(new Item.Properties()
        .group(PetMod.petModItemGroup)
        .food(new Food.Builder()
                .hunger(6)
                .saturation(1.2f)
                .build()));
    }
}
