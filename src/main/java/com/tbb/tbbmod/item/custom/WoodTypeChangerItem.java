package com.tbb.tbbmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.tbb.tbbmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class WoodTypeChangerItem extends Item {
    public boolean canShowCraftText = true;
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
            .build();

    public WoodTypeChangerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        this.canShowCraftText = true;
        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
            if (canChangeWood(blockClicked)) {
                Block newBlock = WOOD_CHANGER_MAP.get(blockClicked);
                level.destroyBlock(positionClicked, false);
                BlockPlaceContext newContext = new BlockPlaceContext(pContext);
                level.setBlock(positionClicked, newBlock.getStateForPlacement(newContext), 2);
                player.sendMessage(new TextComponent(player.getDisplayName().getString() + " used the Wood Changer"), player.getUUID());
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), (user) -> user.broadcastBreakEvent(user.getUsedItemHand()));
            } else {
                player.sendMessage(new TextComponent(player.getDisplayName().getString() + ": This block is not changeable."), player.getUUID());
            }
        }
        return super.useOn(pContext);
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        if (this.canShowCraftText) {
            pPlayer.sendMessage(new TextComponent(pPlayer.getDisplayName().getString() + " has crafted a Wood Changer, use this on a wood block to change its type."), pPlayer.getUUID());
            this.canShowCraftText = false;
        }
    }

    private boolean canChangeWood(Block block) {
        return WOOD_CHANGER_MAP.containsKey(block);
    }
}
