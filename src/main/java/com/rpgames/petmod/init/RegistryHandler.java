package com.rpgames.petmod.init;

import com.rpgames.petmod.block.BlockItemBase;
import com.rpgames.petmod.block.DevBlock;
import com.rpgames.petmod.item.GuideBookItem;
import com.rpgames.petmod.item.SimpleItem;
import com.rpgames.petmod.PetMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, PetMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, PetMod.MOD_ID);

    public static void init(){
        ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Blocks
    public static final RegistryObject<Block> DEV_BLOCK = BLOCK_DEFERRED_REGISTER.register("dev_block", DevBlock::new);

    //Items
    public static final RegistryObject<Item> GUIDE_BOOK = ITEM_DEFERRED_REGISTER.register("guide_book", GuideBookItem::new);
    public static final RegistryObject<Item> SIMPLE_ITEM = ITEM_DEFERRED_REGISTER.register("simple_item", SimpleItem::new);

    //Block-Items
    public static final RegistryObject<Item> DEV_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("dev_block", () -> new BlockItemBase(DEV_BLOCK.get()));
}
