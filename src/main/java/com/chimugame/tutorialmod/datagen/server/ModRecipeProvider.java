package com.chimugame.tutorialmod.datagen.server;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.block.ModBlocks;
import com.chimugame.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput)
    {
        // レインボーダイヤとレインボーダイヤブロックのレシピを追加
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.RAINBOW_DIAMOND, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAINBOW_DIAMOND_BLOCK);

        // レインボーダイヤが精錬できるブロック、アイテムのリスト
        List<ItemLike> RAINBOW_DIAMOND = List.of(
                ModBlocks.RAINBOW_DIAMOND_ORE,
                ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE);
        // かまど精錬レシピの追加
        oreSmelting(recipeOutput, RAINBOW_DIAMOND, RecipeCategory.MISC, ModItems.RAINBOW_DIAMOND.get(), .25f, 200, "rainbow_diamond");
        // 溶鉱炉精錬レシピの追加
        oreBlasting(recipeOutput, RAINBOW_DIAMOND, RecipeCategory.MISC, ModItems.RAINBOW_DIAMOND.get(), .25f, 100, "rainbow_diamond");
    }

    // RecipeProvider.oreSmeltingをそのままコピー
    protected static void oreSmelting(
            RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group)
    {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_smelting"
        );
    }

    // RecipeProvider.oreBlastingをそのままコピー
    protected static void oreBlasting(
            RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group)
    {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_blasting"
        );
    }

    // RecipeProvider.oreCookingをコピーし、tutorialmodフォルダー下に生成されるようにidの部分に「TutorialMod.MOD_ID:」を接頭語として書き加える
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory,
                                                                       List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix)
    {
        for (ItemLike itemlike : ingredients)
        {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TutorialMod.MOD_ID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }
    }
}
