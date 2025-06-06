package com.ender.trickytrials;

import com.ender.trickytrials.content.BaseCopperBulbBlock;
import com.ender.trickytrials.content.TTBlocks;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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

    @SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        BlockState state = level.getBlockState(pos);
        Block currentBlock = state.getBlock();

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

            if (nextBlock instanceof BaseCopperBulbBlock) {
                nextState = nextState
                        .setValue(LIT, currentState.getValue(LIT))
                        .setValue(POWERED, currentState.getValue(POWERED));
            }

            if (!level.isClientSide()) {
                level.setBlock(pos, nextState, 3);
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

    private static final Supplier<Map<Block, Block>> TOWAXED = () -> Map.of(
            TTBlocks.CHISELED_COPPER.get(), TTBlocks.WAXED_CHISELED_COPPER.get(),
            TTBlocks.EXPOSED_CHISELED_COPPER.get(), TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(),
            TTBlocks.WEATHERED_CHISELED_COPPER.get(), TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(),
            TTBlocks.OXIDIZED_CHISELED_COPPER.get(), TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(),

            TTBlocks.COPPER_BULB.get(), TTBlocks.WAXED_COPPER_BULB.get(),
            TTBlocks.EXPOSED_COPPER_BULB.get(), TTBlocks.WAXED_EXPOSED_COPPER_BULB.get(),
            TTBlocks.WEATHERED_COPPER_BULB.get(), TTBlocks.WAXED_WEATHERED_COPPER_BULB.get(),
            TTBlocks.OXIDIZED_COPPER_BULB.get(), TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get()
    );

    private static final Supplier<Map<Block, Block>> WAXSCRAPE = () -> Map.of(
            TTBlocks.WAXED_CHISELED_COPPER.get(), TTBlocks.CHISELED_COPPER.get(),
            TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(), TTBlocks.EXPOSED_CHISELED_COPPER.get(),
            TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(), TTBlocks.WEATHERED_CHISELED_COPPER.get(),
            TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(), TTBlocks.OXIDIZED_CHISELED_COPPER.get(),

            TTBlocks.WAXED_COPPER_BULB.get(), TTBlocks.COPPER_BULB.get(),
            TTBlocks.WAXED_EXPOSED_COPPER_BULB.get(), TTBlocks.EXPOSED_COPPER_BULB.get(),
            TTBlocks.WAXED_WEATHERED_COPPER_BULB.get(), TTBlocks.WEATHERED_COPPER_BULB.get(),
            TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get(), TTBlocks.OXIDIZED_COPPER_BULB.get()
    );

    private static final Supplier<Map<Block, Block>> OXISCRAPE = () -> Map.of(
            TTBlocks.OXIDIZED_CHISELED_COPPER.get(), TTBlocks.WEATHERED_CHISELED_COPPER.get(),
            TTBlocks.WEATHERED_CHISELED_COPPER.get(), TTBlocks.EXPOSED_CHISELED_COPPER.get(),
            TTBlocks.EXPOSED_CHISELED_COPPER.get(), TTBlocks.CHISELED_COPPER.get(),

            TTBlocks.EXPOSED_COPPER_BULB.get(), TTBlocks.COPPER_BULB.get(),
            TTBlocks.WEATHERED_COPPER_BULB.get(), TTBlocks.EXPOSED_COPPER_BULB.get(),
            TTBlocks.OXIDIZED_COPPER_BULB.get(), TTBlocks.WEATHERED_COPPER_BULB.get()
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
