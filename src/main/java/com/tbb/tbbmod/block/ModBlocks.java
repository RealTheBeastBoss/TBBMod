package com.tbb.tbbmod.block;

import com.tbb.tbbmod.TBBMod;
import com.tbb.tbbmod.item.ModCreativeModeTab;
import com.tbb.tbbmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TBBMod.MOD_ID);

    public static final RegistryObject<Block> BEASTBOSSANIUM_BLOCK = registerBlock("beastbossanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(6.5f, 7f).sound(SoundType.METAL)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> RAW_BEASTBOSSANIUM_BLOCK = registerBlock("raw_beastbossanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(6f, 7f).sound(SoundType.METAL)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> BEASTBOSSANIUM_ORE = registerBlock("beastbossanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(4f).sound(SoundType.STONE)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> DEEPSLATE_BEASTBOSSANIUM_ORE = registerBlock("deepslate_beastbossanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(5.5f, 4f).sound(SoundType.DEEPSLATE)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> BEASTBOSS_PLANKS = registerBlock("beastboss_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS).color(MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(4f)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> BEASTBOSS_STAIRS = registerBlock("beastboss_stairs", () -> new StairBlock(() -> BEASTBOSS_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.BIRCH_STAIRS).color(MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(4f)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> BEASTBOSS_FENCE = registerBlock("beastboss_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_FENCE).color(MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(4f)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> BEASTBOSS_FENCE_GATE = registerBlock("beastboss_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_FENCE_GATE).color(MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(4f)), ModCreativeModeTab.TBB_TAB);
    public static final RegistryObject<Block> BEASTBOSS_SLAB = registerBlock("beastboss_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_SLAB).color(MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(4f)), ModCreativeModeTab.TBB_TAB);

    public static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    public static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
