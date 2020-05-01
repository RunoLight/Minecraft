package com.RPGames.PetMod.Utilities;

//this class will store all mod entities


import com.RPGames.PetMod.Items.ItemBase;
import com.RPGames.PetMod.PetMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, PetMod.MOD_ID);

    public static void init(){
        ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> GUIDEBOOK = ITEM_DEFERRED_REGISTER.register("guidebook", ItemBase::new);
}
