package com.tbb.tbbmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.tbb.tbbmod.block.ModBlocks;
import com.tbb.tbbmod.sounds.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WoodTypeChangerItem extends TieredItem {
    public boolean canShowMessage = true;
    private static final Map<Block, Block> WOOD_CHANGER_MAP = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS)
            .put(Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS)
            .put(Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS)
            .put(Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS)
            .put(Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS)
            .put(Blocks.DARK_OAK_PLANKS, Blocks.CRIMSON_PLANKS)
            .put(Blocks.CRIMSON_PLANKS, Blocks.WARPED_PLANKS)
            .put(Blocks.WARPED_PLANKS, ModBlocks.BEASTBOSS_PLANKS.get())
            .put(ModBlocks.BEASTBOSS_PLANKS.get(), Blocks.OAK_PLANKS)
            .put(Blocks.OAK_STAIRS, Blocks.SPRUCE_STAIRS)
            .put(Blocks.SPRUCE_STAIRS, Blocks.BIRCH_STAIRS)
            .put(Blocks.BIRCH_STAIRS, Blocks.JUNGLE_STAIRS)
            .put(Blocks.JUNGLE_STAIRS, Blocks.ACACIA_STAIRS)
            .put(Blocks.ACACIA_STAIRS, Blocks.DARK_OAK_STAIRS)
            .put(Blocks.DARK_OAK_STAIRS, Blocks.CRIMSON_STAIRS)
            .put(Blocks.CRIMSON_STAIRS, Blocks.WARPED_STAIRS)
            .put(Blocks.WARPED_STAIRS, ModBlocks.BEASTBOSS_STAIRS.get())
            .put(ModBlocks.BEASTBOSS_STAIRS.get(), Blocks.OAK_STAIRS)
            .put(Blocks.OAK_FENCE, Blocks.SPRUCE_FENCE)
            .put(Blocks.SPRUCE_FENCE, Blocks.BIRCH_FENCE)
            .put(Blocks.BIRCH_FENCE, Blocks.JUNGLE_FENCE)
            .put(Blocks.JUNGLE_FENCE, Blocks.ACACIA_FENCE)
            .put(Blocks.ACACIA_FENCE, Blocks.DARK_OAK_FENCE)
            .put(Blocks.DARK_OAK_FENCE, Blocks.CRIMSON_FENCE)
            .put(Blocks.CRIMSON_FENCE, Blocks.WARPED_FENCE)
            .put(Blocks.WARPED_FENCE, ModBlocks.BEASTBOSS_FENCE.get())
            .put(ModBlocks.BEASTBOSS_FENCE.get(), Blocks.OAK_FENCE)
            .put(Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE)
            .put(Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE)
            .put(Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE)
            .put(Blocks.JUNGLE_FENCE_GATE, Blocks.ACACIA_FENCE_GATE)
            .put(Blocks.ACACIA_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE)
            .put(Blocks.DARK_OAK_FENCE_GATE, Blocks.CRIMSON_FENCE_GATE)
            .put(Blocks.CRIMSON_FENCE_GATE, Blocks.WARPED_FENCE_GATE)
            .put(Blocks.WARPED_FENCE_GATE, ModBlocks.BEASTBOSS_FENCE_GATE.get())
            .put(ModBlocks.BEASTBOSS_FENCE_GATE.get(), Blocks.OAK_FENCE_GATE)
            .put(Blocks.OAK_SLAB, Blocks.SPRUCE_SLAB)
            .put(Blocks.SPRUCE_SLAB, Blocks.BIRCH_SLAB)
            .put(Blocks.BIRCH_SLAB, Blocks.JUNGLE_SLAB)
            .put(Blocks.JUNGLE_SLAB, Blocks.ACACIA_SLAB)
            .put(Blocks.ACACIA_SLAB, Blocks.DARK_OAK_SLAB)
            .put(Blocks.DARK_OAK_SLAB, Blocks.CRIMSON_SLAB)
            .put(Blocks.CRIMSON_SLAB, Blocks.WARPED_SLAB)
            .put(Blocks.WARPED_SLAB, ModBlocks.BEASTBOSS_SLAB.get())
            .put(ModBlocks.BEASTBOSS_SLAB.get(), Blocks.OAK_SLAB)
            .put(Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON)
            .put(Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON)
            .put(Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON)
            .put(Blocks.JUNGLE_BUTTON, Blocks.ACACIA_BUTTON)
            .put(Blocks.ACACIA_BUTTON, Blocks.DARK_OAK_BUTTON)
            .put(Blocks.DARK_OAK_BUTTON, Blocks.CRIMSON_BUTTON)
            .put(Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON)
            .put(Blocks.WARPED_BUTTON, ModBlocks.BEASTBOSS_BUTTON.get())
            .put(ModBlocks.BEASTBOSS_BUTTON.get(), Blocks.OAK_BUTTON)
            .put(Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE)
            .put(Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE)
            .put(Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE)
            .put(Blocks.JUNGLE_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE)
            .put(Blocks.ACACIA_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE)
            .put(Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.CRIMSON_PRESSURE_PLATE)
            .put(Blocks.CRIMSON_PRESSURE_PLATE, Blocks.WARPED_PRESSURE_PLATE)
            .put(Blocks.WARPED_PRESSURE_PLATE, ModBlocks.BEASTBOSS_PRESSURE_PLATE.get())
            .put(ModBlocks.BEASTBOSS_PRESSURE_PLATE.get(), Blocks.OAK_PRESSURE_PLATE)
            .put(Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR)
            .put(Blocks.SPRUCE_TRAPDOOR, Blocks.BIRCH_TRAPDOOR)
            .put(Blocks.BIRCH_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR)
            .put(Blocks.JUNGLE_TRAPDOOR, Blocks.ACACIA_TRAPDOOR)
            .put(Blocks.ACACIA_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR)
            .put(Blocks.DARK_OAK_TRAPDOOR, Blocks.CRIMSON_TRAPDOOR)
            .put(Blocks.CRIMSON_TRAPDOOR, Blocks.WARPED_TRAPDOOR)
            .put(Blocks.WARPED_TRAPDOOR, ModBlocks.BEASTBOSS_TRAPDOOR.get())
            .put(ModBlocks.BEASTBOSS_TRAPDOOR.get(), Blocks.OAK_TRAPDOOR)
            .put(Blocks.OAK_LOG, Blocks.SPRUCE_LOG)
            .put(Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG)
            .put(Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG)
            .put(Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG)
            .put(Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG)
            .put(Blocks.DARK_OAK_LOG, Blocks.CRIMSON_STEM)
            .put(Blocks.CRIMSON_STEM, Blocks.WARPED_STEM)
            .put(Blocks.WARPED_STEM, ModBlocks.BEASTBOSS_LOG.get())
            .put(ModBlocks.BEASTBOSS_LOG.get(), Blocks.OAK_LOG)
            .put(Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_SPRUCE_LOG)
            .put(Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_BIRCH_LOG)
            .put(Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_JUNGLE_LOG)
            .put(Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_ACACIA_LOG)
            .put(Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
            .put(Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_CRIMSON_STEM)
            .put(Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_WARPED_STEM)
            .put(Blocks.STRIPPED_WARPED_STEM, ModBlocks.STRIPPED_BEASTBOSS_LOG.get())
            .put(ModBlocks.STRIPPED_BEASTBOSS_LOG.get(), Blocks.STRIPPED_OAK_LOG)
            .put(Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD)
            .put(Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD)
            .put(Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD)
            .put(Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD)
            .put(Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD)
            .put(Blocks.DARK_OAK_WOOD, Blocks.CRIMSON_HYPHAE)
            .put(Blocks.CRIMSON_HYPHAE, Blocks.WARPED_HYPHAE)
            .put(Blocks.WARPED_HYPHAE, ModBlocks.BEASTBOSS_WOOD.get())
            .put(ModBlocks.BEASTBOSS_WOOD.get(), Blocks.OAK_WOOD)
            .put(Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
            .put(Blocks.STRIPPED_SPRUCE_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
            .put(Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
            .put(Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
            .put(Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
            .put(Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_CRIMSON_HYPHAE)
            .put(Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
            .put(Blocks.STRIPPED_WARPED_HYPHAE, ModBlocks.STRIPPED_BEASTBOSS_WOOD.get())
            .put(ModBlocks.STRIPPED_BEASTBOSS_WOOD.get(), Blocks.STRIPPED_OAK_WOOD)
            .build();

    public WoodTypeChangerItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
            if (canChangeWood(blockClicked)) {
                Block newBlock = WOOD_CHANGER_MAP.get(blockClicked);
                canShowMessage = true;
                player.addTag("woodchanged");
                level.destroyBlock(positionClicked, false);
                BlockPlaceContext newContext = new BlockPlaceContext(pContext);
                level.setBlock(positionClicked, newBlock.getStateForPlacement(newContext), 2);
                MutableComponent mutableComponent = new TextComponent(player.getDisplayName().getString() + " ");
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                mutableComponent.append(new TranslatableComponent(blockClicked.getDescriptionId()));
                mutableComponent.append(new TextComponent(" --> "));
                mutableComponent.append(new TranslatableComponent(newBlock.getDescriptionId()));
                mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                player.sendMessage(mutableComponent, player.getUUID());
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                level.playSound(player, positionClicked, ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.BLOCKS, 0.5f, 1f);
            } else {
                MutableComponent mutableComponent = new TextComponent(player.getDisplayName().getString() + ": ");
                mutableComponent.append(new TranslatableComponent(blockClicked.getDescriptionId()));
                mutableComponent.append(new TextComponent(" "));
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".fail_message"));
                mutableComponent.withStyle(ChatFormatting.LIGHT_PURPLE);
                mutableComponent.withStyle(ChatFormatting.UNDERLINE);
                player.sendMessage(mutableComponent, player.getUUID());
                level.playSound(player, positionClicked, ModSounds.WOOD_CHANGER_FAIL.get(), SoundSource.BLOCKS, 0.25f, 1f);
            }
        } else {
            if (!canChangeWood(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock())) {
                pContext.getLevel().playSound(player, pContext.getClickedPos(), ModSounds.WOOD_CHANGER_FAIL.get(), SoundSource.BLOCKS, 0.25f,1f);
            } else {
                pContext.getLevel().playSound(player, pContext.getClickedPos(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.BLOCKS, 0.5f,1f);
            }
        }
        return super.useOn(pContext);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pPlayer.getLevel().isClientSide()) {
            if (pInteractionTarget instanceof Cow) {
                pPlayer.addTag("cowchanged");
                if (!pInteractionTarget.isInvulnerable()) {
                    pInteractionTarget.setInvulnerable(true);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Cow --> Unkillable"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                } else {
                    pInteractionTarget.setInvulnerable(false);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Cow --> Killable"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                }
            } else if (pInteractionTarget instanceof Pig) {
                pPlayer.addTag("pigchanged");
                if (!pInteractionTarget.isNoGravity()) {
                    pInteractionTarget.setNoGravity(true);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Pig --> No Gravity"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                } else {
                    pInteractionTarget.setNoGravity(false);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Pig --> Has Gravity"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                }
            } else if (pInteractionTarget instanceof Sheep) {
                pPlayer.addTag("sheepchanged");
                Sheep sheep = ((Sheep) pInteractionTarget);
                sheep.setColor(getDye());
                pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                mutableComponent.append(new TextComponent("Sheep --> " + getColorString(sheep.getColor()) + " Wool"));
                mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
            } else if (pInteractionTarget instanceof Axolotl) {
                pPlayer.addTag("axolotlchanged");
                Axolotl axolotl = ((Axolotl) pInteractionTarget);
                if (!axolotl.isNoAi()) {
                    axolotl.setNoAi(true);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Axolotl --> No AI"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                } else {
                    axolotl.setNoAi(false);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Axolotl --> Has AI"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                }
            } else if (pInteractionTarget instanceof Bat) {
                pPlayer.addTag("batchanged");
                if (pInteractionTarget.getAbsorptionAmount() <= 0f) {
                    pInteractionTarget.setAbsorptionAmount(34f);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Bat --> 40HP"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                } else {
                    pInteractionTarget.setAbsorptionAmount(0f);
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent("Bat --> 6HP"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                }
            } else if (pInteractionTarget instanceof Zombie) {
                pPlayer.addTag("zombiechanged");
                String name = giveName();
                pInteractionTarget.setCustomName(new TextComponent(name));
                pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                mutableComponent.append(new TextComponent("Zombie --> " + name));
                mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
            } else if (pInteractionTarget instanceof Skeleton) {
                pPlayer.addTag("skeletonchanged");
                pInteractionTarget.remove(Entity.RemovalReason.DISCARDED);
                pPlayer.giveExperienceLevels(1);
                pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                mutableComponent.append(new TextComponent("Skeleton --> XP"));
                mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
            } else if (pInteractionTarget instanceof Player) {
                Player player = ((Player) pInteractionTarget);
                if (new Random().nextFloat() >= 0.9f) {
                    ItemStack item = player.getItemInHand(player.getUsedItemHand());
                    player.drop(item, false, true);
                    player.setItemInHand(player.getUsedItemHand(), ItemStack.EMPTY);
                    pPlayer.addTag("playerchanged");
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                    MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
                    mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".use_message"));
                    mutableComponent.append(new TextComponent(player.getDisplayName().getString() + " --> Expelliarmus"));
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                } else {
                    MutableComponent mutableComponent = new TextComponent(player.getDisplayName().getString() + " used Protego");
                    mutableComponent.withStyle(ChatFormatting.DARK_AQUA);
                    pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
                    pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
                    pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
                }
            }
        } else {
            if (pInteractionTarget instanceof Cow ||
                    pInteractionTarget instanceof Pig ||
                    pInteractionTarget instanceof Sheep ||
                    pInteractionTarget instanceof Axolotl ||
                    pInteractionTarget instanceof Bat ||
                    pInteractionTarget instanceof Zombie ||
                    pInteractionTarget instanceof Skeleton ||
                    pInteractionTarget instanceof Player) {
                pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.NEUTRAL, 0.5f, 1f);
            } else {
                pPlayer.getLevel().playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_FAIL.get(), SoundSource.NEUTRAL, 0.25f, 1f);
            }
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

    public String giveName() {
        String name = "TheBeastBoss";
        switch (new Random().nextInt(1, 24)) {
            case 1:
                break;
            case 2:
                name = "CaptainThatGuy";
                break;
            case 3:
                name = "YellowBeastBoss";
                break;
            case 4:
                name = "BlazeBeastBoss";
                break;
            case 5:
                name = "CatBeastBoss";
                break;
            case 6:
                name = "CaptainThatGirl";
                break;
            case 7:
                name = "DanTBB";
                break;
            case 8:
                name = "DarkBeastBoss";
                break;
            case 9:
                name = "De-Saturated BeastBoss";
                break;
            case 10:
                name = "DiverBeastBoss";
                break;
            case 11:
                name = "EnderBeastBoss";
                break;
            case 12:
                name = "Holy Music Stops";
                break;
            case 13:
                name = "KingBeastBoss";
                break;
            case 14:
                name = "MeanBeastBoss";
                break;
            case 15:
                name = "PhantomBB";
                break;
            case 16:
                name = "PhilzaBeastBoss";
                break;
            case 17:
                name = "PinkBeastBoss";
                break;
            case 18:
                name = "Prankster Pinkie";
                break;
            case 19:
                name = "Red Riding Hoodie";
                break;
            case 20:
                name = "RedBeastBoss";
                break;
            case 21:
                name = "ShulkBB";
                break;
            case 22:
                name = "SpiderBeastBoss";
                break;
            case 23:
                name = "TechnoBeastBoss";
                break;
        }
        return name;
    }

    public String getColorString(DyeColor dyeColor) {
        String color = "White";
        switch (dyeColor) {
            case WHITE:
                break;
            case ORANGE:
                color = "Orange";
                break;
            case LIGHT_BLUE:
                color = "Light Blue";
                break;
            case MAGENTA:
                color = "Magenta";
                break;
            case YELLOW:
                color = "Yellow";
                break;
            case LIME:
                color = "Lime";
                break;
            case PINK:
                color = "Pink";
                break;
            case GRAY:
                color = "Gray";
                break;
            case LIGHT_GRAY:
                color = "Light Gray";
                break;
            case CYAN:
                color = "Cyan";
                break;
            case PURPLE:
                color = "Purple";
                break;
            case BLUE:
                color = "Blue";
                break;
            case BROWN:
                color = "Brown";
                break;
            case BLACK:
                color = "Black";
                break;
            case GREEN:
                color = "Green";
                break;
            case RED:
                color = "Red";
                break;
        }
        return color;
    }

    public DyeColor getDye() {
        DyeColor color = DyeColor.WHITE;
        switch (new Random().nextInt(1, 17)) {
            case 1:
                break;
            case 2:
                color = DyeColor.ORANGE;
                break;
            case 3:
                color = DyeColor.LIGHT_BLUE;
                break;
            case 4:
                color = DyeColor.YELLOW;
                break;
            case 5:
                color = DyeColor.LIME;
                break;
            case 6:
                color = DyeColor.PINK;
                break;
            case 7:
                color = DyeColor.GRAY;
                break;
            case 8:
                color = DyeColor.LIGHT_GRAY;
                break;
            case 9:
                color = DyeColor.CYAN;
                break;
            case 10:
                color = DyeColor.PURPLE;
                break;
            case 11:
                color = DyeColor.BLUE;
                break;
            case 12:
                color = DyeColor.BROWN;
                break;
            case 13:
                color = DyeColor.GREEN;
                break;
            case 14:
                color = DyeColor.RED;
                break;
            case 15:
                color = DyeColor.BLACK;
                break;
            case 16:
                color = DyeColor.MAGENTA;
                break;
        }
        return color;
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        if (canShowMessage) {
            MutableComponent mutableComponent = new TextComponent(pPlayer.getDisplayName().getString() + " ");
            mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".craft_message"));
            mutableComponent.withStyle(ChatFormatting.DARK_GREEN);
            mutableComponent.withStyle(ChatFormatting.BOLD);
            pPlayer.sendMessage(mutableComponent, pPlayer.getUUID());
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), ModSounds.WOOD_CHANGER_CRAFT.get(), SoundSource.BLOCKS, 1f,1f);
            canShowMessage = false;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.wood_changer.shift"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.not_shift"));
        }
    }

    private boolean canChangeWood(Block block) {
        return WOOD_CHANGER_MAP.containsKey(block);
    }
}
