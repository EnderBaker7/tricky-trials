package com.ender.trickytrials;

import com.ender.trickytrials.content.*;
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

    private static final BooleanProperty LIT = BaseCopperBulbBlock.LIT;
    private static final BooleanProperty POWERED = BaseCopperBulbBlock.POWERED;
    private static final BooleanProperty WATERLOGGED = BaseCopperGrateBlock.WATERLOGGED;

    private static final DirectionProperty FACING = DoorBlock.FACING;
    private static final BooleanProperty OPEN = DoorBlock.OPEN;
    private static final EnumProperty<DoorHingeSide> HINGE = DoorBlock.HINGE;
    private static final BooleanProperty DPOWERED = DoorBlock.POWERED;
    private static final EnumProperty<DoubleBlockHalf> HALF = DoorBlock.HALF;
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
            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
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
                        .setValue(DPOWERED, currentState.getValue(DPOWERED))
                        .setValue(HALF, DoubleBlockHalf.LOWER);
                topState = bottomState
                        .setValue(HALF, DoubleBlockHalf.UPPER);
                if (nextBlock instanceof CopperDoorBlock) {
                    bottomState = bottomState
                            .setValue(TRANSITIONING, false);
                    topState = topState
                            .setValue(TRANSITIONING, false);
                }
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
            Map.entry(TTBlocks.OXIDIZED_COPPER_DOOR.get(), TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get())
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
            Map.entry(TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(), TTBlocks.OXIDIZED_COPPER_DOOR.get())
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
            Map.entry(TTBlocks.OXIDIZED_COPPER_DOOR.get(), TTBlocks.WEATHERED_COPPER_DOOR.get())
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
