package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.content.TTItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TTItemModelProvider extends ItemModelProvider {
    public TTItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    private void registerDoorItemModel(String name) {
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    private void registerDoorItemModel(String mName, String tName) {
        withExistingParent(mName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + tName + "copper_door"));
    }

    @Override
    protected void registerModels() {
        withExistingParent(TTItems.CHISELED_COPPER.getId().getPath(), modLoc("block/chiseled_copper"));
        withExistingParent(TTItems.EXPOSED_CHISELED_COPPER.getId().getPath(), modLoc("block/exposed_chiseled_copper"));
        withExistingParent(TTItems.WEATHERED_CHISELED_COPPER.getId().getPath(), modLoc("block/weathered_chiseled_copper"));
        withExistingParent(TTItems.OXIDIZED_CHISELED_COPPER.getId().getPath(), modLoc("block/oxidized_chiseled_copper"));

        withExistingParent(TTItems.WAXED_CHISELED_COPPER.getId().getPath(), modLoc("block/waxed_chiseled_copper"));
        withExistingParent(TTItems.WAXED_EXPOSED_CHISELED_COPPER.getId().getPath(), modLoc("block/waxed_exposed_chiseled_copper"));
        withExistingParent(TTItems.WAXED_WEATHERED_CHISELED_COPPER.getId().getPath(), modLoc("block/waxed_weathered_chiseled_copper"));
        withExistingParent(TTItems.WAXED_OXIDIZED_CHISELED_COPPER.getId().getPath(), modLoc("block/waxed_oxidized_chiseled_copper"));

        withExistingParent(TTItems.COPPER_BULB.getId().getPath(), modLoc("block/copper_bulb_off"));
        withExistingParent(TTItems.EXPOSED_COPPER_BULB.getId().getPath(), modLoc("block/exposed_copper_bulb_off"));
        withExistingParent(TTItems.WEATHERED_COPPER_BULB.getId().getPath(), modLoc("block/weathered_copper_bulb_off"));
        withExistingParent(TTItems.OXIDIZED_COPPER_BULB.getId().getPath(), modLoc("block/oxidized_copper_bulb_off"));

        withExistingParent(TTItems.WAXED_COPPER_BULB.getId().getPath(), modLoc("block/waxed_copper_bulb_off"));
        withExistingParent(TTItems.WAXED_EXPOSED_COPPER_BULB.getId().getPath(), modLoc("block/waxed_exposed_copper_bulb_off"));
        withExistingParent(TTItems.WAXED_WEATHERED_COPPER_BULB.getId().getPath(), modLoc("block/waxed_weathered_copper_bulb_off"));
        withExistingParent(TTItems.WAXED_OXIDIZED_COPPER_BULB.getId().getPath(), modLoc("block/waxed_oxidized_copper_bulb_off"));

        withExistingParent(TTItems.COPPER_GRATE.getId().getPath(), modLoc("block/copper_grate"));
        withExistingParent(TTItems.EXPOSED_COPPER_GRATE.getId().getPath(), modLoc("block/exposed_copper_grate"));
        withExistingParent(TTItems.WEATHERED_COPPER_GRATE.getId().getPath(), modLoc("block/weathered_copper_grate"));
        withExistingParent(TTItems.OXIDIZED_COPPER_GRATE.getId().getPath(), modLoc("block/oxidized_copper_grate"));

        withExistingParent(TTItems.WAXED_COPPER_GRATE.getId().getPath(), modLoc("block/waxed_copper_grate"));
        withExistingParent(TTItems.WAXED_EXPOSED_COPPER_GRATE.getId().getPath(), modLoc("block/waxed_exposed_copper_grate"));
        withExistingParent(TTItems.WAXED_WEATHERED_COPPER_GRATE.getId().getPath(), modLoc("block/waxed_weathered_copper_grate"));
        withExistingParent(TTItems.WAXED_OXIDIZED_COPPER_GRATE.getId().getPath(), modLoc("block/waxed_oxidized_copper_grate"));

        registerDoorItemModel(TTItems.COPPER_DOOR.getId().getPath());
        registerDoorItemModel(TTItems.EXPOSED_COPPER_DOOR.getId().getPath());
        registerDoorItemModel(TTItems.WEATHERED_COPPER_DOOR.getId().getPath());
        registerDoorItemModel(TTItems.OXIDIZED_COPPER_DOOR.getId().getPath());

        registerDoorItemModel(TTItems.WAXED_COPPER_DOOR.getId().getPath(), "");
        registerDoorItemModel(TTItems.WAXED_EXPOSED_COPPER_DOOR.getId().getPath(), "exposed_");
        registerDoorItemModel(TTItems.WAXED_WEATHERED_COPPER_DOOR.getId().getPath(), "weathered_");
        registerDoorItemModel(TTItems.WAXED_OXIDIZED_COPPER_DOOR.getId().getPath(), "oxidized_");

        withExistingParent(TTItems.COPPER_TRAPDOOR.getId().getPath(), modLoc("block/copper_trapdoor_bottom"));
        withExistingParent(TTItems.EXPOSED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/exposed_copper_trapdoor_bottom"));
        withExistingParent(TTItems.WEATHERED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/weathered_copper_trapdoor_bottom"));
        withExistingParent(TTItems.OXIDIZED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/oxidized_copper_trapdoor_bottom"));

        withExistingParent(TTItems.WAXED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/waxed_copper_trapdoor_bottom"));
        withExistingParent(TTItems.WAXED_EXPOSED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/waxed_exposed_copper_trapdoor_bottom"));
        withExistingParent(TTItems.WAXED_WEATHERED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/waxed_weathered_copper_trapdoor_bottom"));
        withExistingParent(TTItems.WAXED_OXIDIZED_COPPER_TRAPDOOR.getId().getPath(), modLoc("block/waxed_oxidized_copper_trapdoor_bottom"));

        withExistingParent(TTItems.TUFF_STAIRS.getId().getPath(), modLoc("block/tuff_stairs"));
        withExistingParent(TTItems.TUFF_SLAB.getId().getPath(), modLoc("block/tuff_slab"));
        wallInventory(TTItems.TUFF_WALL.getId().getPath(), mcLoc("block/tuff"));

        withExistingParent(TTItems.POLISHED_TUFF.getId().getPath(), modLoc("block/polished_tuff"));
        withExistingParent(TTItems.POLISHED_TUFF_STAIRS.getId().getPath(), modLoc("block/polished_tuff_stairs"));
        withExistingParent(TTItems.POLISHED_TUFF_SLAB.getId().getPath(), modLoc("block/polished_tuff_slab"));
        wallInventory(TTItems.POLISHED_TUFF_WALL.getId().getPath(), modLoc("block/polished_tuff"));

        withExistingParent(TTItems.TUFF_BRICKS.getId().getPath(), modLoc("block/tuff_bricks"));
        withExistingParent(TTItems.TUFF_BRICK_STAIRS.getId().getPath(), modLoc("block/tuff_brick_stairs"));
        withExistingParent(TTItems.TUFF_BRICK_SLAB.getId().getPath(), modLoc("block/tuff_brick_slab"));
        wallInventory(TTItems.TUFF_BRICK_WALL.getId().getPath(), modLoc("block/tuff_bricks"));

        withExistingParent(TTItems.CHISELED_TUFF.getId().getPath(), modLoc("block/chiseled_tuff"));
        withExistingParent(TTItems.CHISELED_TUFF_BRICKS.getId().getPath(), modLoc("block/chiseled_tuff_bricks"));
    }
}
