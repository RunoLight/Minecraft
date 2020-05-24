package com.rpgames.petmod.event;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.rpgames.petmod.init.RegistryHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.rpgames.petmod.PetMod.MOD_ID;

public class PetVillagerSetup {

    /** Trades of villager we will setup in this event */
    private static Int2ObjectMap<List<VillagerTrades.ITrade>> tradesOfVillager;

    /** ref to https://minecraft.gamepedia.com/Trading while building trades */
    private static final Int2ObjectMap<VillagerTrades.ITrade[]> tradesTemplates = gatAsIntMap(
            ImmutableMap.of(
                    2, new VillagerTrades.ITrade[]{
                            buildTrade(Blocks.STONE, 20, Items.EMERALD, 10, 9, 16, 10),
                            buildTrade(Blocks.STONE, 20, Items.EMERALD, 10, 9, 16, 10)
                    },
                    3, new VillagerTrades.ITrade[]{
                            buildTrade(Blocks.STONE, 30, Items.EMERALD, 10, 9, 16, 10),
                            buildTrade(Blocks.STONE, 30, Items.EMERALD, 10, 9, 16, 10),
                            buildTrade(Blocks.STONE, 30, Items.EMERALD, 10, 9, 16, 10),
                            buildTrade(Blocks.STONE, 30, Items.EMERALD, 10, 9, 16, 10),
                    },
                    4, new VillagerTrades.ITrade[]{
                            buildTrade(Blocks.STONE, 40, Items.EMERALD, 10, 9, 16, 10),
                            buildTrade(Blocks.STONE, 40, Items.EMERALD, 10, 9, 16, 10)
                    },
                    5, new VillagerTrades.ITrade[]{
                            buildTrade(Blocks.STONE, 50, Items.EMERALD, 10, 9, 16, 10),
                            buildTrade(Blocks.STONE, 50, Items.EMERALD, 10, 9, 16, 10)
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

    // TODO: doesnt work
    public static void addTrades(WandererTradesEvent event) {
        // Makes wanderers trade as last two levels of this villager
        List<VillagerTrades.ITrade> genericTrades = event.getGenericTrades();
        addTradeOffers(tradesTemplates.get(4), genericTrades);

        List<VillagerTrades.ITrade> rareTrades = event.getRareTrades();
        addTradeOffers(tradesTemplates.get(5), rareTrades);
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

    /**
     * Villager building setup
     */
    @SuppressWarnings("deprecation")
    public static void init() {
        PlainsVillagePools.init();



        JigsawPattern old = JigsawManager.REGISTRY.get(new ResourceLocation("minecraft", "village/plains/houses"));
        List<JigsawPiece> shuffled = old.getShuffledPieces(new Random());
        List<Pair<JigsawPiece, Integer>> newPieces = new ArrayList<>();
        for(JigsawPiece p : shuffled){
            newPieces.add(new Pair<>(p, 1));
        }
        newPieces.add(new Pair<>(new SingleJigsawPiece("rpgpetmod:village/houses/pet_keeper_house"), 0));
        ResourceLocation something = old.func_214948_a();
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("minecraft", "village/plains/houses"), something, newPieces, JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(MOD_ID, "village/houses/pet_keeper_house"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(MOD_ID + ":village/houses/pet_keeper_house"), 100)), JigsawPattern.PlacementBehaviour.RIGID));
        //JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation("pillager_outpost/towers"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new ListJigsawPiece(ImmutableList.of(new SingleJigsawPiece("pillager_outpost/watchtower"), new SingleJigsawPiece("pillager_outpost/watchtower_overgrown", ImmutableList.of(new IntegrityProcessor(0.05F))))), 1)), JigsawPattern.PlacementBehaviour.RIGID));

        //JigsawManager.func_214889_a(new JigsawPattern(new ResourceLocation(MOD_ID, "), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece("rpgpetmod:village/houses/pet_keeper_house"), 0)), JigsawPattern.PlacementBehaviour.RIGID));
    }
}
