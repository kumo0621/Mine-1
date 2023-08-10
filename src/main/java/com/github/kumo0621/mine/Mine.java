package com.github.kumo0621.mine;

import com.github.kumo0621.mine.commands.*;
import com.github.kumo0621.mine.items.IBuffItem;
import com.github.kumo0621.mine.items.IRightClickHandler;
import com.github.kumo0621.mine.items.ISeitiItem;
import com.github.kumo0621.mine.items.SeitiItems;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Random;

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
        new Commandtransaction();

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

        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    applyBuffToPlayer(onlinePlayer);
                }
            }
        }.runTaskTimer(this, 0, 200);
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

        if (event.getHand() == EquipmentSlot.OFF_HAND)
            return;
        if (event.getAction().isLeftClick())
            return;

        if (block != null && block.getType() == Material.CHEST) {
            event.setCancelled(true);
            moneyHandler.bell(player);
            return;
        }

        ISeitiItem item = SeitiItems.toSeitiItem(event.getItem());
        if (item instanceof IRightClickHandler) {
            ((IRightClickHandler) item).OnRightClick(event);
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() != null && block != null && block.getType() == Material.SMITHING_TABLE) {
            event.setCancelled(true);

            // 古代の残骸を持っているか確認
            if (event.getItem().getType() == Material.ANCIENT_DEBRIS) {
                event.getItem().setAmount(event.getItem().getAmount() - 1);

                // アイテムの取得結果をランダムで決定
                int random = new Random().nextInt(50);
                if (random == 0) {
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND, 100));
                    player.sendMessage("古代の残骸を鑑定してダイヤモンドをゲットしました。");
                } else if (random == 1) {
                    player.getInventory().addItem(new ItemStack(Material.COAL, 20));
                    player.sendMessage("古代の残骸を鑑定して石炭をゲットしました。");
                } else if (random == 2) {
                    player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 64));
                    player.sendMessage("古代の残骸を鑑定して鉄インゴットをゲットしました。");
                } else {
                    player.sendMessage("古代の残骸を鑑定しましたが、何も起こりませんでした。");
                }
            } else {
                player.sendMessage("古代の残骸を持っていません。");
            }
        }
    }

    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent event) {
        ISeitiItem item = SeitiItems.toSeitiItem(event.getItem().getItemStack());
        if (item instanceof IBuffItem)
            ((IBuffItem) item).applyBuff(event.getPlayer());
    }

    public void applyBuffToPlayer(Player onlinePlayer) {
        Inventory inv = onlinePlayer.getInventory();
        for (ItemStack content : inv.getContents()) {
            if (content == null)
                continue;

            ISeitiItem item = SeitiItems.toSeitiItem(content);
            if (!(item instanceof IBuffItem))
                continue;

            ((IBuffItem) item).applyBuff(onlinePlayer);
        }
    }

    public void increaseBreakScore(Player player, int amount) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

        Objective objective = scoreboard.getObjective("break");

        Score score = objective.getScore(player.getName());
        score.setScore(score.getScore() + amount);
    }
    
    public int getBreakScore(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

        Objective objective = scoreboard.getObjective("break");

        Score score = objective.getScore(player.getName());
        return score.getScore();
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        // TODO : 壊したブロックの判定を追加する
        increaseBreakScore(player, 1);
        if (event.getBlock().getType() == Material.STONE || event.getBlock().getType() == Material.DEEPSLATE) {
            // 1%の確率で化石をドロップする
            int random = new Random().nextInt(100);
            if (random == 0) {
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.ANCIENT_DEBRIS));
            }
        }
    }

}