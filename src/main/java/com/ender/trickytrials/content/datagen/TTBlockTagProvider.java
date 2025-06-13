package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.content.TTBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TTBlockTagProvider extends BlockTagsProvider {
    public TTBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        TTBlocks.CHISELED_COPPER.get(),
                        TTBlocks.EXPOSED_CHISELED_COPPER.get(),
                        TTBlocks.WEATHERED_CHISELED_COPPER.get(),
                        TTBlocks.OXIDIZED_CHISELED_COPPER.get(),

                        TTBlocks.WAXED_CHISELED_COPPER.get(),
                        TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(),
                        TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(),
                        TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(),

                        TTBlocks.COPPER_BULB.get(),
                        TTBlocks.EXPOSED_COPPER_BULB.get(),
                        TTBlocks.WEATHERED_COPPER_BULB.get(),
                        TTBlocks.OXIDIZED_COPPER_BULB.get(),

                        TTBlocks.WAXED_COPPER_BULB.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_BULB.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_BULB.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get(),

                        TTBlocks.COPPER_GRATE.get(),
                        TTBlocks.EXPOSED_COPPER_GRATE.get(),
                        TTBlocks.WEATHERED_COPPER_GRATE.get(),
                        TTBlocks.OXIDIZED_COPPER_GRATE.get(),

                        TTBlocks.WAXED_COPPER_GRATE.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_GRATE.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_GRATE.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(),

                        TTBlocks.COPPER_DOOR.get(),
                        TTBlocks.EXPOSED_COPPER_DOOR.get(),
                        TTBlocks.WEATHERED_COPPER_DOOR.get(),
                        TTBlocks.OXIDIZED_COPPER_DOOR.get(),

                        TTBlocks.WAXED_COPPER_DOOR.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_DOOR.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_DOOR.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(),

                        TTBlocks.COPPER_TRAPDOOR.get(),
                        TTBlocks.EXPOSED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WEATHERED_COPPER_TRAPDOOR.get(),
                        TTBlocks.OXIDIZED_COPPER_TRAPDOOR.get(),

                        TTBlocks.WAXED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(),

                        TTBlocks.TUFF_STAIRS.get(),
                        TTBlocks.TUFF_SLAB.get(),
                        TTBlocks.TUFF_WALL.get(),

                        TTBlocks.POLISHED_TUFF.get(),
                        TTBlocks.POLISHED_TUFF_STAIRS.get(),
                        TTBlocks.POLISHED_TUFF_SLAB.get(),
                        TTBlocks.POLISHED_TUFF_WALL.get(),

                        TTBlocks.TUFF_BRICKS.get(),
                        TTBlocks.TUFF_BRICK_STAIRS.get(),
                        TTBlocks.TUFF_BRICK_SLAB.get(),
                        TTBlocks.TUFF_BRICK_WALL.get()
                );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(
                        TTBlocks.CHISELED_COPPER.get(),
                        TTBlocks.EXPOSED_CHISELED_COPPER.get(),
                        TTBlocks.WEATHERED_CHISELED_COPPER.get(),
                        TTBlocks.OXIDIZED_CHISELED_COPPER.get(),

                        TTBlocks.WAXED_CHISELED_COPPER.get(),
                        TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(),
                        TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(),
                        TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(),

                        TTBlocks.COPPER_BULB.get(),
                        TTBlocks.EXPOSED_COPPER_BULB.get(),
                        TTBlocks.WEATHERED_COPPER_BULB.get(),
                        TTBlocks.OXIDIZED_COPPER_BULB.get(),

                        TTBlocks.WAXED_COPPER_BULB.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_BULB.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_BULB.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get(),

                        TTBlocks.COPPER_GRATE.get(),
                        TTBlocks.EXPOSED_COPPER_GRATE.get(),
                        TTBlocks.WEATHERED_COPPER_GRATE.get(),
                        TTBlocks.OXIDIZED_COPPER_GRATE.get(),

                        TTBlocks.WAXED_COPPER_GRATE.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_GRATE.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_GRATE.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(),

                        TTBlocks.COPPER_DOOR.get(),
                        TTBlocks.EXPOSED_COPPER_DOOR.get(),
                        TTBlocks.WEATHERED_COPPER_DOOR.get(),
                        TTBlocks.OXIDIZED_COPPER_DOOR.get(),

                        TTBlocks.WAXED_COPPER_DOOR.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_DOOR.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_DOOR.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(),

                        TTBlocks.COPPER_TRAPDOOR.get(),
                        TTBlocks.EXPOSED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WEATHERED_COPPER_TRAPDOOR.get(),
                        TTBlocks.OXIDIZED_COPPER_TRAPDOOR.get(),

                        TTBlocks.WAXED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(),
                        TTBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get()
                );
        this.tag(BlockTags.WALLS).add(
                TTBlocks.TUFF_WALL.get(),
                TTBlocks.POLISHED_TUFF_WALL.get(),
                TTBlocks.TUFF_BRICK_WALL.get()
        );
    }
}
