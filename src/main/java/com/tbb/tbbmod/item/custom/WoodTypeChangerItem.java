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
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

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
            } else {
                MutableComponent mutableComponent = new TextComponent(player.getDisplayName().getString() + ": ");
                mutableComponent.append(new TranslatableComponent(blockClicked.getDescriptionId()));
                mutableComponent.append(new TextComponent(" "));
                mutableComponent.append(new TranslatableComponent(this.getDescriptionId() + ".fail_message"));
                mutableComponent.withStyle(ChatFormatting.LIGHT_PURPLE);
                mutableComponent.withStyle(ChatFormatting.UNDERLINE);
            }
        } else {
            if (!canChangeWood(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock())) {
                pContext.getLevel().playSound(player, pContext.getClickedPos(), ModSounds.WOOD_CHANGER_FAIL.get(), SoundSource.BLOCKS, 1f,1f);
            } else {
                pContext.getLevel().playSound(player, pContext.getClickedPos(), ModSounds.WOOD_CHANGER_SUCCESS.get(), SoundSource.BLOCKS, 0.5f,1f);
            }
        }
        return super.useOn(pContext);
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
            pTooltipComponents.add(new TranslatableComponent("tooltip.tbbmod.wood_changer"));
        }
    }

    private boolean canChangeWood(Block block) {
        return WOOD_CHANGER_MAP.containsKey(block);
    }
}
