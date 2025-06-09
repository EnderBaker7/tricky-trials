package com.ender.trickytrials.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

public class BaseCopperDoorBlock extends DoorBlock {

    public static final BooleanProperty TRANSITIONING = BooleanProperty.create("transitioning");
    public BaseCopperDoorBlock(Properties properties) {
        super(properties.noOcclusion(), TTBlockSetTypes.COPPER);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(HINGE, DoorHingeSide.LEFT)
                .setValue(POWERED, false)
                .setValue(HALF, DoubleBlockHalf.LOWER));
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
                    .setValue(HALF, DoubleBlockHalf.LOWER);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
    }
}
