package com.ender.trickytrials.content;

import com.ender.trickytrials.TrickyTrials;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TTItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, TrickyTrials.MODID);
    public static RegistryObject<Item> registerBlockItems(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static final RegistryObject<Item> CHISELED_COPPER = registerBlockItems("chiseled_copper", TTBlocks.CHISELED_COPPER);
    public static final RegistryObject<Item> EXPOSED_CHISELED_COPPER = registerBlockItems("exposed_chiseled_copper", TTBlocks.EXPOSED_CHISELED_COPPER);
    public static final RegistryObject<Item> WEATHERED_CHISELED_COPPER = registerBlockItems("weathered_chiseled_copper", TTBlocks.WEATHERED_CHISELED_COPPER);
    public static final RegistryObject<Item> OXIDIZED_CHISELED_COPPER = registerBlockItems("oxidized_chiseled_copper", TTBlocks.OXIDIZED_CHISELED_COPPER);
    public static final RegistryObject<Item> WAXED_CHISELED_COPPER = registerBlockItems("waxed_chiseled_copper", TTBlocks.WAXED_CHISELED_COPPER);
    public static final RegistryObject<Item> WAXED_EXPOSED_CHISELED_COPPER = registerBlockItems("waxed_exposed_chiseled_copper", TTBlocks.WAXED_EXPOSED_CHISELED_COPPER);
    public static final RegistryObject<Item> WAXED_WEATHERED_CHISELED_COPPER = registerBlockItems("waxed_weathered_chiseled_copper", TTBlocks.WAXED_WEATHERED_CHISELED_COPPER);
    public static final RegistryObject<Item> WAXED_OXIDIZED_CHISELED_COPPER = registerBlockItems("waxed_oxidized_chiseled_copper", TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER);

    public static final RegistryObject<Item> COPPER_BULB = registerBlockItems("copper_bulb", TTBlocks.COPPER_BULB);
    public static final RegistryObject<Item> EXPOSED_COPPER_BULB = registerBlockItems("exposed_copper_bulb", TTBlocks.EXPOSED_COPPER_BULB);
    public static final RegistryObject<Item> WEATHERED_COPPER_BULB = registerBlockItems("weathered_copper_bulb", TTBlocks.WEATHERED_COPPER_BULB);
    public static final RegistryObject<Item> OXIDIZED_COPPER_BULB = registerBlockItems("oxidized_copper_bulb", TTBlocks.OXIDIZED_COPPER_BULB);
    public static final RegistryObject<Item> WAXED_COPPER_BULB = registerBlockItems("waxed_copper_bulb", TTBlocks.WAXED_COPPER_BULB);
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_BULB = registerBlockItems("waxed_exposed_copper_bulb", TTBlocks.WAXED_EXPOSED_COPPER_BULB);
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_BULB = registerBlockItems("waxed_weathered_copper_bulb", TTBlocks.WAXED_WEATHERED_COPPER_BULB);
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_BULB = registerBlockItems("waxed_oxidized_copper_bulb", TTBlocks.WAXED_OXIDIZED_COPPER_BULB);
    public static final RegistryObject<Item> COPPER_GRATE = registerBlockItems("copper_grate", TTBlocks.COPPER_GRATE);
    public static final RegistryObject<Item> EXPOSED_COPPER_GRATE = registerBlockItems("exposed_copper_grate", TTBlocks.EXPOSED_COPPER_GRATE);
    public static final RegistryObject<Item> WEATHERED_COPPER_GRATE = registerBlockItems("weathered_copper_grate", TTBlocks.WEATHERED_COPPER_GRATE);
    public static final RegistryObject<Item> OXIDIZED_COPPER_GRATE = registerBlockItems("oxidized_copper_grate", TTBlocks.OXIDIZED_COPPER_GRATE);
    public static final RegistryObject<Item> WAXED_COPPER_GRATE = registerBlockItems("waxed_copper_grate", TTBlocks.WAXED_COPPER_GRATE);
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_GRATE = registerBlockItems("waxed_exposed_copper_grate", TTBlocks.WAXED_EXPOSED_COPPER_GRATE);
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_GRATE = registerBlockItems("waxed_weathered_copper_grate", TTBlocks.WAXED_WEATHERED_COPPER_GRATE);
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_GRATE = registerBlockItems("waxed_oxidized_copper_grate", TTBlocks.WAXED_OXIDIZED_COPPER_GRATE);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
