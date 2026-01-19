package com.chimugame.tutorialmod.datagen.server;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider
{
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        // ツルハシが最適ツールのタグにブロック追加
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAINBOW_DIAMOND_BLOCK.get())
                .add(ModBlocks.RAINBOW_DIAMOND_ORE.get())
                .add(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE.get());

        // 鉄以上のツールが必要なタグにブロック追加
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAINBOW_DIAMOND_BLOCK.get())
                .add(ModBlocks.RAINBOW_DIAMOND_ORE.get())
                .add(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE.get());
    }
}
