package com.tbb.tbbmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TBB_TAB = new CreativeModeTab("tbbtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BEASTBOSSANIUM_INGOT.get());
        }
    };
}
