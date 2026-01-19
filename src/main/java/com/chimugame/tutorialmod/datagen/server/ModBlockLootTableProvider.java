package com.chimugame.tutorialmod.datagen.server;

import com.chimugame.tutorialmod.block.ModBlocks;
import com.chimugame.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    public ModBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        // そのままドロップするブロック
        dropSelf(ModBlocks.RAINBOW_DIAMOND_BLOCK.get());

        // 鉱石ブロック（シルクタッチでそのままドロップ、幸運エンチャントで複数ドロップ）
        add(ModBlocks.RAINBOW_DIAMOND_ORE.get(),
                block -> createOreDrop(ModBlocks.RAINBOW_DIAMOND_ORE.get(), ModItems.RAINBOW_DIAMOND.get()));

        // 鉱石ブロック＋通常でも複数個ドロップ（レッドストーンのような）
        add(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.RAINBOW_DIAMOND_DEEPSLATE_ORE.get(), ModItems.RAINBOW_DIAMOND.get(), 2, 5));
    }

    /**
     * 複数個ドロップするブロックを定義する関数
     * @param pBlock 定義するブロック
     * @param item {@code pBlock} からドロップするアイテム
     * @param minDrops 最小ドロップ数
     * @param maxDrops 最大ドロップ数
     * @return ルートテーブル
     */
    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops){
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(e -> (Block) e.value()).toList();
    }
}
