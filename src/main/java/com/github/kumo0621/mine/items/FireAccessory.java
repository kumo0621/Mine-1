package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FireAccessory extends PurchasableSeitiItem implements IBuffItem {

    /**
     * 　固有アイテムの型を作成する
     */
    public FireAccessory() {
        super(Component.text("精錬のアクセサリー"), Material.GRAY_DYE, "Fire_speed", 3, 500000);
    }

    @Override
    public void applyBuff(Player player) {
        Inventory inventory = player.getInventory();

        // インベントリ内のアイテムを取得
        ItemStack[] items = inventory.getContents();

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            if (item != null) {
                Material itemType = item.getType();

                // 鉄鉱石を精錬
                if (itemType == Material.RAW_IRON||itemType == Material.IRON_ORE||itemType == Material.DEEPSLATE_IRON_ORE) {
                    int itemCount = item.getAmount();
                    inventory.setItem(i, null); // 元のアイテムを削除

                    int ingotCount = itemCount * 1; // 1つの鉄鉱石から1つの鉄インゴットを生成
                    while (ingotCount > 0) {
                        int stackSize = Math.min(ingotCount, Material.IRON_INGOT.getMaxStackSize());
                        ItemStack ingotStack = new ItemStack(Material.IRON_INGOT, stackSize);
                        ingotCount -= stackSize;
                        inventory.addItem(ingotStack);
                    }
                }
                // 金鉱石を精錬
                else if (itemType == Material.RAW_GOLD||itemType == Material.GOLD_ORE||itemType == Material.DEEPSLATE_GOLD_ORE) {
                    int itemCount = item.getAmount();
                    inventory.setItem(i, null); // 元のアイテムを削除

                    int ingotCount = itemCount * 1; // 1つの金鉱石から1つの金インゴットを生成
                    while (ingotCount > 0) {
                        int stackSize = Math.min(ingotCount, Material.GOLD_INGOT.getMaxStackSize());
                        ItemStack ingotStack = new ItemStack(Material.GOLD_INGOT, stackSize);
                        ingotCount -= stackSize;
                        inventory.addItem(ingotStack);
                    }
                }
                // 銅鉱石を精錬
                else if (itemType == Material.RAW_COPPER||itemType == Material.COPPER_ORE||itemType == Material.DEEPSLATE_COPPER_ORE) {
                    int itemCount = item.getAmount();
                    inventory.setItem(i, null); // 元のアイテムを削除

                    int ingotCount = itemCount * 1; // 1つの銅鉱石から1つの銅インゴットを生成
                    while (ingotCount > 0) {
                        int stackSize = Math.min(ingotCount, Material.COPPER_INGOT.getMaxStackSize());
                        ItemStack ingotStack = new ItemStack(Material.COPPER_INGOT, stackSize);
                        ingotCount -= stackSize;
                        inventory.addItem(ingotStack);
                    }
                }
                // 他の鉱石に対する処理も同様に追加してください
            }
        }
    }

}
