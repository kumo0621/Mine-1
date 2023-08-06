package com.github.kumo0621.mine.items;

import com.github.kumo0621.mine.Mine;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AreaMiner extends PurchasableSeitiItem implements IRightClickHandler {

    private static List<Player> miningPlayers = new ArrayList<>();

    /**
     * 　固有アイテムの型を作成する
     */
    public AreaMiner() {
        super(Component.text("右クリックで範囲採掘できる"), Material.PAPER, "blockbreak", 0, 100);
    }

    @Override
    public void OnRightClick(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null && !miningPlayers.contains(event.getPlayer())) {
            startMiningPower(event.getPlayer());
            event.getItem().setAmount(event.getItem().getAmount() - 1);
        }
    }

    private void startMiningPower(Player player) {
        miningPlayers.add(player);
        // 30秒間、3x3の範囲内のブロックを採掘可能にする処理を開始
        new BukkitRunnable() {
            int timer = 30;

            @Override
            public void run() {
                if (timer > 0) {
                    // プレイヤーの位置から3x3の範囲内のブロックを採掘
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            for (int z = -1; z <= 1; z++) {
                                Block block = player.getLocation().getBlock().getRelative(x, y, z);
                                if (block.getType() != Material.BEDROCK) {
                                    block.breakNaturally();
                                }
                            }
                        }
                    }
                    timer--;
                } else {
                    // 30秒経過後に採掘処理を終了
                    this.cancel();
                    miningPlayers.remove(player);
                }
            }
        }.runTaskTimer(Mine.getInstance(), 0, 20); // 1秒間隔で実行
    }
}
