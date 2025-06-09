package com.ender.trickytrials.content.weathering;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class WeatheringCopperBlock extends Block implements WeatheringLogic {
    private final WeatheringStage stage;

    public WeatheringCopperBlock(WeatheringStage stage, Properties properties) {
        super(properties);
        this.stage = stage;
        this.registerDefaultState(this.defaultBlockState());
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.stage != WeatheringStage.OXIDIZED;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        WeatheringHandler.handleOxidationTick(state, level, pos, random, this);
    }

    @Override
    public WeatheringStage getStage() {
        return stage;
    }
}
