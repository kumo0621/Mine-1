package com.github.kumo0621.mine;

import com.github.kumo0621.mine.items.IPurchasableItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Map;
import java.util.UUID;

/**
 * 金関係の処理をするクラス
 */
public class MoneyHandler {

    private Map<UUID, Integer> getMap() {
        return Mine.getInstance().getData().getMoneyMap();
    }

    public int getMoney(Player player) {
        if (!Mine.getInstance().hasData(player))
            Mine.getInstance().register(player);

        int moneyAmount = getMap().get(player.getUniqueId());



        return moneyAmount;
    }

    public void setMoney(Player player, int money) {
        if (!Mine.getInstance().hasData(player))
            Mine.getInstance().register(player);

        getMap().put(player.getUniqueId(), money);
    }

    public void bell(Player player) {
        Inventory playerInventory = player.getInventory();
        int totalMoneyAmount = 0;
        for (int slot = 0; slot < playerInventory.getSize(); slot++) {
            ItemStack currentItem = playerInventory.getItem(slot);
            int price = ItemPrices.getPrice(currentItem);
            if (price != 0) {
                totalMoneyAmount += price;
                playerInventory.setItem(slot, null); // アイテムをインベントリから削除
            }
        }

        int money = getMoney(player);
        int result = money + totalMoneyAmount;
        setMoney(player, result);
        player.sendMessage("所持金は、" + result + "になりました。");
        // Assuming you have access to the scoreboard and objective instance
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective objective = scoreboard.getObjective("money"); // Assuming the objective is named "money"

        // Set the score for the player
        Score score = objective.getScore(player.getName()); // Assuming you're using player's name as score identifier
        score.setScore(result);
    }

    public boolean purchase(Player player, IPurchasableItem item) {
        int purse = getMoney(player);
        if (purse < item.getPrice()) {
            player.sendMessage("お金が足りません");
            return false;
        }

        setMoney(player, purse - item.getPrice());
        player.getInventory().addItem(item.getItemStack());
        return true;
    }
}
