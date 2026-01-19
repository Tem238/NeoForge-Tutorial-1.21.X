package com.chimugame.tutorialmod.datagen.client;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.block.ModBlocks;
import com.chimugame.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Locale;

public class ModJAJPLanguageProvider extends LanguageProvider
{
    public ModJAJPLanguageProvider(PackOutput output)
    {
        super(output, TutorialMod.MOD_ID, Locale.JAPAN.toString().toLowerCase());
    }

    @Override
    protected void addTranslations()
    {
        addItem(ModItems.RAINBOW_DIAMOND, "レインボーダイヤ");

        addBlock(ModBlocks.RAINBOW_DIAMOND_BLOCK, "レインボーダイヤブロック");
        addBlock(ModBlocks.RAINBOW_DIAMOND_ORE, "レインボーダイヤ鉱石");
        addBlock(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE, "深層レインボーダイヤ鉱石");

        add("creativetabs.tutorial_tab", "練習");
    }
}
