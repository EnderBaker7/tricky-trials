package com.ender.trickytrials.content.weathering;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WeatheringCopperBlock extends Block {
    private final WeatheringStage stage;

    public WeatheringCopperBlock(WeatheringStage stage, Properties properties) {
        super(properties);
        this.stage = stage;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.stage != WeatheringStage.OXI;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(20) == 0) {
            Block next = WeatheringStage.getNext(this);
            if (next != null) {
                level.setBlock(pos, next.defaultBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    public WeatheringStage getStage() {
        return stage;
    }
}
