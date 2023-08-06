package com.github.kumo0621.mine.items;

import org.bukkit.entity.Player;

/**
 * 持っているだけで10秒に1回プレイヤーにバフを付与するアイテム
 */
public interface IBuffItem {
    void applyBuff(Player player);
    
    int buffTime = 220;
}
