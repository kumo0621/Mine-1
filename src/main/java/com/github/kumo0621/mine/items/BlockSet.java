package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEvent;

public class TNTSummoner extends PurchasableSeitiItem implements IRightClickHandler {
    /**
     * 　固有アイテムの型を作成する
     */
    public TNTSummoner() {
        super(Component.text("TNTを右クリックで召喚"), Material.PAPER, "tnt", 0, 100);
    }

    @Override
    public void OnRightClick(PlayerInteractEvent event) {
        Location spawnLocation = event.getPlayer().getEyeLocation().add(event.getPlayer().getLocation().getDirection().multiply(3));
        spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.PRIMED_TNT);
        event.getItem().setAmount(event.getItem().getAmount() - 1);
    }
}
