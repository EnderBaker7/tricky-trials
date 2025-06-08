package com.ender.trickytrials.content;

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

public class CopperDoorBlock extends DoorBlock implements WeatheringCopper {
    private final WeatheringCopper.WeatherState weatherState;

    public static final BooleanProperty TRANSITIONING = BooleanProperty.create("transitioning");

    public CopperDoorBlock(WeatheringCopper.WeatherState state, Properties properties) {
        super(properties.noOcclusion(), TTBlockSetTypes.COPPER);
        this.weatherState = state;
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(HINGE, DoorHingeSide.LEFT)
                .setValue(POWERED, false)
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(TRANSITIONING, false));
    }

    private DoorHingeSide calculateHinge(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getHorizontalDirection();
        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();

        BlockPos posLeft = pos.relative(left);
        BlockPos posRight = pos.relative(right);

        BlockState stateLeft = level.getBlockState(posLeft);
        BlockState stateLeftAbove = level.getBlockState(posLeft.above());
        BlockState stateRight = level.getBlockState(posRight);
        BlockState stateRightAbove = level.getBlockState(posRight.above());

        boolean isLeftSolid = stateLeft.isFaceSturdy(level, posLeft, right) || stateLeftAbove.isFaceSturdy(level, posLeft.above(), right);
        boolean isRightSolid = stateRight.isFaceSturdy(level, posRight, left) || stateRightAbove.isFaceSturdy(level, posRight.above(), left);

        boolean leftIsDoor = stateLeft.getBlock() instanceof DoorBlock && stateLeft.getValue(DoorBlock.FACING) == facing;
        boolean rightIsDoor = stateRight.getBlock() instanceof DoorBlock && stateRight.getValue(DoorBlock.FACING) == facing;

        if (leftIsDoor && !rightIsDoor) return DoorHingeSide.RIGHT;
        if (rightIsDoor && !leftIsDoor) return DoorHingeSide.LEFT;

        if (isRightSolid && !isLeftSolid) return DoorHingeSide.RIGHT;
        if (isLeftSolid && !isRightSolid) return DoorHingeSide.LEFT;

        Vec3 clickLocation = context.getClickLocation();
        double hitX = clickLocation.x - pos.getX();
        double hitZ = clickLocation.z - pos.getZ();

        // This final part depends on the door's facing
        return switch (facing) {
            case NORTH -> hitX < 0.5D ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            case SOUTH -> hitX > 0.5D ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            case WEST  -> hitZ > 0.5D ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            case EAST  -> hitZ < 0.5D ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            default    -> DoorHingeSide.RIGHT;
        };
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

        if (pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(context)) {
            Direction facing = context.getHorizontalDirection();
            DoorHingeSide hinge = calculateHinge(context);
            boolean powered = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
            System.out.println("Placing door at: " + context.getClickedPos() + ", hinge=" + hinge + ", facing=" + facing);

            return this.defaultBlockState()
                    .setValue(FACING, facing)
                    .setValue(OPEN, false)
                    .setValue(HINGE, hinge)
                    .setValue(POWERED, powered)
                    .setValue(HALF, DoubleBlockHalf.LOWER)
                    .setValue(TRANSITIONING, false);
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
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.getNext(state).isPresent();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        System.out.println("RANDOM TICK @ " + pos + " - half: " + state.getValue(HALF));
        if (state.getValue(HALF) != DoubleBlockHalf.LOWER) return;

        Block block = state.getBlock();
        if (!(block instanceof WeatheringCopper weatheringCopper)) return;

        Optional<BlockState> nextOpt = weatheringCopper.getNext(state);
        if (nextOpt.isEmpty()) return;

        BlockPos topPos = pos.above();
        BlockState topState = level.getBlockState(topPos);

        if (!(topState.getBlock() == block && topState.getValue(HALF) == DoubleBlockHalf.UPPER)) return;

        level.setBlock(pos, state.setValue(TRANSITIONING, true), Block.UPDATE_ALL);
        level.setBlock(topPos, topState.setValue(TRANSITIONING, true), Block.UPDATE_ALL);

        level.scheduleTick(pos, state.getBlock(), 1);
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

        Optional<BlockState> nextOpt = getNext(state);
        if (nextOpt.isEmpty()) return;

        Block nextBlock = nextOpt.get().getBlock();
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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER && state.getOptionalValue(TRANSITIONING).orElse(false)) {
            level.scheduleTick(pos, state.getBlock(), 1);
        }
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return switch (this.weatherState) {
            case UNAFFECTED -> Optional.of(TTBlocks.EXPOSED_COPPER_DOOR.get().defaultBlockState());
            case EXPOSED -> Optional.of(TTBlocks.WEATHERED_COPPER_DOOR.get().defaultBlockState());
            case WEATHERED -> Optional.of(TTBlocks.OXIDIZED_COPPER_DOOR.get().defaultBlockState());
            default -> Optional.empty();
        };
    }
}
