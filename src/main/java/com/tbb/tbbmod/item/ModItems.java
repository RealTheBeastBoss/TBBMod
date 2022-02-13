package com.tbb.tbbmod.item;

import com.tbb.tbbmod.TBBMod;
import com.tbb.tbbmod.block.ModBlocks;
import com.tbb.tbbmod.item.custom.ModArmorItem;
import com.tbb.tbbmod.item.custom.ModFoods;
import com.tbb.tbbmod.item.custom.StarbiomaticEnergyBallItem;
import com.tbb.tbbmod.item.custom.WoodTypeChangerItem;
import com.tbb.tbbmod.sounds.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TBBMod.MOD_ID);

    public static final RegistryObject<Item> BEASTBOSSANIUM_INGOT = ITEMS.register("beastbossanium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> RAW_BEASTBOSSANIUM_CHUNK = ITEMS.register("raw_beastbossanium_chunk", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> WOOD_CHANGER = ITEMS.register("wood_changer", () -> new WoodTypeChangerItem(ModTiers.WOOD_CHANGER, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_SWORD = ITEMS.register("beastbossanium_sword", () -> new SwordItem(ModTiers.BEASTBOSSANIUM, 4, -2.3f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_PICKAXE = ITEMS.register("beastbossanium_pickaxe", () -> new PickaxeItem(ModTiers.BEASTBOSSANIUM, 1, -2.8f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_AXE = ITEMS.register("beastbossanium_axe", () -> new AxeItem(ModTiers.BEASTBOSSANIUM, 6, -2.9f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_SHOVEL = ITEMS.register("beastbossanium_shovel", () -> new ShovelItem(ModTiers.BEASTBOSSANIUM, 3, -3f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_HOE = ITEMS.register("beastbossanium_hoe", () -> new HoeItem(ModTiers.BEASTBOSSANIUM, 3, -2f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_BOOTS = ITEMS.register("beastbossanium_boots", () -> new ModArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_LEGGINGS = ITEMS.register("beastbossanium_leggings", () -> new ArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_CHESTPLATE = ITEMS.register("beastbossanium_chestplate", () -> new ArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_HELMET = ITEMS.register("beastbossanium_helmet", () -> new ArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSS_BANANA = ITEMS.register("beastboss_banana", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).food(ModFoods.BEASTBOSS_BANANA)));
    public static final RegistryObject<Item> BEASTBOSSANIUM_CARROT = ITEMS.register("beastbossanium_carrot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).food(ModFoods.BEASTBOSSANIUM_CARROT).fireResistant()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            if (Screen.hasShiftDown()) {
                pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.beastbossanium_carrot.shift"));
            } else {
                pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.not_shift"));
            }
        }
    });
    public static final RegistryObject<Item> STARBIOMATIC_ENERGY_BALL = ITEMS.register("starbiomatic_energy_ball", () -> new StarbiomaticEnergyBallItem(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> BEASTBOSS_SIGN_ITEM = ITEMS.register("beastboss_sign",
            () -> new SignItem(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).stacksTo(16),
                    ModBlocks.BEASTBOSS_SIGN.get(), ModBlocks.BEASTBOSS_WALL_SIGN.get()));
    public static final RegistryObject<Item> STRONG_WOOD_CHANGER = ITEMS.register("strong_wood_changer", () -> new WoodTypeChangerItem(ModTiers.WOOD_CHANGER, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            if (Screen.hasShiftDown()) {
                pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.wood_changer.shift"));
            } else {
                pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.not_shift"));
            }
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }

        @Override
        public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_SWORD = ITEMS.register("strong_beastbossanium_sword", () -> new SwordItem(ModTiers.BEASTBOSSANIUM, 4, -2.3f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_PICKAXE = ITEMS.register("strong_beastbossanium_pickaxe", () -> new PickaxeItem(ModTiers.BEASTBOSSANIUM, 1, -2.8f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_AXE = ITEMS.register("strong_beastbossanium_axe", () -> new AxeItem(ModTiers.BEASTBOSSANIUM, 6, -2.9f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_SHOVEL = ITEMS.register("strong_beastbossanium_shovel", () -> new ShovelItem(ModTiers.BEASTBOSSANIUM, 3, -3f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_HOE = ITEMS.register("strong_beastbossanium_hoe", () -> new HoeItem(ModTiers.BEASTBOSSANIUM, 3, -2f, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_BOOTS = ITEMS.register("strong_beastbossanium_boots", () -> new ModArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_LEGGINGS = ITEMS.register("strong_beastbossanium_leggings", () -> new ArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_CHESTPLATE = ITEMS.register("strong_beastbossanium_chestplate", () -> new ArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });
    public static final RegistryObject<Item> STRONG_BEASTBOSSANIUM_HELMET = ITEMS.register("strong_beastbossanium_helmet", () -> new ArmorItem(ModArmorMaterial.BEASTBOSSANIUM, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.TBB_TAB).fireResistant()){
        @Override
        public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
            return !pDamageSource.isFire() && !pDamageSource.isExplosion() && !pDamageSource.equals(DamageSource.CACTUS) && !pDamageSource.equals(DamageSource.LIGHTNING_BOLT);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.strong"));
        }
    });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
