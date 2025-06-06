package com.ender.trickytrials.content;

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

public class CopperBulbBlock extends BaseCopperBulbBlock implements WeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;

    public CopperBulbBlock(WeatheringCopper.WeatherState state, Properties properties) {
        super(properties);
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
            nextState.ifPresent(blockState -> level.setBlockAndUpdate(pos, blockState.setValue(LIT, state.getValue(LIT)).setValue(POWERED, state.getValue(POWERED))));
        }
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return switch (this.weatherState) {
            case UNAFFECTED -> Optional.of(TTBlocks.EXPOSED_COPPER_BULB.get().defaultBlockState().setValue(LIT, state.getValue(LIT))
                    .setValue(POWERED, state.getValue(POWERED)));
            case EXPOSED -> Optional.of(TTBlocks.WEATHERED_COPPER_BULB.get().defaultBlockState().setValue(LIT, state.getValue(LIT))
                    .setValue(POWERED, state.getValue(POWERED)));
            case WEATHERED -> Optional.of(TTBlocks.OXIDIZED_COPPER_BULB.get().defaultBlockState().setValue(LIT, state.getValue(LIT))
                    .setValue(POWERED, state.getValue(POWERED)));
            default -> Optional.empty();
        };
    }
    public Optional<BlockState> getPrevious(BlockState state) {
        return switch (this.weatherState) {
            case EXPOSED -> Optional.of(TTBlocks.COPPER_BULB.get().defaultBlockState().setValue(LIT, state.getValue(LIT))
                    .setValue(POWERED, state.getValue(POWERED)));
            case WEATHERED -> Optional.of(TTBlocks.EXPOSED_COPPER_BULB.get().defaultBlockState().setValue(LIT, state.getValue(LIT))
                    .setValue(POWERED, state.getValue(POWERED)));
            case OXIDIZED -> Optional.of(TTBlocks.WEATHERED_COPPER_BULB.get().defaultBlockState().setValue(LIT, state.getValue(LIT))
                    .setValue(POWERED, state.getValue(POWERED)));
            default -> Optional.empty();
        };
    }
}
