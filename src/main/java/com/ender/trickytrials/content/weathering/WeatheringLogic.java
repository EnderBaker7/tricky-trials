package com.ender.trickytrials.content.weathering;

import com.ender.trickytrials.content.TTBlocks;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public interface WeatheringLogic {
    WeatheringStage getStage();

    enum WeatheringStage {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        OXIDIZED;

        public static final Map<Block, Block> STAGE_MAP = new HashMap<>();
        public static final Map<Block, WeatheringStage> STAGE_LOOKUP = new HashMap<>();

        public static Block getNext(Block current) {
            return STAGE_MAP.get(current);
        }

        public static void setupStageMap() {
            STAGE_MAP.put(TTBlocks.CHISELED_COPPER.get(), TTBlocks.EXPOSED_CHISELED_COPPER.get());
            STAGE_MAP.put(TTBlocks.EXPOSED_CHISELED_COPPER.get(), TTBlocks.WEATHERED_CHISELED_COPPER.get());
            STAGE_MAP.put(TTBlocks.WEATHERED_CHISELED_COPPER.get(), TTBlocks.OXIDIZED_CHISELED_COPPER.get());

            STAGE_MAP.put(TTBlocks.COPPER_BULB.get(), TTBlocks.EXPOSED_COPPER_BULB.get());
            STAGE_MAP.put(TTBlocks.EXPOSED_COPPER_BULB.get(), TTBlocks.WEATHERED_COPPER_BULB.get());
            STAGE_MAP.put(TTBlocks.WEATHERED_COPPER_BULB.get(), TTBlocks.OXIDIZED_COPPER_BULB.get());

            STAGE_MAP.put(TTBlocks.COPPER_GRATE.get(), TTBlocks.EXPOSED_COPPER_GRATE.get());
            STAGE_MAP.put(TTBlocks.EXPOSED_COPPER_GRATE.get(), TTBlocks.WEATHERED_COPPER_GRATE.get());
            STAGE_MAP.put(TTBlocks.WEATHERED_COPPER_GRATE.get(), TTBlocks.OXIDIZED_COPPER_GRATE.get());

            STAGE_MAP.put(TTBlocks.COPPER_DOOR.get(), TTBlocks.EXPOSED_COPPER_DOOR.get());
            STAGE_MAP.put(TTBlocks.EXPOSED_COPPER_DOOR.get(), TTBlocks.WEATHERED_COPPER_DOOR.get());
            STAGE_MAP.put(TTBlocks.WEATHERED_COPPER_DOOR.get(), TTBlocks.OXIDIZED_COPPER_DOOR.get());

            STAGE_LOOKUP.put(TTBlocks.CHISELED_COPPER.get(), UNAFFECTED);
            STAGE_LOOKUP.put(TTBlocks.EXPOSED_CHISELED_COPPER.get(), EXPOSED);
            STAGE_LOOKUP.put(TTBlocks.WEATHERED_CHISELED_COPPER.get(), WEATHERED);
            STAGE_LOOKUP.put(TTBlocks.OXIDIZED_CHISELED_COPPER.get(), OXIDIZED);

            STAGE_LOOKUP.put(TTBlocks.COPPER_BULB.get(), UNAFFECTED);
            STAGE_LOOKUP.put(TTBlocks.EXPOSED_COPPER_BULB.get(), EXPOSED);
            STAGE_LOOKUP.put(TTBlocks.WEATHERED_COPPER_BULB.get(), WEATHERED);
            STAGE_LOOKUP.put(TTBlocks.OXIDIZED_COPPER_BULB.get(), OXIDIZED);

            STAGE_LOOKUP.put(TTBlocks.COPPER_GRATE.get(), UNAFFECTED);
            STAGE_LOOKUP.put(TTBlocks.EXPOSED_COPPER_GRATE.get(), EXPOSED);
            STAGE_LOOKUP.put(TTBlocks.WEATHERED_COPPER_GRATE.get(), WEATHERED);
            STAGE_LOOKUP.put(TTBlocks.OXIDIZED_COPPER_GRATE.get(), OXIDIZED);
        }
    }
}
