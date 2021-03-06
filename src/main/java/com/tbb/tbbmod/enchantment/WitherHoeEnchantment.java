package com.tbb.tbbmod.enchantment;

import com.tbb.tbbmod.sounds.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
            if (pTarget == null) {
                return;
            }
            LivingEntity enemy = ((LivingEntity) pTarget);
            float chance = 0f;
            int duration = 0; // The effect duration in ticks
            switch (pLevel) { // Enchantment Level
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
                default: // This is in case Quark increases the enchant level
                    chance = 0.5f; // 50% chance (1 in 2)
                    duration = 25 * 20; // 25 seconds
                    break;
            }
            if (new Random().nextFloat() >= chance) {
                enemy.addEffect(new MobEffectInstance(MobEffects.WITHER, duration));
                pAttacker.getLevel().playSound((Player) pAttacker, pAttacker.blockPosition(), ModSounds.WITHER_HOE_STRIKE.get(), SoundSource.PLAYERS, 1f, 1f);
                if (pTarget instanceof Player) {
                    pTarget.getLevel().playSound((Player) pTarget, pTarget.blockPosition(), ModSounds.WITHER_HOE_STRIKE.get(), SoundSource.PLAYERS, 1f, 1f);
                }
            }
        }
        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof HoeItem;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}