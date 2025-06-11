package com.ender.trickytrials.content.weathering;

import com.ender.trickytrials.content.TTBlockSetTypes;
import com.ender.trickytrials.content.weathering.logic.WeatheringHandler;
import com.ender.trickytrials.content.weathering.logic.WeatheringLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CopperTrapdoorBlock extends TrapDoorBlock implements WeatheringLogic {
    private final WeatheringStage weatheringStage;

    public CopperTrapdoorBlock(WeatheringStage stage, Properties properties) {
        super(properties.noOcclusion(), TTBlockSetTypes.COPPER);
        this.weatheringStage = stage;
    }

    @Override
    public WeatheringStage getStage() {
        return this.weatheringStage;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.weatheringStage != WeatheringStage.OXIDIZED;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        WeatheringHandler.handleOxidationTick(state, level, pos, source, this);
    }
}
