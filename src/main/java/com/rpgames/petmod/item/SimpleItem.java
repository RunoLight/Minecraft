package com.rpgames.petmod.item;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.init.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class SimpleItem extends Item {

    public SimpleItem() {
        super(new Item.Properties()
                .group(PetMod.petModItemGroup));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        context.getPlayer().playSound(RegistryHandler.TEST_SOUND.get(), 1.0F, 1.0F);
        return super.onItemUse(context);
    }
}
