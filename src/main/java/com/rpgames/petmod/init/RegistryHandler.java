package com.rpgames.petmod.init;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.item.GuideBookItem;
import com.rpgames.petmod.item.SimpleItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, PetMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, PetMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ENTITIES, PetMod.MOD_ID);

    public static void init(){
        ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> GUIDE_BOOK = ITEM_DEFERRED_REGISTER.register("guide_book", GuideBookItem::new);
    public static final RegistryObject<Item> SIMPLE_ITEM = ITEM_DEFERRED_REGISTER.register("simple_item", SimpleItem::new);




    public static ResourceLocation location(String name)
    {
        return new ResourceLocation(PetMod.MOD_ID, name);
    }


}
