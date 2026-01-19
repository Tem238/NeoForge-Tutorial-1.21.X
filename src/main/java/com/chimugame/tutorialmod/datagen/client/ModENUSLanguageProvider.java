package com.chimugame.tutorialmod.datagen.client;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.block.ModBlocks;
import com.chimugame.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Locale;

public class ModENUSLanguageProvider extends LanguageProvider
{
    public ModENUSLanguageProvider(PackOutput output)
    {
        super(output, TutorialMod.MOD_ID, Locale.US.toString().toLowerCase());
    }

    @Override
    protected void addTranslations()
    {
        addItem(ModItems.RAINBOW_DIAMOND, "Rainbow Diamond");

        addBlock(ModBlocks.RAINBOW_DIAMOND_BLOCK, "Rainbow Diamond Block");
        addBlock(ModBlocks.RAINBOW_DIAMOND_ORE, "Rainbow Diamond Ore");
        addBlock(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE, "Rainbow Deepslate Diamond Ore");

        add("creativetabs.tutorial_tab", "Practice");
    }
}
