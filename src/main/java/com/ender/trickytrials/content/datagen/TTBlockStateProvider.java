package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.content.TTBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class TTBlockStateProvider extends BlockStateProvider {
    public TTBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    private void registerBulbStatesAndModels(RegistryObject<Block> block, String oxiType, String waxed) {
        getVariantBuilder(block.get())
                .partialState().with(LIT, false).with(POWERED, false)
                .modelForState().modelFile(models().cubeAll(waxed + oxiType + "copper_bulb_off", modLoc("block/" + oxiType + "copper_bulb"))).addModel()
                .partialState().with(LIT, false).with(POWERED, true)
                .modelForState().modelFile(models().cubeAll(waxed + oxiType + "copper_bulb_off_powered", modLoc("block/" + oxiType + "copper_bulb_powered"))).addModel()
                .partialState().with(LIT, true).with(POWERED, false)
                .modelForState().modelFile(models().cubeAll(waxed + oxiType + "copper_bulb_on", modLoc("block/" + oxiType + "copper_bulb_lit"))).addModel()
                .partialState().with(LIT, true).with(POWERED, true)
                .modelForState().modelFile(models().cubeAll(waxed + oxiType + "copper_bulb_on_powered", modLoc("block/" + oxiType + "copper_bulb_lit_powered"))).addModel();
    }

    private static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(TTBlocks.CHISELED_COPPER.get());
        simpleBlock(TTBlocks.EXPOSED_CHISELED_COPPER.get());
        simpleBlock(TTBlocks.WEATHERED_CHISELED_COPPER.get());
        simpleBlock(TTBlocks.OXIDIZED_CHISELED_COPPER.get());

        simpleBlock(TTBlocks.WAXED_CHISELED_COPPER.get(), models().cubeAll("waxed_chiseled_copper", modLoc("block/chiseled_copper")));
        simpleBlock(TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(), models().cubeAll("waxed_exposed_chiseled_copper", modLoc("block/exposed_chiseled_copper")));
        simpleBlock(TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(), models().cubeAll("waxed_weathered_chiseled_copper", modLoc("block/weathered_chiseled_copper")));
        simpleBlock(TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(), models().cubeAll("waxed_oxidized_chiseled_copper", modLoc("block/oxidized_chiseled_copper")));

        registerBulbStatesAndModels(TTBlocks.COPPER_BULB, "", "");
        registerBulbStatesAndModels(TTBlocks.EXPOSED_COPPER_BULB, "exposed_", "");
        registerBulbStatesAndModels(TTBlocks.WEATHERED_COPPER_BULB, "weathered_", "");
        registerBulbStatesAndModels(TTBlocks.OXIDIZED_COPPER_BULB, "oxidized_", "");

        registerBulbStatesAndModels(TTBlocks.WAXED_COPPER_BULB, "", "waxed_");
        registerBulbStatesAndModels(TTBlocks.WAXED_EXPOSED_COPPER_BULB, "exposed_", "waxed_");
        registerBulbStatesAndModels(TTBlocks.WAXED_WEATHERED_COPPER_BULB, "weathered_", "waxed_");
        registerBulbStatesAndModels(TTBlocks.WAXED_OXIDIZED_COPPER_BULB, "oxidized_", "waxed_");

        simpleBlock(TTBlocks.COPPER_GRATE.get());
        simpleBlock(TTBlocks.EXPOSED_COPPER_GRATE.get());
        simpleBlock(TTBlocks.WEATHERED_COPPER_GRATE.get());
        simpleBlock(TTBlocks.OXIDIZED_COPPER_GRATE.get());

        simpleBlock(TTBlocks.WAXED_COPPER_GRATE.get(), models().cubeAll("waxed_copper_grate", modLoc("block/copper_grate")));
        simpleBlock(TTBlocks.WAXED_EXPOSED_COPPER_GRATE.get(), models().cubeAll("waxed_exposed_copper_grate", modLoc("block/exposed_copper_grate")));
        simpleBlock(TTBlocks.WAXED_WEATHERED_COPPER_GRATE.get(), models().cubeAll("waxed_weathered_copper_grate", modLoc("block/weathered_copper_grate")));
        simpleBlock(TTBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(), models().cubeAll("waxed_oxidized_copper_grate", modLoc("block/oxidized_copper_grate")));

        doorBlock(TTBlocks.COPPER_DOOR.get(), modLoc("block/copper_door_bottom"), modLoc("block/copper_door_top"));
        doorBlock(TTBlocks.EXPOSED_COPPER_DOOR.get(), modLoc("block/exposed_copper_door_bottom"), modLoc("block/exposed_copper_door_top"));
        doorBlock(TTBlocks.WEATHERED_COPPER_DOOR.get(), modLoc("block/weathered_copper_door_bottom"), modLoc("block/weathered_copper_door_top"));
        doorBlock(TTBlocks.OXIDIZED_COPPER_DOOR.get(), modLoc("block/oxidized_copper_door_bottom"), modLoc("block/oxidized_copper_door_top"));

        doorBlock(TTBlocks.WAXED_COPPER_DOOR.get(), modLoc("block/copper_door_bottom"), modLoc("block/copper_door_top"));
        doorBlock(TTBlocks.WAXED_EXPOSED_COPPER_DOOR.get(), modLoc("block/exposed_copper_door_bottom"), modLoc("block/exposed_copper_door_top"));
        doorBlock(TTBlocks.WAXED_WEATHERED_COPPER_DOOR.get(), modLoc("block/weathered_copper_door_bottom"), modLoc("block/weathered_copper_door_top"));
        doorBlock(TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(), modLoc("block/oxidized_copper_door_bottom"), modLoc("block/oxidized_copper_door_top"));

        trapdoorBlock(TTBlocks.COPPER_TRAPDOOR.get(), modLoc("block/copper_trapdoor"), true);
        trapdoorBlock(TTBlocks.EXPOSED_COPPER_TRAPDOOR.get(), modLoc("block/exposed_copper_trapdoor"), true);
        trapdoorBlock(TTBlocks.WEATHERED_COPPER_TRAPDOOR.get(), modLoc("block/weathered_copper_trapdoor"), true);
        trapdoorBlock(TTBlocks.OXIDIZED_COPPER_TRAPDOOR.get(), modLoc("block/oxidized_copper_trapdoor"), true);

        trapdoorBlock(TTBlocks.WAXED_COPPER_TRAPDOOR.get(), modLoc("block/copper_trapdoor"), true);
        trapdoorBlock(TTBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(), modLoc("block/exposed_copper_trapdoor"), true);
        trapdoorBlock(TTBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(), modLoc("block/weathered_copper_trapdoor"), true);
        trapdoorBlock(TTBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(), modLoc("block/oxidized_copper_trapdoor"), true);

        stairsBlock(TTBlocks.TUFF_STAIRS.get(), mcLoc("block/tuff"));
        slabBlock(TTBlocks.TUFF_SLAB.get(), mcLoc("block/tuff"), mcLoc("block/tuff"));
        wallBlock(TTBlocks.TUFF_WALL.get(), mcLoc("block/tuff"));

        simpleBlock(TTBlocks.POLISHED_TUFF.get());
        stairsBlock(TTBlocks.POLISHED_TUFF_STAIRS.get(), modLoc("block/polished_tuff"));
        slabBlock(TTBlocks.POLISHED_TUFF_SLAB.get(), modLoc("block/polished_tuff"), modLoc("block/polished_tuff"));
        wallBlock(TTBlocks.POLISHED_TUFF_WALL.get(), modLoc("block/polished_tuff"));

        simpleBlock(TTBlocks.TUFF_BRICKS.get());
        stairsBlock(TTBlocks.TUFF_BRICK_STAIRS.get(), modLoc("block/tuff_bricks"));
        slabBlock(TTBlocks.TUFF_BRICK_SLAB.get(), modLoc("block/tuff_bricks"), modLoc("block/tuff_bricks"));
        wallBlock(TTBlocks.TUFF_BRICK_WALL.get(), modLoc("block/tuff_bricks"));

        logBlock(TTBlocks.CHISELED_TUFF.get());
        logBlock(TTBlocks.CHISELED_TUFF_BRICKS.get());
    }
}