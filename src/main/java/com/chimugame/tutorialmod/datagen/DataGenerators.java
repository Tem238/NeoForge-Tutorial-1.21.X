package com.chimugame.tutorialmod.datagen;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.datagen.client.ModBlockStateProvider;
import com.chimugame.tutorialmod.datagen.client.ModENUSLanguageProvider;
import com.chimugame.tutorialmod.datagen.client.ModItemModelProvider;
import com.chimugame.tutorialmod.datagen.client.ModJAJPLanguageProvider;
import com.chimugame.tutorialmod.datagen.server.ModBlockLootTableProvider;
import com.chimugame.tutorialmod.datagen.server.ModBlockTagProvider;
import com.chimugame.tutorialmod.datagen.server.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ブロックタグ
        ModBlockTagProvider blockTagProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);

        // ルートテーブル
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        // レシピ
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        // アイテムモデル
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        // ブロックモデル
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        // 言語ファイル
        generator.addProvider(event.includeClient(), new ModJAJPLanguageProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModENUSLanguageProvider(packOutput));
    }
}
