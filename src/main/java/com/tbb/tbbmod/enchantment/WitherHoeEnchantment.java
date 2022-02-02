package com.tbb.tbbmod.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.Random;

public class WitherHoeEnchantment extends Enchantment {
    protected WitherHoeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (!pAttacker.getLevel().isClientSide()) {
            LivingEntity enemy = ((LivingEntity) pTarget);
            float chance = 0f;
            int duration = 0;
            switch (pLevel) {
                case 1:
                    chance = 0.8f; // 20% chance (1 in 5)
                    duration = 8 * 20; // 8 seconds
                    break;
                case 2:
                    chance = 0.75f; // 25% chance (1 in 4)
                    duration = 12 * 20; // 12 seconds
                    break;
                case 3:
                    chance = 0.6667f; // 33.33% chance (1 in 3)
                    duration = 20 * 20; // 20 seconds
                    break;
            }
            if (new Random().nextFloat() >= chance) {
                enemy.addEffect(new MobEffectInstance(MobEffects.WITHER, duration));
            }
        }
        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof HoeItem;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
