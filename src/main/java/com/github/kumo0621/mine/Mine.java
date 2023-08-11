package com.github.kumo0621.mine;

import com.github.kumo0621.mine.commands.*;
import com.github.kumo0621.mine.items.IBuffItem;
import com.github.kumo0621.mine.items.IRightClickHandler;
import com.github.kumo0621.mine.items.ISeitiItem;
import com.github.kumo0621.mine.items.SeitiItems;
import com.github.kumo0621.mine.playerdata.MiningScore;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
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

    List<Material>  miningBlockList = List.of(Material.DIRT, Material.GRASS_BLOCK,Material.SAND, Material.SANDSTONE, Material.RED_SAND, Material.RED_SANDSTONE
            , Material.CLAY, Material.GRAVEL, Material.STONE, Material.COBBLESTONE, Material.DEEPSLATE, Material.COBBLED_DEEPSLATE
            , Material.END_STONE, Material.NETHERRACK, Material.OBSIDIAN, Material.ANDESITE, Material.DIORITE, Material.GRANITE
            , Material.TUFF, Material.DRIPSTONE_BLOCK, Material.BASALT, Material.BLACKSTONE, Material.COAL_ORE, Material.IRON_ORE
            , Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE
            , Material.COPPER_ORE, Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE, Material.ANCIENT_DEBRIS
            , Material.OAK_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG
            , Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_IRON_ORE, Material.DEEPSLATE_GOLD_ORE, Material.DEEPSLATE_DIAMOND_ORE
            , Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_REDSTONE_ORE
            , Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_GOLD_ORE);

    List<Material> allowMiningBlockList =
            List.of(Material.GRASS, Material.TALL_GRASS, Material.BEDROCK, Material.LAVA, Material.WATER
                    , Material.OAK_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG
                    , Material.OAK_LEAVES, Material.JUNGLE_LEAVES, Material.ACACIA_LEAVES, Material.BIRCH_LEAVES, Material.SPRUCE_LEAVES
                    , Material.WITHER_SKELETON_SKULL, Material.SKELETON_SKULL, Material.ZOMBIE_HEAD, Material.PLAYER_HEAD
                    , Material.CREEPER_HEAD, Material.DRAGON_HEAD, Material.TORCH, Material.REDSTONE_TORCH, Material.REDSTONE_WALL_TORCH
                    , Material.SOUL_TORCH, Material.SOUL_WALL_TORCH, Material.WALL_TORCH,Material.LANTERN, Material.SOUL_LANTERN
                    , Material.CAMPFIRE, Material.SOUL_CAMPFIRE, Material.FIRE, Material.SOUL_FIRE, Material.END_ROD
                    , Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK
                    , Material.MUSHROOM_STEM, Material.REDSTONE_LAMP, Material.REDSTONE_BLOCK, Material.REDSTONE_WIRE
                    , Material.REPEATER, Material.COMPARATOR, Material.DAYLIGHT_DETECTOR, Material.DAYLIGHT_DETECTOR
                    , Material.LEVER, Material.STONE_BUTTON, Material.OAK_BUTTON, Material.SPRUCE_BUTTON, Material.BIRCH_BUTTON
                    , Material.JUNGLE_BUTTON, Material.ACACIA_BUTTON, Material.DARK_OAK_BUTTON, Material.CRIMSON_BUTTON
                    , Material.WARPED_BUTTON, Material.STONE_PRESSURE_PLATE, Material.OAK_PRESSURE_PLATE, Material.SPRUCE_PRESSURE_PLATE
                    , Material.BIRCH_PRESSURE_PLATE, Material.JUNGLE_PRESSURE_PLATE, Material.ACACIA_PRESSURE_PLATE
                    , Material.DARK_OAK_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE
                    , Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.IRON_DOOR
                    , Material.OAK_DOOR, Material.SPRUCE_DOOR, Material.BIRCH_DOOR, Material.JUNGLE_DOOR, Material.ACACIA_DOOR
                    , Material.DARK_OAK_DOOR, Material.CRIMSON_DOOR, Material.WARPED_DOOR, Material.IRON_TRAPDOOR
                    , Material.OAK_TRAPDOOR, Material.SPRUCE_TRAPDOOR, Material.BIRCH_TRAPDOOR, Material.JUNGLE_TRAPDOOR
                    , Material.ACACIA_TRAPDOOR, Material.DARK_OAK_TRAPDOOR, Material.CRIMSON_TRAPDOOR, Material.WARPED_TRAPDOOR
                    , Material.OAK_FENCE_GATE, Material.SPRUCE_FENCE_GATE, Material.BIRCH_FENCE_GATE, Material.JUNGLE_FENCE_GATE
                    , Material.ACACIA_FENCE_GATE, Material.DARK_OAK_FENCE_GATE, Material.CRIMSON_FENCE_GATE, Material.WARPED_FENCE_GATE
                    , Material.OAK_FENCE, Material.SPRUCE_FENCE, Material.BIRCH_FENCE, Material.JUNGLE_FENCE, Material.ACACIA_FENCE
                    , Material.DARK_OAK_FENCE, Material.CRIMSON_FENCE, Material.WARPED_FENCE, Material.OAK_SIGN, Material.SPRUCE_SIGN
                    , Material.BIRCH_SIGN, Material.JUNGLE_SIGN, Material.ACACIA_SIGN, Material.DARK_OAK_SIGN, Material.CRIMSON_SIGN
                    , Material.WARPED_SIGN, Material.OAK_WALL_SIGN, Material.SPRUCE_WALL_SIGN, Material.BIRCH_WALL_SIGN
                    , Material.JUNGLE_WALL_SIGN, Material.ACACIA_WALL_SIGN, Material.DARK_OAK_WALL_SIGN, Material.CRIMSON_WALL_SIGN
                    , Material.WARPED_WALL_SIGN, Material.CHEST, Material.TRAPPED_CHEST, Material.ENDER_CHEST, Material.BARREL
                    , Material.SHULKER_BOX, Material.BLACK_SHULKER_BOX, Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX
                    , Material.CYAN_SHULKER_BOX, Material.GRAY_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX
                    , Material.LIGHT_GRAY_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX, Material.ORANGE_SHULKER_BOX
                    , Material.PINK_SHULKER_BOX, Material.PURPLE_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.WHITE_SHULKER_BOX
                    , Material.YELLOW_SHULKER_BOX, Material.BREWING_STAND, Material.CAULDRON, Material.BELL, Material.CAKE
                    , Material.CAKE, Material.CRAFTING_TABLE, Material.ENCHANTING_TABLE, Material.ANVIL, Material.CHIPPED_ANVIL
                    , Material.DAMAGED_ANVIL, Material.BEACON, Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED
                    , Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED, Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED
                    , Material.LIME_BED, Material.MAGENTA_BED, Material.ORANGE_BED, Material.PINK_BED, Material.PURPLE_BED
                    , Material.RED_BED, Material.WHITE_BED, Material.YELLOW_BED, Material.FURNACE, Material.STONECUTTER
                    , Material.SMOKER, Material.BLAST_FURNACE, Material.JUKEBOX, Material.LANTERN, Material.SOUL_LANTERN
                    , Material.BEE_NEST, Material.BEEHIVE, Material.CARTOGRAPHY_TABLE, Material.FLETCHING_TABLE, Material.GRINDSTONE
                    , Material.LOOM, Material.SMITHING_TABLE, Material.COMPOSTER, Material.CONDUIT, Material.LODESTONE, Material.LADDER
                    , Material.SCAFFOLDING, Material.BAMBOO_SAPLING, Material.BAMBOO, Material.LIGHTNING_ROD, Material.PAINTING
                    , Material.ITEM_FRAME, Material.GLOW_ITEM_FRAME, Material.FLOWER_POT, Material.RAIL, Material.POWERED_RAIL
                    , Material.DETECTOR_RAIL, Material.ACTIVATOR_RAIL, Material.PISTON, Material.STICKY_PISTON, Material.PISTON_HEAD
                    , Material.MOVING_PISTON, Material.WHITE_CARPET, Material.ORANGE_CARPET, Material.MAGENTA_CARPET
                    , Material.LIGHT_BLUE_CARPET, Material.YELLOW_CARPET, Material.LIME_CARPET, Material.PINK_CARPET
                    , Material.GRAY_CARPET, Material.LIGHT_GRAY_CARPET, Material.CYAN_CARPET, Material.PURPLE_CARPET
                    , Material.BLUE_CARPET, Material.BROWN_CARPET, Material.GREEN_CARPET, Material.RED_CARPET
                    , Material.BLACK_CARPET, Material.DANDELION, Material.POPPY, Material.BLUE_ORCHID, Material.ALLIUM
                    , Material.AZURE_BLUET, Material.RED_TULIP, Material.ORANGE_TULIP, Material.WHITE_TULIP, Material.PINK_TULIP
                    , Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY, Material.WITHER_ROSE, Material.SUNFLOWER
                    , Material.LILAC, Material.ROSE_BUSH, Material.PEONY, Material.LARGE_FERN, Material.TUBE_CORAL, Material.BRAIN_CORAL
                    , Material.BUBBLE_CORAL, Material.FIRE_CORAL, Material.HORN_CORAL, Material.DEAD_TUBE_CORAL, Material.DEAD_BRAIN_CORAL
                    , Material.DEAD_BUBBLE_CORAL, Material.DEAD_FIRE_CORAL, Material.DEAD_HORN_CORAL, Material.TUBE_CORAL_FAN
                    , Material.BRAIN_CORAL_FAN, Material.BUBBLE_CORAL_FAN, Material.FIRE_CORAL_FAN, Material.HORN_CORAL_FAN
                    , Material.DEAD_TUBE_CORAL_FAN, Material.DEAD_BRAIN_CORAL_FAN, Material.DEAD_BUBBLE_CORAL_FAN, Material.DEAD_FIRE_CORAL_FAN
                    , Material.DEAD_HORN_CORAL_FAN, Material.TUBE_CORAL_WALL_FAN, Material.BRAIN_CORAL_WALL_FAN, Material.BUBBLE_CORAL_WALL_FAN
                    , Material.FIRE_CORAL_WALL_FAN, Material.HORN_CORAL_WALL_FAN, Material.DEAD_TUBE_CORAL_WALL_FAN, Material.DEAD_BRAIN_CORAL_WALL_FAN
                    , Material.DEAD_BUBBLE_CORAL_WALL_FAN, Material.DEAD_FIRE_CORAL_WALL_FAN, Material.DEAD_HORN_CORAL_WALL_FAN, Material.SEA_PICKLE
                    , Material.GLASS, Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS
                    , Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS, Material.PINK_STAINED_GLASS
                    , Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS, Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS
                    , Material.BLUE_STAINED_GLASS, Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS
                    , Material.BLACK_STAINED_GLASS, Material.GLASS_PANE, Material.WHITE_STAINED_GLASS_PANE, Material.ORANGE_STAINED_GLASS_PANE
                    , Material.MAGENTA_STAINED_GLASS_PANE, Material.LIGHT_BLUE_STAINED_GLASS_PANE, Material.YELLOW_STAINED_GLASS_PANE
                    , Material.LIME_STAINED_GLASS_PANE, Material.PINK_STAINED_GLASS_PANE, Material.GRAY_STAINED_GLASS_PANE
                    , Material.LIGHT_GRAY_STAINED_GLASS_PANE, Material.CYAN_STAINED_GLASS_PANE, Material.PURPLE_STAINED_GLASS_PANE
                    , Material.BLUE_STAINED_GLASS_PANE, Material.BROWN_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE
                    , Material.RED_STAINED_GLASS_PANE, Material.BLACK_STAINED_GLASS_PANE, Material.WHITE_WOOL, Material.ORANGE_WOOL
                    , Material.MAGENTA_WOOL, Material.LIGHT_BLUE_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL
                    , Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL, Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL
                    , Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL, Material.WHITE_BED, Material.ORANGE_BED
                    , Material.MAGENTA_BED, Material.LIGHT_BLUE_BED, Material.YELLOW_BED, Material.LIME_BED, Material.PINK_BED
                    , Material.GRAY_BED, Material.LIGHT_GRAY_BED, Material.CYAN_BED, Material.PURPLE_BED, Material.BLUE_BED
                    , Material.BROWN_BED, Material.GREEN_BED, Material.RED_BED, Material.BLACK_BED, Material.SPONGE
                    , Material.WET_SPONGE, Material.AIR, Material.WATER, Material.LAVA);

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
        new CommandTransaction();
        new CommandSpawn();
        new CommandSetSpawn();

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
        }.runTaskTimer(this, 0, 1200); //毎分configを保存

        new BukkitRunnable() {

            @Override
            public void run() {
                int x = 0;
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    x++;
                    if (x >= 200) {
                        applyBuffToPlayer(onlinePlayer);
                        x = 0;
                    }
                    updateActionBar(onlinePlayer);
                }
            }
        }.runTaskTimer(this, 0, 1);
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

        updateActionBar(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (event.getAction().isLeftClick()) return;

        if (block != null && block.getType() == Material.CHEST) {
            event.setCancelled(true);
            moneyHandler.sell(player);
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
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND, 40));
                    player.sendMessage("古代の残骸を鑑定してダイヤモンドをゲットしました");
                } else if (random == 1) {
                    player.getInventory().addItem(new ItemStack(Material.COAL, 20));
                    player.sendMessage("古代の残骸を鑑定して石炭をゲットしました");
                } else if (random == 2) {
                    player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 24));
                    player.sendMessage("古代の残骸を鑑定して鉄インゴットをゲットしました");
                } else {
                    player.sendMessage("古代の残骸を鑑定しましたが何も見つかりませんでした");
                }
            } else {
                player.sendMessage("古代の残骸を持っていません");
            }
        }
    }

    @EventHandler
    public void onAccessoryClick(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            if (event.getItem() != null) {
                ISeitiItem item = SeitiItems.toSeitiItem(event.getItem());
                if (item instanceof IBuffItem) {
                    ((IBuffItem) item).applyBuff(event.getPlayer());
                }
            }
        }
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material blockType = event.getBlock().getType();

        Block breakBreak = event.getBlock();

        // 特定のブロックは例外にする
        // 上から掘らなかった場合はキャンセルしてリターンする
        // 掘ろうとしたブロックとワールドの一番上のブロックを比較して、同じだった場合は掘れるようにしている
        if (!allowMiningBlockList.contains(blockType)) {
            Material block = breakBreak.getLocation().add(0, 2, 0).getBlock().getType();
            if (!allowMiningBlockList.contains(block)) {
                event.setCancelled(true);
                return;
            }
        }

        // 対象のブロックを採掘した時に採掘量を増やす
        if (miningBlockList.contains(blockType)) {
            MiningScore.increaseBreakScore(player, 1);
        }

        // TODO : 対象ブロックの調整
        if (event.getBlock().getType() == Material.STONE || event.getBlock().getType() == Material.DEEPSLATE) {
            // 1%の確率で化石をドロップする
            int random = new Random().nextInt(100);
            if (random == 0) {
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.ANCIENT_DEBRIS));
            }
        }
    }

    public static void updateActionBar(Player player) {
        // 採掘量と所持金をアクションバーに表示
        int breakScore = MiningScore.getBreakScore(player);
        int money = Mine.getInstance().data.getMoneyMap().get(player.getUniqueId());
        player.sendActionBar(Component.text(ChatColor.AQUA + "所持金: " + money + "G   " + ChatColor.GREEN + "採掘量: " + breakScore));

    }
}