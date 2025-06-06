package com.ender.trickytrials.content;

import com.ender.trickytrials.TrickyTrials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TTSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, TrickyTrials.MODID);

    public static ResourceLocation modLoc(String pPath) {
        return new ResourceLocation(TrickyTrials.MODID, pPath);
    }

    private static RegistryObject<SoundEvent> registerSoundEvent(String pPath) {
        return SOUND_EVENTS.register(pPath, () -> SoundEvent.createVariableRangeEvent(modLoc(pPath)));
    }

    public static final RegistryObject<SoundEvent> COPPER_BULB_BREAK =
            registerSoundEvent("block.copper_bulb.break");
    public static final RegistryObject<SoundEvent> COPPER_BULB_PLACE =
            registerSoundEvent("block.copper_bulb.place");
    public static final RegistryObject<SoundEvent> COPPER_BULB_HIT =
            registerSoundEvent("block.copper_bulb.hit");
    public static final RegistryObject<SoundEvent> COPPER_BULB_FALL =
            registerSoundEvent("block.copper_bulb.fall");
    public static final RegistryObject<SoundEvent> COPPER_BULB_STEP =
            registerSoundEvent("block.copper_bulb.step");

    public static final RegistryObject<SoundEvent> COPPER_BULB_CLICK =
            registerSoundEvent("block.copper_bulb.click");

    public static final Supplier<SoundType> COPPER_BULB = () -> new SoundType(1.0f, 1.0f, COPPER_BULB_BREAK.get(), COPPER_BULB_STEP.get(),
            COPPER_BULB_PLACE.get(), COPPER_BULB_HIT.get(), COPPER_BULB_FALL.get());

    public static final RegistryObject<SoundEvent> COPPER_GRATE_BREAK =
            registerSoundEvent("block.copper_grate.break");
    public static final RegistryObject<SoundEvent> COPPER_GRATE_PLACE =
            registerSoundEvent("block.copper_grate.place");
    public static final RegistryObject<SoundEvent> COPPER_GRATE_HIT =
            registerSoundEvent("block.copper_grate.hit");
    public static final RegistryObject<SoundEvent> COPPER_GRATE_FALL =
            registerSoundEvent("block.copper_grate.fall");
    private static final RegistryObject<SoundEvent> COPPER_GRATE_STEP =
            registerSoundEvent("block.copper_grate.step");

    public static final Supplier<SoundType> COPPER_GRATE = () -> new SoundType(1.0f, 1.0f, COPPER_GRATE_BREAK.get(), COPPER_GRATE_STEP.get(),
            COPPER_GRATE_PLACE.get(), COPPER_GRATE_HIT.get(), COPPER_GRATE_FALL.get());

    public static final RegistryObject<SoundEvent> COPPER_DOOR_OPEN = registerSoundEvent("block.copper_door.open");
    public static final RegistryObject<SoundEvent> COPPER_DOOR_CLOSE = registerSoundEvent("block.copper_door.close");

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
