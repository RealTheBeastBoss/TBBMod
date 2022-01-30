package com.tbb.tbbmod.item;

import com.tbb.tbbmod.TBBMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TBBMod.MOD_ID);

    public static final RegistryObject<Item> BEASTBOSSANIUM_INGOT = ITEMS.register("beastbossanium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));
    public static final RegistryObject<Item> RAW_BEASTBOSSANIUM_CHUNK = ITEMS.register("raw_beastbossanium_chunk", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TBB_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
