package com.rpgames.petmod.event;

import com.google.common.collect.ImmutableMap;
import com.rpgames.petmod.init.RegistryHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetVillagerSetup {
    /** Trades of villager we will setup in this event */
    private static Int2ObjectMap<List<VillagerTrades.ITrade>> tradesOfVillager;

    /** ref to https://minecraft.gamepedia.com/Trading while building trades */
    private static final Int2ObjectMap<VillagerTrades.ITrade[]> tradesTemplates = gatAsIntMap(
            ImmutableMap.of(
                    2, new VillagerTrades.ITrade[]{
                            buildTrade(Items.EMERALD, 15, RegistryHandler.KEEPER_FURNACE.get(), 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, 7, RegistryHandler.PEANUT_FOOD.get(), 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, (int)((Math.random()+20)*64), RegistryHandler.PEANUT_FOOD.get(), 1, 9, 16, 10)
                    },
                    3, new VillagerTrades.ITrade[]{
                            buildTrade(Items.EMERALD, 10, RegistryHandler.KEEPER_FURNACE.get(), 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, 3, RegistryHandler.PEANUT_FOOD.get(), 1, 9, 16, 10),
                            buildTrade(Items.LEATHER, 24, RegistryHandler.PEANUT_FOOD.get(), 4, 9, 16, 10),
                            buildTrade(Items.CARROT, (int)((Math.random()+20)*64), Items.EMERALD, 1, 9, 16, 10)
                    },
                    4, new VillagerTrades.ITrade[]{
                            buildTrade(Items.EMERALD, 5, RegistryHandler.KEEPER_FURNACE.get(), 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, 2, RegistryHandler.PEANUT_FOOD.get(), 1, 9, 16, 10),
                            buildTrade(Items.LEATHER, 16, RegistryHandler.PEANUT_FOOD.get(), 4, 9, 16, 10),
                            buildTrade(Items.CARROT, (int)((Math.random()+12)*64), Items.EMERALD, 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, (int)((Math.random()+48)*64), RegistryHandler.RACCOON_ENTITY_EGG.get(), 1, 9, 16, 10)
                    },
                    5, new VillagerTrades.ITrade[]{
                            buildTrade(Items.EMERALD, 3, RegistryHandler.KEEPER_FURNACE.get(), 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, 1, RegistryHandler.PEANUT_FOOD.get(), 2, 9, 16, 10),
                            buildTrade(Items.LEATHER, 8, RegistryHandler.PEANUT_FOOD.get(), 4, 9, 16, 10),
                            buildTrade(Items.CARROT, (int)((Math.random()+4)*64), Items.EMERALD, 1, 9, 16, 10),
                            buildTrade(Items.EMERALD, (int)((Math.random()+16)*64), RegistryHandler.RACCOON_ENTITY_EGG.get(), 1, 9, 16, 10)
                    }
            )
    );

    /**
     * Villager levels:
     * 0   == Non-trading villagers
     * 1   == Jobless
     * 2-5 == Villager levels
     * 6   == Wanderer
     * 7   == Wanderer Rare
     */
    public static void addTrades(VillagerTradesEvent event) {
        if (event.getType() == RegistryHandler.PET_PROFESSION.get()) {
            tradesOfVillager = event.getTrades();

            for (int i = 2; i <= 5; i++) {
                addTradeOffers(i, tradesTemplates.get(i));
            }
        }
    }

    /** Inner-space functions */
    private static  void addTradeOffer(int masteryLevel, VillagerTrades.ITrade trade) {
        tradesOfVillager.get(masteryLevel).add(trade);
    }

    private static  void addTradeOffers(int masteryLevel, VillagerTrades.ITrade[] tradeArray) {
        List<VillagerTrades.ITrade> tradeList = new ArrayList<>(Arrays.asList(tradeArray));
        tradesOfVillager.get(masteryLevel).addAll(tradeList);
    }
    private static  void addTradeOffers(VillagerTrades.ITrade[] tradeArray, List<VillagerTrades.ITrade> whereToAdd) {
        List<VillagerTrades.ITrade> tradeList = new ArrayList<>(Arrays.asList(tradeArray));
        whereToAdd.addAll(tradeList);
    }

    private static VillagerTrades.ITrade buildTrade(IItemProvider itemIn, int countIn,
                                             IItemProvider itemOut, int countOut,
                                             int maxUses, int givenExp, float priceMultiple) {
        return (entity, random) -> {
            return new MerchantOffer(
                    new ItemStack(itemIn, countIn),
                    new ItemStack(itemOut, countOut),
                    maxUses, givenExp, priceMultiple);
        };
    }

    private static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> tradeArray) {
        return new Int2ObjectOpenHashMap<>(tradeArray);
    }

    @SubscribeEvent
    public static void villagerTrades(VillagerTradesEvent event) { addTrades(event); }
}
