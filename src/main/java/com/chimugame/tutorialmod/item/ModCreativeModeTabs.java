package com.chimugame.tutorialmod.item;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final Supplier<CreativeModeTab> TUTORIAL_ITEM_TAB = CREATIVE_MODE_TAB.register("tutorial_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RAINBOW_DIAMOND.get()))
                    .title(Component.translatable("creativetabs.tutorial_tab"))
                    .displayItems((itemdisplayParameters, output) -> {
                        output.accept(ModItems.RAINBOW_DIAMOND);
                        output.accept(ModBlocks.RAINBOW_DIAMOND_BLOCK);
                        output.accept(ModBlocks.RAINBOW_DIAMOND_ORE);
                        output.accept(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE);
                    })
                    .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
