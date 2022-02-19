package com.tbb.tbbmod.enchantment;

import com.tbb.tbbmod.sounds.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MLGBucketEnchantment extends Enchantment {

    protected MLGBucketEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public static boolean mlgLand = false;

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END || !event.player.level.isClientSide()) return;

        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.MLG_BUCKET.get(), event.player);

        if (level > 0 && mlgLand) {
            event.player.getLevel().playSound(event.player, event.player.blockPosition(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.BLOCKS, 1f, 1f);
            mlgLand = false;
        }
    }

    @SubscribeEvent
    public static void onFall(LivingDamageEvent source) {
        if (!source.getEntity().getLevel().isClientSide()) {
            if (source.getSource().isFall() && !source.getEntity().getLevel().dimension().equals(Level.NETHER)) {
                if (source.getEntity() instanceof Player) {
                    Player player = ((Player) source.getEntity());
                    int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.MLG_BUCKET.get(), player);
                    if (level > 0) {
                        float chance = 0f;
                        switch (level) {
                            case 1:
                                chance = 0.95f; // 5% chance (1 in 20)
                                break;
                            case 2:
                                chance = 0.9f; // 10% chance (1 in 10)
                                break;
                            case 3:
                                chance = 0.8571f; // 14.29% chance (1 in 7)
                                break;
                            case 4:
                                chance = 0.75f; // 25% chance (1 in 4)
                                break;
                            case 5:
                                chance = 0.5f; // 50% chance (1 in 2)
                                break;
                            case 6:
                                chance = 0.25f; // 75% chance (1 in 1.25)
                                break;
                            default:
                                chance = 0.2f; // 80% chance (1 in 1.2) This is in case the enchant level gets increases to 7 by a mod
                                break;
                        }
                        if (new Random().nextFloat() >= chance) {
                            source.setCanceled(true);
                            MutableComponent mutableComponent = new TextComponent(player.getDisplayName().getString() + " ");
                            mutableComponent.append(new TranslatableComponent("enchantment.tbbmod.mlg_bucket.use_message"));
                            mutableComponent.withStyle(ChatFormatting.AQUA);
                            player.sendMessage(mutableComponent, player.getUUID());
                            player.getLevel().playSound(player, player.blockPosition(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.BLOCKS, 1f, 1f);
                            mlgLand = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.FROST_WALKER && pOther != ModEnchantments.BEASTBOSS_KICK.get();
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }
}
