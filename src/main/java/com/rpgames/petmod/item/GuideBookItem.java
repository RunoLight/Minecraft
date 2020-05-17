package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import net.minecraft.item.*;

public class GuideBookItem extends WrittenBookItem {

    public GuideBookItem() {
        super(new Item.Properties()
                .group(PetMod.petModItemGroup)
                .rarity(Rarity.RARE));
    }


}
