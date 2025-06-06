package com.ender.trickytrials.content;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CopperGrateBlock extends BaseCopperGrateBlock implements WeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;

    public CopperGrateBlock(WeatheringCopper.WeatherState state, Properties properties) {
        super(properties.noOcclusion().sound(TTSoundEvents.COPPER_GRATE.get()));
        this.weatherState = state;
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.getNext(state).isPresent();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        Block block = state.getBlock();
        if (block instanceof WeatheringCopper weatheringCopper) {
            Optional<BlockState> nextState = weatheringCopper.getNext(state);
            nextState.ifPresent(blockState -> level.setBlockAndUpdate(pos, blockState.setValue(WATERLOGGED, state.getValue(WATERLOGGED))));
        }
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return switch (this.weatherState) {
            case UNAFFECTED -> Optional.of(TTBlocks.EXPOSED_COPPER_GRATE.get().defaultBlockState().setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
            case EXPOSED -> Optional.of(TTBlocks.WEATHERED_COPPER_GRATE.get().defaultBlockState().setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
            case WEATHERED -> Optional.of(TTBlocks.OXIDIZED_COPPER_GRATE.get().defaultBlockState().setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
            default -> Optional.empty();
        };
    }
}
