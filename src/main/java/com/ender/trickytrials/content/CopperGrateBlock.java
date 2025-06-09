package com.ender.trickytrials.content;

import com.ender.trickytrials.content.weathering.WeatheringHandler;
import com.ender.trickytrials.content.weathering.WeatheringLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CopperGrateBlock extends BaseCopperGrateBlock implements WeatheringLogic {
    private final WeatheringStage weatherState;

    public CopperGrateBlock(WeatheringStage state, Properties properties) {
        super(properties.noOcclusion().sound(TTSoundEvents.COPPER_GRATE.get()));
        this.weatherState = state;
    }

    @Override
    public WeatheringStage getStage() {
        return this.weatherState;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.weatherState != WeatheringStage.OXIDIZED;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        WeatheringHandler.handleOxidationTick(state, level, pos, source, this);
    }
}
