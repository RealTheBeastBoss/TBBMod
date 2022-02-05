package com.tbb.tbbmod.world.gen;

import com.tbb.tbbmod.world.feature.ModPlacedFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModTreeGeneration {
    public static void generateTrees(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.OVERWORLD) && !types.contains(BiomeDictionary.Type.WATER) &&
                !types.contains(BiomeDictionary.Type.WASTELAND) && !types.contains(BiomeDictionary.Type.DEAD) &&
                !types.contains(BiomeDictionary.Type.VOID) && !types.contains(BiomeDictionary.Type.UNDERGROUND)) {
            List<Supplier<PlacedFeature>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModPlacedFeatures.BEASTBOSS_TREE_PLACED);
        }
    }
}
