package com.tbb.tbbmod.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {
    public static final ForgeTier BEASTBOSSANIUM = new ForgeTier(5, 2500, 12.0f, 5.0f, 16, Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> {
        return Ingredient.of(ModItems.BEASTBOSSANIUM_INGOT.get());
    });
    public static final ForgeTier WOOD_CHANGER = new ForgeTier(0, 100, 0.0f, 0.0f, 10, Tags.Blocks.NEEDS_WOOD_TOOL, () -> {
        return Ingredient.of(ModItems.BEASTBOSSANIUM_INGOT.get());
    });
}
