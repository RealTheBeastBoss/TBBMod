package com.tbb.tbbmod.painting;

import com.tbb.tbbmod.TBBMod;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOTIVES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, TBBMod.MOD_ID);

    public static final RegistryObject<Motive> BEASTBOSSANIUM_COLLECTION = PAINTING_MOTIVES.register("beastbossanium_collection",
            () -> new Motive(64, 64));
    public static final RegistryObject<Motive> BEASTBOSS_TREE_PAINTING = PAINTING_MOTIVES.register("beastboss_tree_painting",
            () -> new Motive(32, 64));

    public static void register(IEventBus eventBus) {
        PAINTING_MOTIVES.register(eventBus);
    }
}
