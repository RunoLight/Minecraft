package com.rpgames.petmod.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class RaccoonEntityInit {
    public static EntityType<?> RACCOON_ENTITY;

    public static void registerEntityWorldSpawns(){
        registerEntityWorldSpawn(EntityClassification.CREATURE, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BEACH, Biomes.RIVER);
    }


    public static void registerEntityWorldSpawn(EntityClassification entityClassification, Biome... biomes) {
        for(Biome biome : biomes){
            if(biome!= null){
                biome.getSpawns(entityClassification).add(new Biome.SpawnListEntry(RACCOON_ENTITY, 10,1,10));
            }
        }
    }
}
