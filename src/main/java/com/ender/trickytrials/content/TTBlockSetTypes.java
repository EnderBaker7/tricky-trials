package com.ender.trickytrials.content;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class TTBlockSetTypes {
    public static final BlockSetType COPPER = new BlockSetType(
            "copper",
            true,
            SoundType.COPPER,
            TTSoundEvents.COPPER_DOOR_OPEN.get(),
            TTSoundEvents.COPPER_DOOR_CLOSE.get(),
            TTSoundEvents.COPPER_TRAPDOOR_OPEN.get(),
            TTSoundEvents.COPPER_TRAPDOOR_CLOSE.get(),
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF
    );
}
