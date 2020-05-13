package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import net.minecraft.item.Item;

public class SimpleItem extends Item {

    public SimpleItem() {
        super(new Item.Properties()
                .group(PetMod.petModItemGroup));
    }
}
