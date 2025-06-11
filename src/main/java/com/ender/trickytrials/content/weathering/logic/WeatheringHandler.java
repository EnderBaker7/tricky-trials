package com.ender.trickytrials.content.weathering.logic;

import com.ender.trickytrials.content.weathering.CopperBulbBlock;
import com.ender.trickytrials.content.weathering.CopperDoorBlock;
import com.ender.trickytrials.content.weathering.CopperGrateBlock;
import com.ender.trickytrials.content.weathering.CopperTrapdoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;

import java.util.ArrayList;
import java.util.List;

public class WeatheringHandler {
    public static void handleOxidationTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, WeatheringLogic blockLogic) {
        if (random.nextInt(1125) >= 64) return;

        BooleanProperty lit = BlockStateProperties.LIT;
        BooleanProperty powered = BlockStateProperties.POWERED;

        BooleanProperty waterlogged = BlockStateProperties.WATERLOGGED;

        EnumProperty<Half> half = BlockStateProperties.HALF;
        BooleanProperty open = BlockStateProperties.OPEN;

        BooleanProperty transitioning = CopperDoorBlock.TRANSITIONING;

        Block currentBlock = state.getBlock();
        WeatheringLogic.WeatheringStage currentStage = blockLogic.getStage();

        List<BlockPos> neighbors = getNearbyCopper(level, pos);
        int a = 0;
        int b = 0;

        for (BlockPos neighborPos : neighbors) {
            BlockState neighborState = level.getBlockState(neighborPos);
            Block neighborBlock = neighborState.getBlock();
            WeatheringLogic.WeatheringStage neighborStage = WeatheringLogic.WeatheringStage.STAGE_LOOKUP.get(neighborBlock);

            if (neighborStage.ordinal() < currentStage.ordinal()) return;
            if (neighborStage.ordinal() > currentStage.ordinal()) b++;
            a++;
        }

        double c = (double)(b + 1) / (a + 1);
        double m = (currentStage == WeatheringLogic.WeatheringStage.UNAFFECTED) ? 0.75 : 1.0;
        double oxidationChance = m * c * c;

        if (random.nextFloat() < oxidationChance) {
            Block next = WeatheringLogic.WeatheringStage.STAGE_MAP.get(currentBlock);
            if (next != null) {
                if (blockLogic instanceof CopperBulbBlock) {
                    level.setBlock(pos, next.defaultBlockState()
                            .setValue(lit, state.getValue(lit))
                            .setValue(powered, state.getValue(powered)), Block.UPDATE_ALL);
                } else if (blockLogic instanceof CopperGrateBlock) {
                    level.setBlock(pos, next.defaultBlockState()
                            .setValue(waterlogged, state.getValue(waterlogged)), Block.UPDATE_ALL);
                } else if (blockLogic instanceof CopperTrapdoorBlock) {
                    level.setBlock(pos, next.defaultBlockState()
                            .setValue(open, state.getValue(open))
                            .setValue(half, state.getValue(half))
                            .setValue(powered, state.getValue(powered))
                            .setValue(waterlogged, state.getValue(waterlogged)), Block.UPDATE_ALL);
                } else if (blockLogic instanceof CopperDoorBlock) {
                    BlockPos topPos = pos.above();
                    BlockState topState = level.getBlockState(topPos);

                    level.setBlock(pos, state.setValue(transitioning, true), Block.UPDATE_ALL);
                    level.setBlock(topPos, topState.setValue(transitioning, true), Block.UPDATE_ALL);

                    level.scheduleTick(pos, state.getBlock(), 1);
                } else {
                    level.setBlock(pos, next.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }
    }

    private static List<BlockPos> getNearbyCopper(ServerLevel level, BlockPos pos) {
        List<BlockPos> result = new ArrayList<>();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -4; dy <= 4; dy++) {
                for (int dz = -4; dz <= 4; dz++) {
                    if (Math.abs(dx) + Math.abs(dy) + Math.abs(dz) <= 4) {
                        checkPos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                        BlockState neighborState = level.getBlockState(checkPos);
                        Block neighborBlock = neighborState.getBlock();
                        if (WeatheringLogic.WeatheringStage.STAGE_LOOKUP.containsKey(neighborBlock)) {
                            result.add(checkPos.immutable());
                        }
                    }
                }
            }
        }

        return result;
    }
}
