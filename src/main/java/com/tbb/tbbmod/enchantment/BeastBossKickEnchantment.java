package com.tbb.tbbmod.enchantment;

import com.tbb.tbbmod.sounds.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BeastBossKickEnchantment extends Enchantment {

    protected BeastBossKickEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public static boolean kickSound = false;

    @Override
    public void doPostHurt(LivingEntity pUser, Entity pAttacker, int pLevel) {
        if (!pUser.getLevel().isClientSide()) {
            LivingEntity enemy = ((LivingEntity) pAttacker);
            float chance = 0f;
            if (pLevel == 1) {
                chance = 0.975f; // 2.5% Chance ()
            } else if (pLevel == 2) {
                chance = 0.9667f; // 3.33% Chance ()
            } else if (pLevel == 3) {
                chance = 0.95f; // 5% Chance (1 in 20)
            } else {
                chance = 0.9f; // 10% Chance (1 in 10)
            }
            if (new Random().nextFloat() >= chance) {
                enemy.move(MoverType.PLAYER, new Vec3(0, 15, 0));
                MutableComponent mutableComponent = new TextComponent(pUser.getDisplayName().getString() + " ");
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".kick_message"));
                mutableComponent.withStyle(ChatFormatting.GREEN);
                pUser.sendMessage(mutableComponent, pUser.getUUID());
                pUser.getLevel().playSound((Player) pUser, pUser.blockPosition(), ModSounds.BEASTBOSS_KICK_LAUNCH.get(), SoundSource.PLAYERS, 1f, 1f);
                kickSound = true;
            }
        }
        super.doPostHurt(pUser, pAttacker, pLevel);
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END || !event.player.level.isClientSide()) return;

        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BEASTBOSS_KICK.get(), event.player);

        if (level > 0 && kickSound) {
            event.player.playSound(ModSounds.BEASTBOSS_KICK_LAUNCH.get(), 1f, 1f);
            kickSound = false;
        }
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != ModEnchantments.MLG_BUCKET.get();
    }
}
