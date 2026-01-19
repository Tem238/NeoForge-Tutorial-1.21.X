package com.chimugame.tutorialmod.datagen.client;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlockWithItem(ModBlocks.RAINBOW_DIAMOND_BLOCK);
        simpleBlockWithItem(ModBlocks.RAINBOW_DIAMOND_ORE);
        simpleBlockWithItem(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE);
    }

    /// [#simpleBlockWithItem]を実行するUtil関数
    /// @param deferredBlock 登録するブロック
    private void simpleBlockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
