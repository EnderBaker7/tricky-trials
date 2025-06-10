package com.ender.trickytrials.content.weathering;

import com.ender.trickytrials.content.BaseCopperBulbBlock;
import com.ender.trickytrials.content.weathering.logic.WeatheringHandler;
import com.ender.trickytrials.content.weathering.logic.WeatheringLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class CopperBulbBlock extends BaseCopperBulbBlock implements WeatheringLogic {
    private final WeatheringStage weatherState;

    public CopperBulbBlock(WeatheringStage state, Properties properties) {
        super(properties);
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
