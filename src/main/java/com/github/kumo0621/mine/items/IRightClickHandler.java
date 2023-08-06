package com.github.kumo0621.mine.items;

import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 右クリックで効果を発揮できるアイテム
 */
public interface IRightClickHandler {

    void OnRightClick(PlayerInteractEvent event);
}
