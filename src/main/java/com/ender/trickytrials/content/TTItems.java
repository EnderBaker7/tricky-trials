package com.ender.trickytrials.content;

import com.ender.trickytrials.TrickyTrials;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TTItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, TrickyTrials.MODID);
    private static RegistryObject<Item> registerBlockItems(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> registerDoorBlockItems(String name, RegistryObject<DoorBlock> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> registerTrapdoorBlockItems(String name, RegistryObject<TrapDoorBlock> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> registerStairBlockItems(String name, RegistryObject<StairBlock> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> registerSlabBlockItems(String name, RegistryObject<SlabBlock> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> registerWallBlockItems(String name, RegistryObject<WallBlock> block) {
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

    public static final RegistryObject<Item> COPPER_DOOR = registerDoorBlockItems("copper_door", TTBlocks.COPPER_DOOR);
    public static final RegistryObject<Item> EXPOSED_COPPER_DOOR = registerDoorBlockItems("exposed_copper_door", TTBlocks.EXPOSED_COPPER_DOOR);
    public static final RegistryObject<Item> WEATHERED_COPPER_DOOR = registerDoorBlockItems("weathered_copper_door", TTBlocks.WEATHERED_COPPER_DOOR);
    public static final RegistryObject<Item> OXIDIZED_COPPER_DOOR = registerDoorBlockItems("oxidized_copper_door", TTBlocks.OXIDIZED_COPPER_DOOR);
    public static final RegistryObject<Item> WAXED_COPPER_DOOR = registerDoorBlockItems("waxed_copper_door", TTBlocks.WAXED_COPPER_DOOR);
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_DOOR = registerDoorBlockItems("waxed_exposed_copper_door", TTBlocks.WAXED_EXPOSED_COPPER_DOOR);
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_DOOR = registerDoorBlockItems("waxed_weathered_copper_door", TTBlocks.WAXED_WEATHERED_COPPER_DOOR);
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_DOOR = registerDoorBlockItems("waxed_oxidized_copper_door", TTBlocks.WAXED_OXIDIZED_COPPER_DOOR);

    public static final RegistryObject<Item> COPPER_TRAPDOOR = registerTrapdoorBlockItems("copper_trapdoor", TTBlocks.COPPER_TRAPDOOR);
    public static final RegistryObject<Item> EXPOSED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("exposed_copper_trapdoor", TTBlocks.EXPOSED_COPPER_TRAPDOOR);
    public static final RegistryObject<Item> WEATHERED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("weathered_copper_trapdoor", TTBlocks.WEATHERED_COPPER_TRAPDOOR);
    public static final RegistryObject<Item> OXIDIZED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("oxidized_copper_trapdoor", TTBlocks.OXIDIZED_COPPER_TRAPDOOR);
    public static final RegistryObject<Item> WAXED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("waxed_copper_trapdoor", TTBlocks.WAXED_COPPER_TRAPDOOR);
    public static final RegistryObject<Item> WAXED_EXPOSED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("waxed_exposed_copper_trapdoor", TTBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR);
    public static final RegistryObject<Item> WAXED_WEATHERED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("waxed_weathered_copper_trapdoor", TTBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR);
    public static final RegistryObject<Item> WAXED_OXIDIZED_COPPER_TRAPDOOR = registerTrapdoorBlockItems("waxed_oxidized_copper_trapdoor", TTBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR);

    public static final RegistryObject<Item> TUFF_STAIRS = registerStairBlockItems("tuff_stairs", TTBlocks.TUFF_STAIRS);
    public static final RegistryObject<Item> TUFF_SLAB = registerSlabBlockItems("tuff_slab", TTBlocks.TUFF_SLAB);
    public static final RegistryObject<Item> TUFF_WALL = registerWallBlockItems("tuff_wall", TTBlocks.TUFF_WALL);

    public static final RegistryObject<Item> POLISHED_TUFF = registerBlockItems("polished_tuff", TTBlocks.POLISHED_TUFF);
    public static final RegistryObject<Item> POLISHED_TUFF_STAIRS = registerStairBlockItems("polished_tuff_stairs", TTBlocks.POLISHED_TUFF_STAIRS);
    public static final RegistryObject<Item> POLISHED_TUFF_SLAB = registerSlabBlockItems("polished_tuff_slab", TTBlocks.POLISHED_TUFF_SLAB);
    public static final RegistryObject<Item> POLISHED_TUFF_WALL = registerWallBlockItems("polished_tuff_wall", TTBlocks.POLISHED_TUFF_WALL);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
