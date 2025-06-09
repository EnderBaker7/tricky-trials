package com.ender.trickytrials.content;

import com.ender.trickytrials.content.weathering.WeatheringHandler;
import com.ender.trickytrials.content.weathering.WeatheringLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class CopperDoorBlock extends BaseCopperDoorBlock implements WeatheringLogic {
    private final WeatheringStage weatherState;

    public CopperDoorBlock(WeatheringStage state, Properties properties) {
        super(properties.noOcclusion());
        this.weatherState = state;
        this.registerDefaultState(this.defaultBlockState()
                .setValue(TRANSITIONING, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TRANSITIONING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = super.getStateForPlacement(context);

        if (pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(context)) {
            if (state != null) {
                return state.setValue(TRANSITIONING, false);
            }
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getOptionalValue(TRANSITIONING).orElse(false)) return true;

        DoubleBlockHalf half = state.getValue(HALF);

        if (half == DoubleBlockHalf.LOWER) {
            return true;
        } else {
            BlockState below = level.getBlockState(pos.below());
            return below.getBlock() == this &&
                    below.getValue(HALF) == DoubleBlockHalf.LOWER &&
                    below.getOptionalValue(TRANSITIONING).orElse(true);
        }
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
        if (state.getValue(HALF) != DoubleBlockHalf.LOWER) return;

        Block block = state.getBlock();
        if (!(block instanceof WeatheringLogic)) return;

        WeatheringHandler.handleOxidationTick(state, level, pos, source, this);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (!state.getOptionalValue(TRANSITIONING).orElse(false)) return;
        if (state.getValue(HALF) != DoubleBlockHalf.LOWER) return;

        BlockPos topPos = pos.above();
        BlockState topState = level.getBlockState(topPos);

        if (topState.getBlock() != state.getBlock() || topState.getValue(HALF) != DoubleBlockHalf.UPPER) {
            System.out.println("Top half is missing or mismatched - skipping replacement.");
            return;
        }

        BlockState nextOpt = WeatheringStage.getNext(state.getBlock()).defaultBlockState();

        Block nextBlock = nextOpt.getBlock();
        if (!(nextBlock instanceof CopperDoorBlock)) return;

        Direction facing = state.getValue(FACING);
        DoorHingeSide hinge = state.getValue(HINGE);
        boolean open = state.getValue(OPEN);
        boolean powered = state.getValue(POWERED);

        BlockState bottomFinal = nextBlock.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(HINGE, hinge)
                .setValue(OPEN, open)
                .setValue(POWERED, powered)
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(TRANSITIONING, false);

        BlockState topFinal = nextBlock.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(HINGE, hinge)
                .setValue(OPEN, open)
                .setValue(POWERED, powered)
                .setValue(HALF, DoubleBlockHalf.UPPER)
                .setValue(TRANSITIONING, false);

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);
        level.setBlock(topPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);

        level.setBlock(pos, bottomFinal, Block.UPDATE_ALL);
        level.setBlock(topPos, topFinal, Block.UPDATE_ALL);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER && state.getOptionalValue(TRANSITIONING).orElse(false)) {
            level.scheduleTick(pos, state.getBlock(), 1);
        }
    }
}
