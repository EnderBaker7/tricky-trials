package com.ender.trickytrials.content.weathering;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public enum WeatheringStage {
    FINE,
    EXPO,
    WEATHER,
    OXI;

    public static final Map<WeatheringCopperBlock, Block> STAGE_MAP = new HashMap<>();

    public static Block getNext(WeatheringCopperBlock current) {
        return STAGE_MAP.get(current);
    }
}
