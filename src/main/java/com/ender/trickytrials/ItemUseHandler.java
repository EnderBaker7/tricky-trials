package com.ender.trickytrials;

import com.ender.trickytrials.content.*;
import com.ender.trickytrials.content.weathering.CopperDoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TrickyTrials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemUseHandler {

    private static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    private static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    private static final EnumProperty<DoubleBlockHalf> DOORHALF = DoorBlock.HALF;
    private static final EnumProperty<Half> TRAPHALF = BlockStateProperties.HALF;
    private static final BooleanProperty TRANSITIONING = BaseCopperDoorBlock.TRANSITIONING;

    @SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        BlockState state = level.getBlockState(pos);
        Block currentBlock = state.getBlock();

        if (currentBlock instanceof BaseCopperDoorBlock) {
            if (state.getValue(DOORHALF) == DoubleBlockHalf.UPPER) {
                pos = pos.below();
            }
        }

        if (itemStack.is(Items.HONEYCOMB)) {
            Block waxedBlock = getWaxedBlock(currentBlock);
            changeBlock(waxedBlock, level, pos, player, itemStack, 3003, event);
        }

        if (itemStack.is(ItemTags.AXES)) {
            Block scrapedWaxedBlock = getScrapedBlock(currentBlock, "waxed");
            changeBlock(scrapedWaxedBlock, level, pos, player, itemStack, 3004, event);

            Block scrapedBlock = getScrapedBlock(currentBlock, "unwaxed");
            changeBlock(scrapedBlock, level, pos, player, itemStack, 3005, event);
        }
    }

    private static void changeBlock(Block nextBlock, Level level, BlockPos pos, Player player, ItemStack itemStack, int pType, PlayerInteractEvent.RightClickBlock event) {
        if (nextBlock != null) {
            BlockState currentState = level.getBlockState(pos);
            BlockState nextState = nextBlock.defaultBlockState();

            BlockState bottomState = null;
            BlockState topState = null;

            if (nextBlock instanceof BaseCopperBulbBlock) {
                nextState = nextState
                        .setValue(LIT, currentState.getValue(LIT))
                        .setValue(POWERED, currentState.getValue(POWERED));
            }

            if (nextBlock instanceof BaseCopperGrateBlock) {
                nextState = nextState.setValue(WATERLOGGED, currentState.getValue(WATERLOGGED));
            }

            if (nextBlock instanceof BaseCopperDoorBlock) {
                bottomState = nextState
                        .setValue(FACING, currentState.getValue(FACING))
                        .setValue(OPEN, currentState.getValue(OPEN))
                        .setValue(HINGE, currentState.getValue(HINGE))
                        .setValue(POWERED, currentState.getValue(POWERED))
                        .setValue(DOORHALF, DoubleBlockHalf.LOWER);
                topState = bottomState
                        .setValue(DOORHALF, DoubleBlockHalf.UPPER);
                if (nextBlock instanceof CopperDoorBlock) {
                    bottomState = bottomState
                            .setValue(TRANSITIONING, false);
                    topState = topState
                            .setValue(TRANSITIONING, false);
                }
            }

            if (nextBlock instanceof TrapDoorBlock) {
                nextState = nextState
                        .setValue(OPEN, currentState.getValue(OPEN))
                        .setValue(TRAPHALF, currentState.getValue(TRAPHALF))
                        .setValue(POWERED, currentState.getValue(POWERED))
                        .setValue(WATERLOGGED, currentState.getValue(WATERLOGGED));
            }

            if (!level.isClientSide()) {
                if (nextBlock instanceof BaseCopperDoorBlock) {
                    BlockPos topPos = pos.above();
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);

                    level.setBlock(pos, bottomState, Block.UPDATE_ALL);
                    level.setBlock(topPos, topState, Block.UPDATE_ALL);

                    level.levelEvent(pType, topPos, 0);
                } else {
                    level.setBlock(pos, nextState, Block.UPDATE_ALL);
                }
                level.levelEvent(pType, pos, 0);

                if (pType == 3004) {
                    level.playSound(null, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1, 1);
                } else if (pType == 3005) {
                    level.playSound(null, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1, 1);
                }

                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }
            }

            player.swing(event.getHand());

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

    private static final Supplier<Map<Block, Block>> TOWAXED = () -> Map.ofEntries(
            Map.entry(TTBlocks.CHISELED_COPPER.get(), TTBlocks.WAXED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.EXPOSED_CHISELED_COPPER.get(), TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.WEATHERED_CHISELED_COPPER.get(), TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.OXIDIZED_CHISELED_COPPER.get(), TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get()),

            Map.entry(TTBlocks.COPPER_BULB.get(), TTBlocks.WAXED_COPPER_BULB.get()),
            Map.entry(TTBlocks.EXPOSED_COPPER_BULB.get(), TTBlocks.WAXED_EXPOSED_COPPER_BULB.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_BULB.get(), TTBlocks.WAXED_WEATHERED_COPPER_BULB.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_BULB.get(), TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get()),

            Map.entry(TTBlocks.COPPER_GRATE.get(), TTBlocks.WAXED_COPPER_GRATE.get()),
            Map.entry(TTBlocks.EXPOSED_COPPER_GRATE.get(), TTBlocks.WAXED_EXPOSED_COPPER_GRATE.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_GRATE.get(), TTBlocks.WAXED_WEATHERED_COPPER_GRATE.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_GRATE.get(), TTBlocks.WAXED_OXIDIZED_COPPER_GRATE.get()),

            Map.entry(TTBlocks.COPPER_DOOR.get(), TTBlocks.WAXED_COPPER_DOOR.get()),
            Map.entry(TTBlocks.EXPOSED_COPPER_DOOR.get(), TTBlocks.WAXED_EXPOSED_COPPER_DOOR.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_DOOR.get(), TTBlocks.WAXED_WEATHERED_COPPER_DOOR.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_DOOR.get(), TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get()),

            Map.entry(TTBlocks.COPPER_TRAPDOOR.get(), TTBlocks.WAXED_COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.EXPOSED_COPPER_TRAPDOOR.get(), TTBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_TRAPDOOR.get(), TTBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_TRAPDOOR.get(), TTBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get())
    );

    private static final Supplier<Map<Block, Block>> WAXSCRAPE = () -> Map.ofEntries(
            Map.entry(TTBlocks.WAXED_CHISELED_COPPER.get(), TTBlocks.CHISELED_COPPER.get()),
            Map.entry(TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(), TTBlocks.EXPOSED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(), TTBlocks.WEATHERED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(), TTBlocks.OXIDIZED_CHISELED_COPPER.get()),

            Map.entry(TTBlocks.WAXED_COPPER_BULB.get(), TTBlocks.COPPER_BULB.get()),
            Map.entry(TTBlocks.WAXED_EXPOSED_COPPER_BULB.get(), TTBlocks.EXPOSED_COPPER_BULB.get()),
            Map.entry(TTBlocks.WAXED_WEATHERED_COPPER_BULB.get(), TTBlocks.WEATHERED_COPPER_BULB.get()),
            Map.entry(TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get(), TTBlocks.OXIDIZED_COPPER_BULB.get()),

            Map.entry(TTBlocks.WAXED_COPPER_GRATE.get(), TTBlocks.COPPER_GRATE.get()),
            Map.entry(TTBlocks.WAXED_EXPOSED_COPPER_GRATE.get(), TTBlocks.EXPOSED_COPPER_GRATE.get()),
            Map.entry(TTBlocks.WAXED_WEATHERED_COPPER_GRATE.get(), TTBlocks.WEATHERED_COPPER_GRATE.get()),
            Map.entry(TTBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(), TTBlocks.OXIDIZED_COPPER_GRATE.get()),

            Map.entry(TTBlocks.WAXED_COPPER_DOOR.get(), TTBlocks.COPPER_DOOR.get()),
            Map.entry(TTBlocks.WAXED_EXPOSED_COPPER_DOOR.get(), TTBlocks.EXPOSED_COPPER_DOOR.get()),
            Map.entry(TTBlocks.WAXED_WEATHERED_COPPER_DOOR.get(), TTBlocks.WEATHERED_COPPER_DOOR.get()),
            Map.entry(TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(), TTBlocks.OXIDIZED_COPPER_DOOR.get()),

            Map.entry(TTBlocks.WAXED_COPPER_TRAPDOOR.get(), TTBlocks.COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(), TTBlocks.EXPOSED_COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(), TTBlocks.WEATHERED_COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(), TTBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
    );

    private static final Supplier<Map<Block, Block>> OXISCRAPE = () -> Map.ofEntries(
            Map.entry(TTBlocks.OXIDIZED_CHISELED_COPPER.get(), TTBlocks.WEATHERED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.WEATHERED_CHISELED_COPPER.get(), TTBlocks.EXPOSED_CHISELED_COPPER.get()),
            Map.entry(TTBlocks.EXPOSED_CHISELED_COPPER.get(), TTBlocks.CHISELED_COPPER.get()),

            Map.entry(TTBlocks.EXPOSED_COPPER_BULB.get(), TTBlocks.COPPER_BULB.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_BULB.get(), TTBlocks.EXPOSED_COPPER_BULB.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_BULB.get(), TTBlocks.WEATHERED_COPPER_BULB.get()),

            Map.entry(TTBlocks.EXPOSED_COPPER_GRATE.get(), TTBlocks.COPPER_GRATE.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_GRATE.get(), TTBlocks.EXPOSED_COPPER_GRATE.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_GRATE.get(), TTBlocks.WEATHERED_COPPER_GRATE.get()),

            Map.entry(TTBlocks.EXPOSED_COPPER_DOOR.get(), TTBlocks.COPPER_DOOR.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_DOOR.get(), TTBlocks.EXPOSED_COPPER_DOOR.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_DOOR.get(), TTBlocks.WEATHERED_COPPER_DOOR.get()),

            Map.entry(TTBlocks.EXPOSED_COPPER_TRAPDOOR.get(), TTBlocks.COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.WEATHERED_COPPER_TRAPDOOR.get(), TTBlocks.EXPOSED_COPPER_TRAPDOOR.get()),
            Map.entry(TTBlocks.OXIDIZED_COPPER_TRAPDOOR.get(), TTBlocks.WEATHERED_COPPER_TRAPDOOR.get())
    );

    private static Block getWaxedBlock(Block block) {
        return TOWAXED.get().get(block);
    }

    private static Block getScrapedBlock(Block block, String type) {
        if (Objects.equals(type, "waxed")) {
            return WAXSCRAPE.get().get(block);
        } else {
            return OXISCRAPE.get().get(block);
        }
    }
}
