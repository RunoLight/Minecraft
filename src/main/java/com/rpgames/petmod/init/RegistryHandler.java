package com.rpgames.petmod.init;

import com.google.common.collect.ImmutableSet;
import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.block.BlockItemBase;
import com.rpgames.petmod.block.DevBlock;
import com.rpgames.petmod.block.PeanutFoodBlock;
import com.rpgames.petmod.entity.RaccoonEntity;
import com.rpgames.petmod.item.GuideBookItem;
import com.rpgames.petmod.item.RaccoonEntityEgg;
import com.rpgames.petmod.item.SimpleItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.rpgames.petmod.item.PeanutFood;

import static net.minecraft.village.PointOfInterestType.getAllStates;

public class RegistryHandler {


    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, PetMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, PetMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ENTITIES, PetMod.MOD_ID);
    public static final DeferredRegister<PointOfInterestType> POI_TYPE_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.POI_TYPES, PetMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.PROFESSIONS, PetMod.MOD_ID);

    public static void registerAll() {
        ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        POI_TYPE_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        VILLAGER_PROFESSION_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /*
     * TODO: Split up registers to different classes
     *  which will contain ID of item as string instead of silent argument in .register() func
     *  for having ID base
     */


    // Item
    public static final RegistryObject<Item> GUIDE_BOOK = ITEM_DEFERRED_REGISTER.register("guide_book", GuideBookItem::new);
    public static final RegistryObject<Item> SIMPLE_ITEM = ITEM_DEFERRED_REGISTER.register("simple_item", SimpleItem::new);

    //Food
    public static final RegistryObject<Item> PEANUT_FOOD = ITEM_DEFERRED_REGISTER.register("peanut_food", PeanutFood::new);

    // Block
    public static final RegistryObject<Block> DEV_BLOCK = BLOCK_DEFERRED_REGISTER.register("dev_block", DevBlock::new);
    public static final RegistryObject<Block> PEANUT_FOOD_BLOCK = BLOCK_DEFERRED_REGISTER.register("peanut_food_block", PeanutFoodBlock::new);

    // Block-Items
    public static final RegistryObject<Item> DEV_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("dev_block", () -> new BlockItemBase(DEV_BLOCK.get()));
    public static final RegistryObject<Item> PEANUT_FOOD_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("peanut_food_block", () -> new BlockItemBase(PEANUT_FOOD_BLOCK.get()));

    // Entity
    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON_ENTITY = ENTITY_DEFERRED_REGISTER
            .register("raccoon_entity",
                    () -> EntityType.Builder.<RaccoonEntity>create(RaccoonEntity::new, EntityClassification.CREATURE)
                            .size(0.9f, 1.3f)
                            .build(new ResourceLocation(PetMod.MOD_ID, "raccoon_entity").toString()));

    public static final RegistryObject<Item> RACCOON_ENTITY_EGG = ITEM_DEFERRED_REGISTER.register("raccoon_entity_egg",
            ()-> new RaccoonEntityEgg(RACCOON_ENTITY,
            0x111111,
            0x222222,
            new Item.Properties()
                    .group(PetMod.petModItemGroup)
                    .rarity(Rarity.EPIC)
            ));

    // POI
    public static final RegistryObject<PointOfInterestType> POI = POI_TYPE_DEFERRED_REGISTER.register(
            "pet_poi", () -> new PointOfInterestType("pet_poi", getAllStates(RegistryHandler.DEV_BLOCK.get()), 1, null, 1)
    );

    // Profession
    public static final RegistryObject<VillagerProfession> PET_PROFESSION = VILLAGER_PROFESSION_DEFERRED_REGISTER.register(
            "pet_villager", () -> new VillagerProfession("pet_villager", POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FARMER)
    );

}
