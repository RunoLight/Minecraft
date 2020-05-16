package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.init.RaccoonEntityInit;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;

public class RaccoonEntityEggItem extends SpawnEggItem {

    public RaccoonEntityEggItem() {
        super(RaccoonEntityInit.RACCOON_ENTITY,
                0x8a8372,
                0x37396b,
                new Item.Properties().group(PetMod.petModItemGroup));
    }
}
