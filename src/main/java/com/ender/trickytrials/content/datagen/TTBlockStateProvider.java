package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.TrickyTrials;
import com.ender.trickytrials.content.CopperDoorBlock;
import com.ender.trickytrials.content.TTBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

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
    }
}