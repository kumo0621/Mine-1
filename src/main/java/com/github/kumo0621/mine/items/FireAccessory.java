package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireAccessory extends PurchasableSeitiItem implements IBuffItem {

    /**
     * 　固有アイテムの型を作成する
     */
    public FireAccessory() {
        super(Component.text("精錬のアクセサリー"), Material.IRON_PICKAXE, "Fire_speed", 3, 500000);
    }

    @Override
    public void applyBuff(Player player) {
        Inventory inventory = player.getInventory();

        // インベントリ内のアイテムを取得
        ItemStack[] items = inventory.getContents();

        for (ItemStack item : items) {
            if (item != null) {
                Material itemType = item.getType();

                // 鉄鉱石を精錬
                if (itemType == Material.IRON_ORE) {
                    inventory.removeItem(item);
                    inventory.addItem(new ItemStack(Material.IRON_INGOT, 1));
                    player.sendMessage("鉄鉱石が精錬されて鉄インゴットが生成されました！");
                }
                // 金鉱石を精錬
                else if (itemType == Material.GOLD_ORE) {
                    inventory.removeItem(item);
                    inventory.addItem(new ItemStack(Material.GOLD_INGOT, 1));
                    player.sendMessage("金鉱石が精錬されて金インゴットが生成されました！");
                }
                // 銅鉱石を精錬
                else if (itemType == Material.COPPER_ORE) {
                    inventory.removeItem(item);
                    inventory.addItem(new ItemStack(Material.COPPER_INGOT, 1));
                    player.sendMessage("銅鉱石が精錬されて銅インゴットが生成されました！");
                }
            }
        }
    }
}
