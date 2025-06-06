package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.content.TTBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class TTLootTableProvider extends LootTableProvider {
    private static final List<SubProviderEntry> SUB_PROVIDER_ENTRIES = List.of(
            new SubProviderEntry(TTBlockLootSubProvider::new, LootContextParamSets.BLOCK)
    );

    public TTLootTableProvider(PackOutput output) {
        super(output, Set.of(), SUB_PROVIDER_ENTRIES);
    }

    private static class TTBlockLootSubProvider extends BlockLootSubProvider {
        public TTBlockLootSubProvider() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            this.dropSelf(TTBlocks.CHISELED_COPPER.get());
            this.dropSelf(TTBlocks.EXPOSED_CHISELED_COPPER.get());
            this.dropSelf(TTBlocks.WEATHERED_CHISELED_COPPER.get());
            this.dropSelf(TTBlocks.OXIDIZED_CHISELED_COPPER.get());

            this.dropSelf(TTBlocks.WAXED_CHISELED_COPPER.get());
            this.dropSelf(TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get());
            this.dropSelf(TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get());
            this.dropSelf(TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get());

            this.dropSelf(TTBlocks.COPPER_BULB.get());
            this.dropSelf(TTBlocks.EXPOSED_COPPER_BULB.get());
            this.dropSelf(TTBlocks.WEATHERED_COPPER_BULB.get());
            this.dropSelf(TTBlocks.OXIDIZED_COPPER_BULB.get());

            this.dropSelf(TTBlocks.WAXED_COPPER_BULB.get());
            this.dropSelf(TTBlocks.WAXED_EXPOSED_COPPER_BULB.get());
            this.dropSelf(TTBlocks.WAXED_WEATHERED_COPPER_BULB.get());
            this.dropSelf(TTBlocks.WAXED_OXIDIZED_COPPER_BULB.get());

            this.dropSelf(TTBlocks.COPPER_GRATE.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return TTBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }
}
