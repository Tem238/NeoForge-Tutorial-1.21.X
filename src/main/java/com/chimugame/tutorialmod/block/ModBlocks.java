package com.chimugame.tutorialmod.block;

import com.chimugame.tutorialmod.TutorialMod;
import com.chimugame.tutorialmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks
{
    // 追加するブロックの登録簿
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TutorialMod.MOD_ID);

    // -----追加するブロック群-----
    public static final DeferredBlock<Block> RAINBOW_DIAMOND_BLOCK =
            registerBlock("rainbow_diamond_block",
                    () -> new Block(BlockBehaviour.Properties.of()
                            .strength(4.f)                  // ブロックの硬さ
                            .requiresCorrectToolForDrops()  // 適正ツールでなければアイテムがドロップしない
                            .sound(SoundType.AMETHYST)      // 設置音
                    ));
    public static final DeferredBlock<Block> RAINBOW_DIAMOND_ORE =
            registerBlock("rainbow_diamond_ore",
                    () -> new DropExperienceBlock(      // 経験値をドロップする経験値
                            UniformInt.of(2,4),         // 2~4の経験値をランダムにドロップ
                            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));     // ダイヤモンド鉱石の特徴をコピー
    public static final DeferredBlock<Block> RAINBOW_DIAMOND_DEEPSLATE_ORE =
            registerBlock("rainbow_diamond_deepslate_ore",
                    () -> new DropExperienceBlock(      // 経験値をドロップする経験値
                            UniformInt.of(3,6),         // 3~6の経験値をランダムにドロップ
                            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));     // 深層ダイヤモンド鉱石の特徴をコピー
    // -------------------------

    /// 登録簿へブロックを追加する
    /// @param name ブロックID
    /// @param block 登録時に呼び出され、{@link T} 型のブロックを生成する {@link Supplier}
    /// @return 登録した {@link DeferredBlock}
    /// @param <T> 登録するブロックの型({@link Block} またはそのサブクラス)
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        // ブロックとしての登録
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        // アイテムとしての登録も必要
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    /// アイテムの登録簿へアイテムを追加する
    /// @param name アイテムID
    /// @param block 登録するアイテムに対応するブロック
    /// @param <T> {@code block} のブロック型({@link Block} またはそのサブクラス)
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        // 特に能力のないただのアイテムとして追加
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    /// ブロックの登録
    /// @param eventBus イベントバス
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
