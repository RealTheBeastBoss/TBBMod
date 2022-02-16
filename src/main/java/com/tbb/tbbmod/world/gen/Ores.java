package com.tbb.tbbmod.world.gen;

import com.tbb.tbbmod.block.ModBlocks;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Ores {
    public static final int OVERWORLD_AMOUNT = 4; // Spawns on average once every 4 chunks
    public static final int DEEPSLATE_AMOUNT = 2; // Spawns on average once every 2 chunks
    public static final int OVERWORLD_VEINSIZE = 3;
    public static final int DEEPSLATE_VEINSIZE = 4;

    public static PlacedFeature OVERWORLD_OREGEN;
    public static PlacedFeature DEEPSLATE_OREGEN;

    public static void registerConfiguredFeatures() {
        OreConfiguration overworldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES,
                ModBlocks.BEASTBOSSANIUM_ORE.get().defaultBlockState(), OVERWORLD_VEINSIZE);
        OVERWORLD_OREGEN = registerPlacedFeature("overworld_beastbossanium_ore", Feature.ORE.configured(overworldConfig),
                RarityFilter.onAverageOnceEvery(OVERWORLD_AMOUNT), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(16)));
        OreConfiguration deepslateConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                ModBlocks.DEEPSLATE_BEASTBOSSANIUM_ORE.get().defaultBlockState(), DEEPSLATE_VEINSIZE);
        DEEPSLATE_OREGEN = registerPlacedFeature("deepslate_beastbossanium_ore", Feature.ORE.configured(deepslateConfig),
                RarityFilter.onAverageOnceEvery(DEEPSLATE_AMOUNT), InSquarePlacement.spread(), BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedFeature(String registryName,
                                                                                                              ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        PlacedFeature placed = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(registryName), feature)
                .placed(placementModifiers);
        return PlacementUtils.register(registryName, placed);
    }

    @SubscribeEvent
    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_OREGEN);
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_OREGEN);
    }
}
