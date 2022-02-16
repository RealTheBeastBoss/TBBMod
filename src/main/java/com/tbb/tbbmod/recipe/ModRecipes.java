package com.tbb.tbbmod.recipe;

import com.tbb.tbbmod.TBBMod;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TBBMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DiamondConverterRecipe>> DIAMOND_CONVERTER_SERIALIZER =
            SERIALIZERS.register("diamond_converting", () -> DiamondConverterRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, DiamondConverterRecipe.Type.ID, DiamondConverterRecipe.Type.INSTANCE);
    }
}
