package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.entity.RaccoonEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.item.SpawnEggItem;

public class RaccoonEntityEgg extends SpawnEggItem {
    public RaccoonEntityEgg(EntityType<RaccoonEntity> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
        super(typeIn, 0x288888, 0x1111111, new Item.Properties()
                .group(PetMod.petModItemGroup)
                .rarity(Rarity.EPIC));
    }
}
