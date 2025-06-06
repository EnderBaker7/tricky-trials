package com.ender.trickytrials.content;

import com.ender.trickytrials.TrickyTrials;
import com.ender.trickytrials.content.weathering.WeatheringCopperBlock;
import com.ender.trickytrials.content.weathering.WeatheringStage;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, TrickyTrials.MODID);

    public static RegistryObject<Block> registerWeatheringBlocks(String name, WeatheringStage stage, Block copyProperty) {
        return BLOCKS.register(name, () -> new WeatheringCopperBlock(stage, BlockBehaviour.Properties.copy(copyProperty)));
    }
    public static RegistryObject<Block> registerBlock(String name, Block copyProperty) {
        return BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.copy(copyProperty)));
    }

    public static RegistryObject<Block> registerBulbBlock(String name, WeatheringCopper.WeatherState pState, int lightLev, MapColor color) {
        return BLOCKS.register(name, () -> new CopperBulbBlock(pState, copyBulbProperties(COPPER_BULB)
                .lightLevel(state -> state.getValue(CopperBulbBlock.LIT) ? lightLev : 0)
                .mapColor(color)));
    }

    private static BlockBehaviour.Properties copyBulbProperties(RegistryObject<Block> ref) {
        return BlockBehaviour.Properties.copy(ref.get());
    }
    public static final RegistryObject<Block> CHISELED_COPPER = registerWeatheringBlocks("chiseled_copper", WeatheringStage.FINE, Blocks.COPPER_BLOCK);
    public static final RegistryObject<Block> EXPOSED_CHISELED_COPPER = registerWeatheringBlocks("exposed_chiseled_copper",
            WeatheringStage.EXPO, Blocks.EXPOSED_COPPER);
    public static final RegistryObject<Block> WEATHERED_CHISELED_COPPER = registerWeatheringBlocks("weathered_chiseled_copper",
            WeatheringStage.WEATHER, Blocks.WEATHERED_COPPER);
    public static final RegistryObject<Block> OXIDIZED_CHISELED_COPPER = registerWeatheringBlocks("oxidized_chiseled_copper",
            WeatheringStage.OXI, Blocks.OXIDIZED_COPPER);
    public static final RegistryObject<Block> WAXED_CHISELED_COPPER = registerBlock("waxed_chiseled_copper", Blocks.WAXED_COPPER_BLOCK);
    public static final RegistryObject<Block> WAXED_EXPOSED_CHISELED_COPPER = registerBlock("waxed_exposed_chiseled_copper", Blocks.WAXED_EXPOSED_COPPER);
    public static final RegistryObject<Block> WAXED_WEATHERED_CHISELED_COPPER = registerBlock("waxed_weathered_chiseled_copper", Blocks.WAXED_WEATHERED_COPPER);
    public static final RegistryObject<Block> WAXED_OXIDIZED_CHISELED_COPPER = registerBlock("waxed_oxidized_chiseled_copper", Blocks.WAXED_OXIDIZED_COPPER);

    public static final RegistryObject<Block> COPPER_BULB = BLOCKS.register("copper_bulb",
            () -> new CopperBulbBlock(WeatheringCopper.WeatherState.UNAFFECTED, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(CopperBulbBlock.LIT) ? 15 : 0).sound(TTSoundEvents.COPPER_BULB.get())));
    public static final RegistryObject<Block> EXPOSED_COPPER_BULB = registerBulbBlock("exposed_copper_bulb", WeatheringCopper.WeatherState.EXPOSED,
            12, MapColor.TERRACOTTA_LIGHT_GRAY);
    public static final RegistryObject<Block> WEATHERED_COPPER_BULB = registerBulbBlock("weathered_copper_bulb", WeatheringCopper.WeatherState.WEATHERED,
            8, MapColor.WARPED_STEM);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BULB = registerBulbBlock("oxidized_copper_bulb", WeatheringCopper.WeatherState.OXIDIZED,
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
            () -> new CopperGrateBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).noOcclusion().sound(TTSoundEvents.COPPER_GRATE.get())));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}