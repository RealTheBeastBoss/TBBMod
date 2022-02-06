package com.tbb.tbbmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BEASTBOSS_BANANA = new FoodProperties.Builder().nutrition(7).saturationMod(1.5F).build();
    public static final FoodProperties BEASTBOSSANIUM_CARROT = new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).alwaysEat()
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 0.5f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1), 0.5f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 800, 1), 0.5f).build(); // TODO: Finish these effects
}
