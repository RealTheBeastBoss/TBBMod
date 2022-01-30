package com.tbb.tbbmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.tbb.tbbmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
                level.setBlock(positionClicked, newBlock.defaultBlockState(), 2);
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
