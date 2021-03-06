package com.rpgames.petmod.init;

import com.google.common.collect.ImmutableSet;
import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.block.BlockItemBase;
import com.rpgames.petmod.block.KeeperFurnace;
import com.rpgames.petmod.block.PeanutFoodBlock;
import com.rpgames.petmod.entity.RaccoonEntity;
import com.rpgames.petmod.item.PeanutFood;
import com.rpgames.petmod.item.RaccoonEntityEgg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, PetMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, PetMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ENTITIES, PetMod.MOD_ID);
    public static final DeferredRegister<PointOfInterestType> POI_TYPE_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.POI_TYPES, PetMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.PROFESSIONS, PetMod.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, PetMod.MOD_ID);

    public static void registerAll() {
        ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        POI_TYPE_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        VILLAGER_PROFESSION_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUND_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Item
    //public static final RegistryObject<Item> GUIDE_BOOK = ITEM_DEFERRED_REGISTER.register("guide_book", GuideBookItem::new);
    //public static final RegistryObject<Item> SIMPLE_ITEM = ITEM_DEFERRED_REGISTER.register("simple_item", SimpleItem::new);

    //Food
    public static final RegistryObject<Item> PEANUT_FOOD = ITEM_DEFERRED_REGISTER.register("peanut_food", PeanutFood::new);

    // Block
    public static final RegistryObject<Block> KEEPER_FURNACE = BLOCK_DEFERRED_REGISTER.register("dev_block", KeeperFurnace::new);
    public static final RegistryObject<Block> PEANUT_FOOD_BLOCK = BLOCK_DEFERRED_REGISTER.register("peanut_food_block", PeanutFoodBlock::new);

    // Block-Items
    public static final RegistryObject<Item> DEV_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("dev_block", () -> new BlockItemBase(KEEPER_FURNACE.get()));
    public static final RegistryObject<Item> PEANUT_FOOD_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("peanut_food_block", () -> new BlockItemBase(PEANUT_FOOD_BLOCK.get()));

    // Entity
    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON_ENTITY = ENTITY_DEFERRED_REGISTER
            .register("raccoon_entity",
                    () -> EntityType.Builder.create(RaccoonEntity::new, EntityClassification.CREATURE)
                            .size(0.9f, 1.3f)
                            .build(new ResourceLocation(PetMod.MOD_ID, "raccoon_entity").toString()));

    public static final RegistryObject<Item> RACCOON_ENTITY_EGG = ITEM_DEFERRED_REGISTER.register("raccoon_entity_egg",
            ()-> new RaccoonEntityEgg(RACCOON_ENTITY,
                        0x8aafff,
                        0xff8a8a,
                        new Item.Properties()
                                .group(PetMod.petModItemGroup)
                                .rarity(Rarity.EPIC)
            ));

    // POI
    public static final RegistryObject<PointOfInterestType> POI = POI_TYPE_DEFERRED_REGISTER.register(
            "pet_poi",
            () -> new PointOfInterestType(
                    "pet_poi",
                    getAllStates(RegistryHandler.KEEPER_FURNACE.get()),
                    1,
                     (iHaveNoIdeaHowItWorks) -> { return true; },
                     1
             )
    );

    // Profession
    public static final RegistryObject<VillagerProfession> PET_PROFESSION = VILLAGER_PROFESSION_DEFERRED_REGISTER.register(
            "pet_villager", () -> new VillagerProfession(
                    "pet_villager", POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FARMER)
    );

    // Sound events
    public static final RegistryObject<SoundEvent> TEST_SOUND = SOUND_DEFERRED_REGISTER.register(
            "test_sound", () -> new net.minecraft.util.SoundEvent(new ResourceLocation(PetMod.MOD_ID, "test_sound"))
    );

    /**
     * Reflection fix
     * Methods to fix POI reflection, makes villagers available to switch profession to some of modded one
     */
    private static final Method blockStatesInjector;

    static
    {
        blockStatesInjector = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class);
    }

    public static void fixPOITypeBlockStates(PointOfInterestType poiType)
    {
        try
        {
            blockStatesInjector.invoke(null, poiType);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * -- Reflection fix
     */

    public static Set<BlockState> getAllStates(Block block)
    {
        return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
    }
}
