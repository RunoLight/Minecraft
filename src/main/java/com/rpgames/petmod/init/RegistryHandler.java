package com.rpgames.petmod.init;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.block.BlockItemBase;
import com.rpgames.petmod.block.DevBlock;
import com.rpgames.petmod.entity.RaccoonEntity;
import com.rpgames.petmod.item.GuideBookItem;
import com.rpgames.petmod.item.SimpleItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, PetMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, PetMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ENTITIES, PetMod.MOD_ID);
    public static final DeferredRegister<PointOfInterestType> POI_TYPE_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.POI_TYPES, PetMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.PROFESSIONS, PetMod.MOD_ID);

    public static void init(){
        ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Item
    public static final RegistryObject<Item> GUIDE_BOOK = ITEM_DEFERRED_REGISTER.register("guide_book", GuideBookItem::new);
    public static final RegistryObject<Item> SIMPLE_ITEM = ITEM_DEFERRED_REGISTER.register("simple_item", SimpleItem::new);

    // Block
    public static final RegistryObject<Block> DEV_BLOCK = BLOCK_DEFERRED_REGISTER.register("dev_block", DevBlock::new);

    // Block-Items
    public static final RegistryObject<Item> DEV_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("dev_block", () -> new BlockItemBase(DEV_BLOCK.get()));

    // Entity
    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON_ENTITY = ENTITY_DEFERRED_REGISTER
            .register("raccoon_entity",
                    () -> EntityType.Builder.<RaccoonEntity>create(RaccoonEntity::new, EntityClassification.CREATURE)
                            .size(0.9f, 1.3f)
                            .build(new ResourceLocation(PetMod.MOD_ID, "raccoon_entity").toString()));

    
    //Test merge
}
