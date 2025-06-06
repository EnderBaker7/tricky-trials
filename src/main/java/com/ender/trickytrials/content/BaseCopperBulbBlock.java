package com.ender.trickytrials.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BaseCopperBulbBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public BaseCopperBulbBlock(Properties pProperties) {
        super(pProperties
                .isRedstoneConductor((blockState, blockGetter, blockPos) -> false)
                .isSuffocating((blockState, blockGetter, blockPos) -> false)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, POWERED);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            boolean wasPowered = state.getValue(POWERED);
            boolean newLit = !state.getValue(LIT);

            if (powered && !wasPowered) {
                level.setBlock(pos, state.setValue(LIT, newLit).setValue(POWERED, true), 3);
                level.playSound(null, pos, TTSoundEvents.COPPER_BULB_CLICK.get(), SoundSource.BLOCKS, 1.0f, newLit ? 1.2f : 0.8f);
            } else if (!powered && wasPowered) {
                level.setBlock(pos, state.setValue(POWERED, false), 2);
                level.playSound(null, pos, TTSoundEvents.COPPER_BULB_CLICK.get(), SoundSource.BLOCKS, 1.0f, newLit ? 1.2f : 0.8f);
            }
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LIT) ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return false;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }
}
