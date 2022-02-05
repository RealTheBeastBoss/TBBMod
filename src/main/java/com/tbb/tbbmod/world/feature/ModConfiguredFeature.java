package com.tbb.tbbmod.world.feature;

import com.tbb.tbbmod.block.ModBlocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeature {
    public static final ConfiguredFeature<TreeConfiguration, ?> BEASTBOSS_TREE =
            FeatureUtils.register("beastboss_tree", Feature.TREE.configured(
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(ModBlocks.BEASTBOSS_LOG.get()),
                            new StraightTrunkPlacer(5, 2, 0),
                            BlockStateProvider.simple(ModBlocks.BEASTBOSS_LEAVES.get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                            new TwoLayersFeatureSize(1, 0, 1)).build()));

    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> BEASTBOSS_TREE_CHECKED =
            FeatureUtils.register("beastboss_tree_feature",
                    Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            BEASTBOSS_TREE.filteredByBlockSurvival(ModBlocks.BEASTBOSS_SAPLING.get()), 0.1f)),
                            BEASTBOSS_TREE.filteredByBlockSurvival(ModBlocks.BEASTBOSS_SAPLING.get()))));

    public static final ConfiguredFeature<RandomPatchConfiguration, ?> BEASTBOSS_FLOWER =
            FeatureUtils.register("flower_beastboss",
                    Feature.FLOWER.configured(new RandomPatchConfiguration(2, 6, 2, () -> {
                        return Feature.SIMPLE_BLOCK.configured(
                                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BEASTBOSS_FLOWER.get())))
                                .onlyWhenEmpty();})));
}
