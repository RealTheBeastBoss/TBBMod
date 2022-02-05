package com.tbb.tbbmod.world.feature;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacedFeatures {
    public static final PlacedFeature BEASTBOSS_TREE_PLACED =
            PlacementUtils.register("beastboss_tree_placed",
                    ModConfiguredFeature.BEASTBOSS_TREE_CHECKED.placed(VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(256))));
    public static final PlacedFeature BEASTBOSS_FLOWER_PLACED = PlacementUtils.register("beastboss_flower_placed",
            ModConfiguredFeature.BEASTBOSS_FLOWER.placed(RarityFilter.onAverageOnceEvery(64),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
}
