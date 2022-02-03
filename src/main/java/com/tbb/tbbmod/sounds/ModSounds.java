package com.tbb.tbbmod.sounds;

import com.tbb.tbbmod.TBBMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TBBMod.MOD_ID);

    public static RegistryObject<SoundEvent> WOOD_CHANGER_FAIL = registerSoundEvents("wood_changer_fail");
    public static RegistryObject<SoundEvent> WOOD_CHANGER_SUCCESS = registerSoundEvents("wood_changer_success");
    public static RegistryObject<SoundEvent> WOOD_CHANGER_CRAFT = registerSoundEvents("wood_changer_craft");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(TBBMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> new SoundEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
