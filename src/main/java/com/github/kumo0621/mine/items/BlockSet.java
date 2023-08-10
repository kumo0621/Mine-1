package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockSet extends PurchasableSeitiItem implements IRightClickHandler {

    /**
     * 　固有アイテムの型を作成する
     */
    public BlockSet() {
        super(Component.text("右クリックで範囲設置"), Material.PAPER, "set", 0, 1);
    }
    @Override
    public void OnRightClick(PlayerInteractEvent event) {
        int radius = 2; // The radius of the filled area (5x5 = 2 blocks from the center in each direction)
        Block clickedBlock = event.getClickedBlock();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Block targetBlock = clickedBlock.getLocation().clone().add(x, 0, z).getBlock();
                if (targetBlock.getType() == Material.AIR) {
                    targetBlock.setType(Material.DIRT);
                }
            }
        }

        event.getItem().setAmount(event.getItem().getAmount() - 1);
    }
}
