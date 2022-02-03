package com.tbb.tbbmod;

import com.google.common.collect.ImmutableMap;
import com.tbb.tbbmod.block.ModBlocks;
import com.tbb.tbbmod.enchantment.ModEnchantments;
import com.tbb.tbbmod.item.ModItems;
import com.tbb.tbbmod.sounds.ModSounds;
import com.tbb.tbbmod.world.gen.Ores;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TBBMod.MOD_ID)
public class TBBMod
{
    public static final String MOD_ID = "tbbmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public TBBMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEnchantments.register(eventBus);
        ModSounds.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::setupClient);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BEASTBOSS_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BEASTBOSS_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BEASTBOSS_FLOWER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_BEASTBOSS_FLOWER.get(), RenderType.cutout());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(ModBlocks.BEASTBOSS_LOG.get(), ModBlocks.STRIPPED_BEASTBOSS_LOG.get())
                    .put(ModBlocks.BEASTBOSS_WOOD.get(), ModBlocks.STRIPPED_BEASTBOSS_WOOD.get())
                    .build();
            Ores.registerConfiguredFeatures();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BEASTBOSS_FLOWER.getId(), ModBlocks.POTTED_BEASTBOSS_FLOWER);
        });
    }
}
