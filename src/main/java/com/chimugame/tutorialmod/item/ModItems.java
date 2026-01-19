package com.chimugame.tutorialmod.item;

import com.chimugame.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    // 追加するアイテムを登録するリストのようなもの
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);

    // -----追加するアイテム群-----
    public static final DeferredItem<Item> RAINBOW_DIAMOND = ITEMS.register("rainbow_diamond",
            () -> new Item(new Item.Properties()));
    // -------------------------

    /// アイテムの登録
    /// @param eventBus イベントバス
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
