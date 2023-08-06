package com.github.kumo0621.mine;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

/**
 * 金関係の処理をするクラス
 */
public class MoneyHandler {

    private Map<UUID, Integer> getMap(){
        return Mine.getInstance().getData().getMoneyMap();
    }

    public int getMoney(Player player){
        return getMap().get(player.getUniqueId());
    }

    public void setMoney(Player player, int money){
        if(!Mine.getInstance().hasData(player))
            Mine.getInstance().register(player);

        getMap().put(player.getUniqueId(), money);
    }

    public void bell(Player player) {
        Inventory playerInventory = player.getInventory();
        int totalMoneyAmount = 0;
        for (int slot = 0; slot < playerInventory.getSize(); slot++) {
            ItemStack currentItem = playerInventory.getItem(slot);
            int price = ItemPrices.getprice(currentItem);
            if (price != 0) {
                totalMoneyAmount += price;
                playerInventory.setItem(slot, null); // アイテムをインベントリから削除
            }
        }

        int money = getMoney(player);
        int result = money + totalMoneyAmount;
        setMoney(player, result);
        player.sendMessage("所持金は、" + result + "になりました。");
    }

    public void purchase(Player player, PurchasableSeitiTool tool){
        int purse = getMoney(player);
        if(purse < tool.getPrice()){
            player.sendMessage("お金が足りません");
            return;
        }

        setMoney(player, purse - tool.getPrice());
        player.getInventory().addItem(tool.getItemStack());
        player.sendMessage("ツールを強化しました");
    }
}
