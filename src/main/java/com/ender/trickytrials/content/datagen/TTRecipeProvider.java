package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.TrickyTrials;
import com.ender.trickytrials.content.TTItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class TTRecipeProvider extends RecipeProvider {

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(TrickyTrials.MODID, path);
    }

    public TTRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private static void buildChiseledRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, Item ing1, Item ing2, Item ing3, RegistryObject<Item> waxIng, String type) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get())
                .define('#', ing1)
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_" + type + "cut_copper_slab", has(ing1))
                .save(consumer, modLoc(type + "chiseled_copper"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing2), RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .unlockedBy("has_" + type + "copper_block", has(ing2))
                .save(consumer, modLoc(type + "chiseled_copper_from_" + type + "copper_block_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing3), RecipeCategory.BUILDING_BLOCKS, result.get())
                .unlockedBy("has_" + type + "cut_copper", has(ing3))
                .save(consumer, modLoc(type + "chiseled_copper_from_" + type + "cut_copper_stonecutting"));
        if (waxIng != null) {
            buildWaxedRecipes(consumer, result, waxIng);
        }
    }

    private static void buildBulbRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, Item ing, RegistryObject<Item> waxIng, String type) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get(), 4)
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
            buildWaxedRecipes(consumer, result, waxIng);
        }
    }

    private static void buildGrateRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, Item ing, RegistryObject<Item> waxIng, String type) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .define('#', ing)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_" + type + "copper_block", has(ing))
                .save(consumer, modLoc(type + "copper_grate"));
        if (waxIng != null) {
            buildWaxedRecipes(consumer, result, waxIng);
        }
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing), RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .unlockedBy("has_" + type + "copper_block", has(ing))
                .save(consumer, modLoc(type + "copper_grate_from_" + type + "copper_block_stonecutting"));
    }

    private static void buildWaxedRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> waxIng) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                .requires(waxIng.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_" + waxIng.getId().getPath(), has(waxIng.get()))
                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                .save(consumer, modLoc(result.getId().getPath() + "_from_honeycomb_crafting"));
    }

    private static void buildStairRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TTItems.TUFF_STAIRS.get(), 4)
                .define('#', Blocks.TUFF)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.BUILDING_BLOCKS, TTItems.TUFF_STAIRS.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc("tuff_stairs_from_tuff_stonecutting"));
    }

    private static void buildStairRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 4)
                .define('#', ing.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc(result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc(result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    private static void buildStairRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing, RegistryObject<Item> ing2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 4)
                .define('#', ing.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc(result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing2.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing2.getId().getPath(), has(ing2.get()))
                .save(consumer, modLoc(result.getId().getPath() + "_from_" + ing2.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc(result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    private static void buildSlabRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TTItems.TUFF_SLAB.get(), 6)
                .define('#', Blocks.TUFF)
                .pattern("###")
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.BUILDING_BLOCKS, TTItems.TUFF_SLAB.get(), 2)
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc("tuff_slab_from_tuff_stonecutting"));
    }

    private static void buildSlabRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .define('#', ing.get())
                .pattern("###")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get(), 2)
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get(), 2)
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc(result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    private static void buildSlabRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing, RegistryObject<Item> ing2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .define('#', ing.get())
                .pattern("###")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get(), 2)
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing2.get()), RecipeCategory.DECORATIONS, result.get(), 2)
                .unlockedBy("has_" + ing2.getId().getPath(), has(ing2.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing2.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get(), 2)
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc(result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    private static void buildWallRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TTItems.TUFF_WALL.get(), 6)
                .define('#', Blocks.TUFF)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, TTItems.TUFF_WALL.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc("tuff_wall_from_tuff_stonecutting"));
    }

    private static void buildWallRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .define('#', ing.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc(result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    private static void buildWallRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing, RegistryObject<Item> ing2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .define('#', ing.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing2.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing2.getId().getPath(), has(ing2.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing2.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc(result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    private static void build2x2Recipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TTItems.POLISHED_TUFF.get(), 4)
                .define('#', Blocks.TUFF)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, TTItems.POLISHED_TUFF.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc("polished_tuff_from_tuff_stonecutting"));
    }

    private static void build2x2Recipes(Consumer<FinishedRecipe> consumer, RegistryObject<Item> result, RegistryObject<Item> ing) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 4)
                .define('#', ing.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ing.get()), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_" + ing.getId().getPath(), has(ing.get()))
                .save(consumer, modLoc( result.getId().getPath() + "_from_" + ing.getId().getPath() + "_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TUFF), RecipeCategory.DECORATIONS, result.get())
                .unlockedBy("has_tuff", has(Blocks.TUFF))
                .save(consumer, modLoc( result.getId().getPath() + "_from_tuff_stonecutting"));
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        buildChiseledRecipes(consumer, TTItems.CHISELED_COPPER, Items.CUT_COPPER_SLAB, Items.COPPER_BLOCK, Items.CUT_COPPER, null, "");
        buildChiseledRecipes(consumer, TTItems.EXPOSED_CHISELED_COPPER, Items.EXPOSED_CUT_COPPER_SLAB, Items.EXPOSED_COPPER, Items.EXPOSED_CUT_COPPER, null, "exposed_");
        buildChiseledRecipes(consumer, TTItems.WEATHERED_CHISELED_COPPER, Items.WEATHERED_CUT_COPPER_SLAB,
                Items.WEATHERED_COPPER, Items.WEATHERED_CUT_COPPER, null, "weathered_");
        buildChiseledRecipes(consumer, TTItems.OXIDIZED_CHISELED_COPPER, Items.OXIDIZED_CUT_COPPER_SLAB,
                Items.OXIDIZED_COPPER, Items.OXIDIZED_CUT_COPPER, null, "oxidized_");

        buildChiseledRecipes(consumer, TTItems.WAXED_CHISELED_COPPER, Items.WAXED_CUT_COPPER_SLAB, Items.WAXED_COPPER_BLOCK, Items.WAXED_CUT_COPPER, TTItems.CHISELED_COPPER, "waxed_");
        buildChiseledRecipes(consumer, TTItems.WAXED_EXPOSED_CHISELED_COPPER, Items.WAXED_EXPOSED_CUT_COPPER_SLAB,
                Items.WAXED_EXPOSED_COPPER, Items.WAXED_EXPOSED_CUT_COPPER, TTItems.EXPOSED_CHISELED_COPPER, "waxed_exposed_");
        buildChiseledRecipes(consumer, TTItems.WAXED_WEATHERED_CHISELED_COPPER, Items.WAXED_WEATHERED_CUT_COPPER_SLAB,
                Items.WAXED_WEATHERED_COPPER, Items.WAXED_WEATHERED_CUT_COPPER, TTItems.WEATHERED_CHISELED_COPPER, "waxed_weathered_");
        buildChiseledRecipes(consumer, TTItems.WAXED_OXIDIZED_CHISELED_COPPER, Items.WAXED_OXIDIZED_CUT_COPPER_SLAB,
                Items.WAXED_OXIDIZED_COPPER, Items.WAXED_OXIDIZED_CUT_COPPER, TTItems.OXIDIZED_CHISELED_COPPER, "waxed_oxidized_");

        buildBulbRecipes(consumer, TTItems.COPPER_BULB, Items.COPPER_BLOCK, null, "");
        buildBulbRecipes(consumer, TTItems.EXPOSED_COPPER_BULB, Items.EXPOSED_COPPER, null, "exposed_");
        buildBulbRecipes(consumer, TTItems.WEATHERED_COPPER_BULB, Items.WEATHERED_COPPER, null, "weathered_");
        buildBulbRecipes(consumer, TTItems.OXIDIZED_COPPER_BULB, Items.OXIDIZED_COPPER, null, "oxidized_");

        buildBulbRecipes(consumer, TTItems.WAXED_COPPER_BULB, Items.WAXED_COPPER_BLOCK, TTItems.COPPER_BULB, "waxed_");
        buildBulbRecipes(consumer, TTItems.WAXED_EXPOSED_COPPER_BULB, Items.WAXED_EXPOSED_COPPER, TTItems.EXPOSED_COPPER_BULB, "waxed_exposed_");
        buildBulbRecipes(consumer, TTItems.WAXED_WEATHERED_COPPER_BULB, Items.WAXED_WEATHERED_COPPER, TTItems.WEATHERED_COPPER_BULB, "waxed_weathered_");
        buildBulbRecipes(consumer, TTItems.WAXED_OXIDIZED_COPPER_BULB, Items.WAXED_OXIDIZED_COPPER, TTItems.OXIDIZED_COPPER_BULB, "waxed_oxidized_");

        buildGrateRecipes(consumer, TTItems.COPPER_GRATE, Items.COPPER_BLOCK, null, "");
        buildGrateRecipes(consumer, TTItems.EXPOSED_COPPER_GRATE, Items.EXPOSED_COPPER, null, "exposed_");
        buildGrateRecipes(consumer, TTItems.WEATHERED_COPPER_GRATE, Items.WEATHERED_COPPER, null, "weathered_");
        buildGrateRecipes(consumer, TTItems.OXIDIZED_COPPER_GRATE, Items.OXIDIZED_COPPER, null, "oxidized_");

        buildGrateRecipes(consumer, TTItems.WAXED_COPPER_GRATE, Items.WAXED_COPPER_BLOCK, TTItems.COPPER_GRATE, "waxed_");
        buildGrateRecipes(consumer, TTItems.WAXED_EXPOSED_COPPER_GRATE, Items.WAXED_EXPOSED_COPPER, TTItems.EXPOSED_COPPER_GRATE, "waxed_exposed_");
        buildGrateRecipes(consumer, TTItems.WAXED_WEATHERED_COPPER_GRATE, Items.WAXED_WEATHERED_COPPER, TTItems.WEATHERED_COPPER_GRATE, "waxed_weathered_");
        buildGrateRecipes(consumer, TTItems.WAXED_OXIDIZED_COPPER_GRATE, Items.WAXED_OXIDIZED_COPPER, TTItems.OXIDIZED_COPPER_GRATE, "waxed_oxidized_");

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TTItems.COPPER_DOOR.get(), 3)
                .define('#', Items.COPPER_INGOT)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(consumer);

        buildWaxedRecipes(consumer, TTItems.WAXED_COPPER_DOOR, TTItems.COPPER_DOOR);
        buildWaxedRecipes(consumer, TTItems.WAXED_EXPOSED_COPPER_DOOR, TTItems.EXPOSED_COPPER_DOOR);
        buildWaxedRecipes(consumer, TTItems.WAXED_WEATHERED_COPPER_DOOR, TTItems.WEATHERED_COPPER_DOOR);
        buildWaxedRecipes(consumer, TTItems.WAXED_OXIDIZED_COPPER_DOOR, TTItems.OXIDIZED_COPPER_DOOR);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TTItems.COPPER_TRAPDOOR.get(), 2)
                .define('#', Items.COPPER_INGOT)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(consumer);

        buildWaxedRecipes(consumer, TTItems.WAXED_COPPER_TRAPDOOR, TTItems.COPPER_TRAPDOOR);
        buildWaxedRecipes(consumer, TTItems.WAXED_EXPOSED_COPPER_TRAPDOOR, TTItems.EXPOSED_COPPER_TRAPDOOR);
        buildWaxedRecipes(consumer, TTItems.WAXED_WEATHERED_COPPER_TRAPDOOR, TTItems.WEATHERED_COPPER_TRAPDOOR);
        buildWaxedRecipes(consumer, TTItems.WAXED_OXIDIZED_COPPER_TRAPDOOR, TTItems.OXIDIZED_COPPER_TRAPDOOR);

        buildStairRecipes(consumer);
        buildSlabRecipes(consumer);
        buildWallRecipes(consumer);

        build2x2Recipes(consumer);
        buildStairRecipes(consumer, TTItems.POLISHED_TUFF_STAIRS, TTItems.POLISHED_TUFF);
        buildSlabRecipes(consumer, TTItems.POLISHED_TUFF_SLAB, TTItems.POLISHED_TUFF);
        buildWallRecipes(consumer, TTItems.POLISHED_TUFF_WALL, TTItems.POLISHED_TUFF);

        build2x2Recipes(consumer, TTItems.TUFF_BRICKS, TTItems.POLISHED_TUFF);
        buildStairRecipes(consumer, TTItems.TUFF_BRICK_STAIRS, TTItems.TUFF_BRICKS, TTItems.POLISHED_TUFF);
        buildSlabRecipes(consumer, TTItems.TUFF_BRICK_SLAB, TTItems.TUFF_BRICKS, TTItems.POLISHED_TUFF);
        buildWallRecipes(consumer, TTItems.TUFF_BRICK_WALL, TTItems.TUFF_BRICKS, TTItems.POLISHED_TUFF);
    }
}
