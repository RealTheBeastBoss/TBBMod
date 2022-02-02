package com.tbb.tbbmod.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class BeastBossKickEnchantment extends Enchantment {

    protected BeastBossKickEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostHurt(LivingEntity pUser, Entity pAttacker, int pLevel) {
        if (!pUser.getLevel().isClientSide()) {
            LivingEntity enemy = ((LivingEntity) pAttacker);
            float chance = 0f;
            if (pLevel == 1) {
                chance = 0.975f;
            } else if (pLevel == 2) {
                chance = 0.9667f;
            } else if (pLevel == 3) {
                chance = 0.95f;
            } else {
                chance = 0.9f;
            }
            if (new Random().nextFloat() >= chance) {
                if (pAttacker.equals(null)) {
                    MutableComponent mutableComponent = new TranslatableComponent(this.getDescriptionId() + ".entity_null_message");
                    mutableComponent.withStyle(ChatFormatting.UNDERLINE);
                    pUser.sendMessage(mutableComponent, pUser.getUUID());
                }
                enemy.move(MoverType.PLAYER, new Vec3(0, 15, 0));
                MutableComponent mutableComponent = new TextComponent(pUser.getDisplayName().getString() + " ");
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".kick_message"));
                mutableComponent.withStyle(ChatFormatting.GREEN);
                pUser.sendMessage(mutableComponent, pUser.getUUID());
            }
        }
        super.doPostHurt(pUser, pAttacker, pLevel);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
