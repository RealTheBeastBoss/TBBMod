package com.tbb.tbbmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BEASTBOSS_BANANA = new FoodProperties.Builder().nutrition(7).saturationMod(1.5F).build();
    public static final FoodProperties BEASTBOSSANIUM_CARROT = new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).alwaysEat()
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800, 0), 0.5f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800, 1), 0.5f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 800, 1), 0.5f)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0), 0.5f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 800, 1), 0.5f)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 800, 0), 0.5f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 800, 0), 0.5f)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 800, 0), 0.5f).build();
}
