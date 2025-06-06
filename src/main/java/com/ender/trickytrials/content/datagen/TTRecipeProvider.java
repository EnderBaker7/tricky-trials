package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.TrickyTrials;
import com.ender.trickytrials.content.TTBlocks;
import com.ender.trickytrials.content.TTItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.StringContent;
import java.util.function.Consumer;

public class TTRecipeProvider extends RecipeProvider {

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(TrickyTrials.MODID, path);
    }

    public TTRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private static void buildChiseledRecipes(Consumer<FinishedRecipe> consumer, Block result, Item ing1, Item ing2, Item ing3, String type) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result)
                .define('#', ing1)
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_" + type + "cut_copper_slab", has(ing1))
                .save(consumer, modLoc(type + "chiseled_copper"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing2), RecipeCategory.BUILDING_BLOCKS, result, 4)
                .unlockedBy("has_" + type + "copper_block", has(ing2))
                .save(consumer, modLoc(type + "chiseled_copper_from_" + type + "copper_block_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing3), RecipeCategory.BUILDING_BLOCKS, result)
                .unlockedBy("has_" + type + "cut_copper", has(ing3))
                .save(consumer, modLoc(type + "chiseled_copper_from_" + type + "cut_copper_stonecutting"));
    }

    private static void buildBulbRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> result, Item ing, RegistryObject<Item> waxIng, String type) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .define('#', ing)
                .define('$', Items.BLAZE_ROD)
                .define('R', Items.REDSTONE)
                .pattern(" # ")
                .pattern("#$#")
                .pattern(" R ")
                .unlockedBy("has_" + type + "copper_block", has(ing))
                .unlockedBy("has_redstone_dust", has(Items.REDSTONE))
                .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
                .save(consumer, modLoc(type + "copper_bulb"));
        if (waxIng != null) {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                    .requires(waxIng.get())
                    .requires(Items.HONEYCOMB)
                    .unlockedBy("has_" + waxIng.getId().getPath(), has(waxIng.get()))
                    .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                    .save(consumer, modLoc(type + "copper_bulb_from_honeycomb_crafting"));
        }
    }

    private static void buildGrateRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> result, Item ing, RegistryObject<Item> waxIng, String type) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .define('#', ing)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_" + type + "copper_block", has(ing))
                .save(consumer, modLoc(type + "copper_grate"));
        if (waxIng != null) {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                    .requires(waxIng.get())
                    .requires(Items.HONEYCOMB)
                    .unlockedBy("has_" + waxIng.getId().getPath(), has(waxIng.get()))
                    .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                    .save(consumer, modLoc(type + "copper_grate_from_honeycomb_crafting"));
        }
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing), RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .unlockedBy("has_" + type + "copper_block", has(ing))
                .save(consumer, modLoc(type + "copper_grate_from_" + type + "copper_block_stonecutting"));
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        buildChiseledRecipes(consumer, TTBlocks.CHISELED_COPPER.get(), Items.CUT_COPPER_SLAB, Items.COPPER_BLOCK, Items.CUT_COPPER, "");
        buildChiseledRecipes(consumer, TTBlocks.EXPOSED_CHISELED_COPPER.get(), Items.EXPOSED_CUT_COPPER_SLAB, Items.EXPOSED_COPPER, Items.EXPOSED_CUT_COPPER, "exposed_");
        buildChiseledRecipes(consumer, TTBlocks.WEATHERED_CHISELED_COPPER.get(), Items.WEATHERED_CUT_COPPER_SLAB,
                Items.WEATHERED_COPPER, Items.WEATHERED_CUT_COPPER, "weathered_");
        buildChiseledRecipes(consumer, TTBlocks.OXIDIZED_CHISELED_COPPER.get(), Items.OXIDIZED_CUT_COPPER_SLAB,
                Items.OXIDIZED_COPPER, Items.OXIDIZED_CUT_COPPER, "oxidized_");

        buildChiseledRecipes(consumer, TTBlocks.WAXED_CHISELED_COPPER.get(), Items.WAXED_CUT_COPPER_SLAB, Items.WAXED_COPPER_BLOCK, Items.WAXED_CUT_COPPER, "waxed_");
        buildChiseledRecipes(consumer, TTBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(), Items.WAXED_EXPOSED_CUT_COPPER_SLAB,
                Items.WAXED_EXPOSED_COPPER, Items.WAXED_EXPOSED_CUT_COPPER, "waxed_exposed_");
        buildChiseledRecipes(consumer, TTBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(), Items.WAXED_WEATHERED_CUT_COPPER_SLAB,
                Items.WAXED_WEATHERED_COPPER, Items.WAXED_WEATHERED_CUT_COPPER, "waxed_weathered_");
        buildChiseledRecipes(consumer, TTBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(), Items.WAXED_OXIDIZED_CUT_COPPER_SLAB,
                Items.WAXED_OXIDIZED_COPPER, Items.WAXED_OXIDIZED_CUT_COPPER, "waxed_oxidized_");

        buildBulbRecipes(consumer, TTBlocks.COPPER_BULB, Items.COPPER_BLOCK, null, "");
        buildBulbRecipes(consumer, TTBlocks.EXPOSED_COPPER_BULB, Items.EXPOSED_COPPER, null, "exposed_");
        buildBulbRecipes(consumer, TTBlocks.WEATHERED_COPPER_BULB, Items.WEATHERED_COPPER, null, "weathered_");
        buildBulbRecipes(consumer, TTBlocks.OXIDIZED_COPPER_BULB, Items.OXIDIZED_COPPER, null, "oxidized_");

        buildBulbRecipes(consumer, TTBlocks.WAXED_COPPER_BULB, Items.WAXED_COPPER_BLOCK, TTItems.COPPER_BULB, "waxed_");
        buildBulbRecipes(consumer, TTBlocks.WAXED_EXPOSED_COPPER_BULB, Items.WAXED_EXPOSED_COPPER, TTItems.EXPOSED_COPPER_BULB, "waxed_exposed_");
        buildBulbRecipes(consumer, TTBlocks.WAXED_WEATHERED_COPPER_BULB, Items.WAXED_WEATHERED_COPPER, TTItems.WEATHERED_COPPER_BULB, "waxed_weathered_");
        buildBulbRecipes(consumer, TTBlocks.WAXED_OXIDIZED_COPPER_BULB, Items.WAXED_OXIDIZED_COPPER, TTItems.OXIDIZED_COPPER_BULB, "waxed_oxidized_");

        buildGrateRecipes(consumer, TTBlocks.COPPER_GRATE, Items.COPPER_BLOCK, null, "");
        buildGrateRecipes(consumer, TTBlocks.EXPOSED_COPPER_GRATE, Items.EXPOSED_COPPER, null, "exposed_");
        buildGrateRecipes(consumer, TTBlocks.WEATHERED_COPPER_GRATE, Items.WEATHERED_COPPER, null, "weathered_");
        buildGrateRecipes(consumer, TTBlocks.OXIDIZED_COPPER_GRATE, Items.OXIDIZED_COPPER, null, "oxidized_");

        buildGrateRecipes(consumer, TTBlocks.WAXED_COPPER_GRATE, Items.WAXED_COPPER_BLOCK, TTItems.COPPER_GRATE, "waxed_");
        buildGrateRecipes(consumer, TTBlocks.WAXED_EXPOSED_COPPER_GRATE, Items.WAXED_EXPOSED_COPPER, TTItems.EXPOSED_COPPER_GRATE, "waxed_exposed_");
        buildGrateRecipes(consumer, TTBlocks.WAXED_WEATHERED_COPPER_GRATE, Items.WAXED_WEATHERED_COPPER, TTItems.WEATHERED_COPPER_GRATE, "waxed_weathered_");
        buildGrateRecipes(consumer, TTBlocks.WAXED_OXIDIZED_COPPER_GRATE, Items.WAXED_OXIDIZED_COPPER, TTItems.OXIDIZED_COPPER_GRATE, "waxed_oxidized_");
    }
}
