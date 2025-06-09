package com.ender.trickytrials.content;

import com.ender.trickytrials.content.weathering.WeatheringHandler;
import com.ender.trickytrials.content.weathering.WeatheringLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Optional;

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
