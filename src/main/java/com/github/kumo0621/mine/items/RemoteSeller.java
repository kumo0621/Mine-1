package com.github.kumo0621.mine.items;

import com.github.kumo0621.mine.Mine;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEvent;

public class RemoteSeller extends PurchasableSeitiItem implements IRightClickHandler {
    /**
     * 　固有アイテムの型を作成する
     */
    public RemoteSeller() {
        super(Component.text("売却を右クリックでできる"), Material.PAPER, "seller", 0, 500);
    }

    @Override
    public void OnRightClick(PlayerInteractEvent event) {
        Mine.getInstance().getMoneyHandler().bell(event.getPlayer());
        event.getItem().setAmount(event.getItem().getAmount() - 1);
    }
}
