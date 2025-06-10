package com.ender.trickytrials.content;

import com.ender.trickytrials.TrickyTrials;
import com.ender.trickytrials.content.weathering.*;
import com.ender.trickytrials.content.weathering.logic.WeatheringCopperBlock;
import com.ender.trickytrials.content.weathering.logic.WeatheringLogic;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, TrickyTrials.MODID);

    public static RegistryObject<Block> registerWeatheringBlocks(String name, WeatheringLogic.WeatheringStage stage, Block copyProperty) {
        return BLOCKS.register(name, () -> new WeatheringCopperBlock(stage, BlockBehaviour.Properties.copy(copyProperty)));
    }
    public static RegistryObject<Block> registerBlock(String name, Block copyProperty) {
        return BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.copy(copyProperty)));
    }

    public static RegistryObject<Block> registerBulbBlock(String name, WeatheringLogic.WeatheringStage pState, int lightLev, MapColor color) {
        return BLOCKS.register(name, () -> new CopperBulbBlock(pState, copyBulbProperties(COPPER_BULB)
                .lightLevel(state -> state.getValue(CopperBulbBlock.LIT) ? lightLev : 0)
                .mapColor(color)));
    }

    private static BlockBehaviour.Properties copyBulbProperties(RegistryObject<Block> ref) {
        return BlockBehaviour.Properties.copy(ref.get());
    }
    public static final RegistryObject<Block> CHISELED_COPPER = registerWeatheringBlocks("chiseled_copper",
            WeatheringLogic.WeatheringStage.UNAFFECTED, Blocks.COPPER_BLOCK);
    public static final RegistryObject<Block> EXPOSED_CHISELED_COPPER = registerWeatheringBlocks("exposed_chiseled_copper",
            WeatheringLogic.WeatheringStage.EXPOSED, Blocks.EXPOSED_COPPER);
    public static final RegistryObject<Block> WEATHERED_CHISELED_COPPER = registerWeatheringBlocks("weathered_chiseled_copper",
            WeatheringLogic.WeatheringStage.WEATHERED, Blocks.WEATHERED_COPPER);
    public static final RegistryObject<Block> OXIDIZED_CHISELED_COPPER = registerWeatheringBlocks("oxidized_chiseled_copper",
            WeatheringLogic.WeatheringStage.OXIDIZED, Blocks.OXIDIZED_COPPER);
    public static final RegistryObject<Block> WAXED_CHISELED_COPPER = registerBlock("waxed_chiseled_copper", Blocks.WAXED_COPPER_BLOCK);
    public static final RegistryObject<Block> WAXED_EXPOSED_CHISELED_COPPER = registerBlock("waxed_exposed_chiseled_copper", Blocks.WAXED_EXPOSED_COPPER);
    public static final RegistryObject<Block> WAXED_WEATHERED_CHISELED_COPPER = registerBlock("waxed_weathered_chiseled_copper", Blocks.WAXED_WEATHERED_COPPER);
    public static final RegistryObject<Block> WAXED_OXIDIZED_CHISELED_COPPER = registerBlock("waxed_oxidized_chiseled_copper", Blocks.WAXED_OXIDIZED_COPPER);

    public static final RegistryObject<Block> COPPER_BULB = BLOCKS.register("copper_bulb",
            () -> new CopperBulbBlock(WeatheringLogic.WeatheringStage.UNAFFECTED, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(CopperBulbBlock.LIT) ? 15 : 0).sound(TTSoundEvents.COPPER_BULB.get())));
    public static final RegistryObject<Block> EXPOSED_COPPER_BULB = registerBulbBlock("exposed_copper_bulb", WeatheringLogic.WeatheringStage.EXPOSED,
            12, MapColor.TERRACOTTA_LIGHT_GRAY);
    public static final RegistryObject<Block> WEATHERED_COPPER_BULB = registerBulbBlock("weathered_copper_bulb", WeatheringLogic.WeatheringStage.WEATHERED,
            8, MapColor.WARPED_STEM);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BULB = registerBulbBlock("oxidized_copper_bulb", WeatheringLogic.WeatheringStage.OXIDIZED,
            4, MapColor.WARPED_NYLIUM);

    public static final RegistryObject<Block> WAXED_COPPER_BULB = BLOCKS.register("waxed_copper_bulb",
            () -> new BaseCopperBulbBlock(copyBulbProperties(COPPER_BULB)));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BULB = BLOCKS.register("waxed_exposed_copper_bulb",
            () -> new BaseCopperBulbBlock(copyBulbProperties(EXPOSED_COPPER_BULB)));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BULB = BLOCKS.register("waxed_weathered_copper_bulb",
            () -> new BaseCopperBulbBlock(copyBulbProperties(WEATHERED_COPPER_BULB)));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BULB = BLOCKS.register("waxed_oxidized_copper_bulb",
            () -> new BaseCopperBulbBlock(copyBulbProperties(OXIDIZED_COPPER_BULB)));

    public static final RegistryObject<Block> COPPER_GRATE = BLOCKS.register("copper_grate",
            () -> new CopperGrateBlock(WeatheringLogic.WeatheringStage.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> EXPOSED_COPPER_GRATE = BLOCKS.register("exposed_copper_grate",
            () -> new CopperGrateBlock(WeatheringLogic.WeatheringStage.EXPOSED, BlockBehaviour.Properties.copy(Blocks.EXPOSED_COPPER)));
    public static final RegistryObject<Block> WEATHERED_COPPER_GRATE = BLOCKS.register("weathered_copper_grate",
            () -> new CopperGrateBlock(WeatheringLogic.WeatheringStage.WEATHERED, BlockBehaviour.Properties.copy(Blocks.WEATHERED_COPPER)));
    public static final RegistryObject<Block> OXIDIZED_COPPER_GRATE = BLOCKS.register("oxidized_copper_grate",
            () -> new CopperGrateBlock(WeatheringLogic.WeatheringStage.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.OXIDIZED_COPPER)));
    public static final RegistryObject<Block> WAXED_COPPER_GRATE = BLOCKS.register("waxed_copper_grate",
            () -> new BaseCopperGrateBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_GRATE = BLOCKS.register("waxed_exposed_copper_grate",
            () -> new BaseCopperGrateBlock(BlockBehaviour.Properties.copy(Blocks.EXPOSED_COPPER)));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_GRATE = BLOCKS.register("waxed_weathered_copper_grate",
            () -> new BaseCopperGrateBlock(BlockBehaviour.Properties.copy(Blocks.WEATHERED_COPPER)));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_GRATE = BLOCKS.register("waxed_oxidized_copper_grate",
            () -> new BaseCopperGrateBlock(BlockBehaviour.Properties.copy(Blocks.OXIDIZED_COPPER)));
    public static final RegistryObject<DoorBlock> COPPER_DOOR = BLOCKS.register("copper_door",
            () -> new CopperDoorBlock(WeatheringLogic.WeatheringStage.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<DoorBlock> EXPOSED_COPPER_DOOR = BLOCKS.register("exposed_copper_door",
            () -> new CopperDoorBlock(WeatheringLogic.WeatheringStage.EXPOSED, BlockBehaviour.Properties.copy(Blocks.EXPOSED_COPPER)));
    public static final RegistryObject<DoorBlock> WEATHERED_COPPER_DOOR = BLOCKS.register("weathered_copper_door",
            () -> new CopperDoorBlock(WeatheringLogic.WeatheringStage.WEATHERED, BlockBehaviour.Properties.copy(Blocks.WEATHERED_COPPER)));
    public static final RegistryObject<DoorBlock> OXIDIZED_COPPER_DOOR = BLOCKS.register("oxidized_copper_door",
            () -> new CopperDoorBlock(WeatheringLogic.WeatheringStage.OXIDIZED, BlockBehaviour.Properties.copy(Blocks.OXIDIZED_COPPER)));
    public static final RegistryObject<DoorBlock> WAXED_COPPER_DOOR = BLOCKS.register("waxed_copper_door",
            () -> new BaseCopperDoorBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));
    public static final RegistryObject<DoorBlock> WAXED_EXPOSED_COPPER_DOOR = BLOCKS.register("waxed_exposed_copper_door",
            () -> new BaseCopperDoorBlock(BlockBehaviour.Properties.copy(Blocks.EXPOSED_COPPER)));
    public static final RegistryObject<DoorBlock> WAXED_WEATHERED_COPPER_DOOR = BLOCKS.register("waxed_weathered_copper_door",
            () -> new BaseCopperDoorBlock(BlockBehaviour.Properties.copy(Blocks.WEATHERED_COPPER)));
    public static final RegistryObject<DoorBlock> WAXED_OXIDIZED_COPPER_DOOR = BLOCKS.register("waxed_oxidized_copper_door",
            () -> new BaseCopperDoorBlock(BlockBehaviour.Properties.copy(Blocks.OXIDIZED_COPPER)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}