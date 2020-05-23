package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvents;

public class SimpleItem extends Item {

    public SimpleItem() {
        super(new Item.Properties()
                .group(PetMod.petModItemGroup));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        context.getPlayer().playSound(SoundEvents.ENTITY_WANDERING_TRADER_YES, 1.0F, 1.0F);
        return super.onItemUse(context);
    }
}
