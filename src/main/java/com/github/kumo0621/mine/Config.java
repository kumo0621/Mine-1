package com.github.kumo0621.mine;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * コンフィグファイルを読み書きする
 */
public class Config {
    private static final String alreadyJoinedStr = "alreadyJoined";
    private static final String moneyStr = "money.";
    private static final String homeStr = "home";

    private Data getData() {
        return Mine.getInstance().getData();
    }

    /**
     * コンフィグファイル内のデータをデータクラスにコピーする
     */
    public void loadConfig() {
        JavaPlugin plugin = Mine.getInstance();
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        List<String> strList = config.getStringList(alreadyJoinedStr);
        getData().getJoinedPlayerList().clear();
        strList.forEach(s -> getData().getJoinedPlayerList().add(UUID.fromString(s)));

        getData().getJoinedPlayerList().forEach(uuid -> {
            getData().getMoneyMap().put(uuid, config.getInt(moneyStr + uuid.toString()));
        });

        getData().setHome(config.getLocation(homeStr));
    }

    /**
     * データクラスの中身をコンフィグにセーブする
     */
    public void saveConfig() {
        resetConfig();//古いデータが混在しないように一旦コンフィグを消す

        JavaPlugin plugin = Mine.getInstance();
        FileConfiguration config = plugin.getConfig();

        List<String> strList = new ArrayList<>();
        getData().getJoinedPlayerList().forEach(uuid -> strList.add(uuid.toString()));
        config.set(alreadyJoinedStr, strList);

        getData().getMoneyMap().forEach((uuid, integer) -> config.set(moneyStr + uuid.toString(), integer));

        config.set(homeStr, getData().getHome());

        plugin.saveConfig();
    }

    /**
     * コンフィグを全て削除する
     */
    public void resetConfig() {
        JavaPlugin plugin = Mine.getInstance();
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        if (configFile.delete()) {
            plugin.saveDefaultConfig();
            plugin.reloadConfig();
        }
    }
}
