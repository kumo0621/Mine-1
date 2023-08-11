package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandFly extends CommandBase {

    private static final String commandName = "fly";

    private static final List<Player> flyingPlayersList = new ArrayList<>();

    public CommandFly() {
        super(commandName, 0, 0, true);
        setPermission("");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
        Player player = (Player) sender;
        int money = Mine.getInstance().getMoneyHandler().getMoney(player);

        if (money < 1000) {
            sender.sendMessage("お金が足りません。1000G必要です。");
            return true;
        }
        if (player.getGameMode() == GameMode.CREATIVE) {
            player.sendMessage("クリエイティブモードでは飛行は無効です！");
            return true;
        }
        if (flyingPlayersList.contains(player)) {
            player.sendMessage("現在飛行中です！");
            return true;
        }

        int result = money - 1000;
        Mine.getInstance().getMoneyHandler().setMoney(player, result);

        // 空を飛ぶ許可をトグル
        player.setAllowFlight(true);
        flyingPlayersList.add(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                flyingPlayersList.remove(player);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage("空を飛ぶ機能が無効になりました。");
            }
        }.runTaskLater(Mine.getInstance(), 20 * 60);

        player.sendMessage("空を飛べるようになりました。");
        return true;
    }
}
