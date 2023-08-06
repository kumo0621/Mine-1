package com.github.kumo0621.mine;

import com.github.kumo0621.mine.commands.*;
import com.github.kumo0621.mine.items.SeitiItems;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Mine extends JavaPlugin implements Listener {

    @Getter
    private static Mine instance;
    @Getter
    private Config seitiConfig;
    @Getter
    private Data data;
    @Getter
    private MoneyHandler moneyHandler;

    public Mine() {
        instance = this;
    }

    @Override
    public void onEnable() {
        new CommandSeitiGive();
        new CommandGetMoney();
        getServer().getPluginManager().registerEvents(new CommandSetMoney(), this);
        new CommandHome();
        new CommandSetHome();
        new CommandOpenMenu();
        new CommandFly();

        data = new Data();
        moneyHandler = new MoneyHandler();

        getServer().getPluginManager().registerEvents(this, this);

        seitiConfig = new Config();
        seitiConfig.loadConfig();

        new BukkitRunnable() {

            @Override
            public void run() {
                seitiConfig.saveConfig();
            }
        }.runTaskTimer(this, 0, 1200);//毎分configを保存
    }

    @Override
    public void onDisable() {
        seitiConfig.saveConfig();
    }


    public boolean hasData(Player player) {
        return getData().getJoinedPlayerList().contains(player.getUniqueId());
    }

    public void register(Player player) {
        getData().getJoinedPlayerList().add(player.getUniqueId());
        getData().getMoneyMap().put(player.getUniqueId(), 0);

        // プレイヤーにアイテムを与える
        player.getInventory().addItem(SeitiItems.BEGINNER_PICKAXE.get().getItemStack());
        player.getInventory().addItem(SeitiItems.BEGINNER_SHOVEL.get().getItemStack());
        player.getInventory().addItem(SeitiItems.BEGINNER_AXE.get().getItemStack());

        // 初回ログイン時のメッセージを送信
        player.sendMessage("初回ログインです。所持金が初期化されました。");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // プレイヤーデータからフラグを取得
        boolean isFirstJoin = !hasData(player);

        if (isFirstJoin) {
            // 初回ログイン時の処理
            register(player);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && block != null && block.getType() == Material.CHEST) {
            event.setCancelled(true);
            moneyHandler.bell(player);
        }
    }
}