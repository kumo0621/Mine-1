package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Commandtransaction extends CommandBase {

    private static final String commandName = "sendmoney";


    public Commandtransaction() {
        super(commandName, 2, 2, true);
        setPermission("");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
        Player player = (Player) sender;
        int money;
        Player targetPlayer = Bukkit.getPlayer(arguments[0]);
        int player1money = Mine.getInstance().getMoneyHandler().getMoney((Player) sender);
        int player2money = Mine.getInstance().getMoneyHandler().getMoney(targetPlayer);
        if (targetPlayer == null) {
            sender.sendMessage("プレイヤーを指定してください");
            return true;
        }
        try {
            money = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("数値を指定してください");
            return true;
        }
        if(player1money<=money){
            sender.sendMessage("お金が足りません。");
            return true;
        }
        player1money -= money;
        player2money += money;
        Mine.getInstance().getMoneyHandler().setMoney(player, player1money);
        Mine.getInstance().getMoneyHandler().setMoney(targetPlayer, player2money);

        sender.sendMessage(((Player) sender).getPlayer().getName()+"さんは、"+targetPlayer.getName()+"さんに"+money+"送金しました。");
        targetPlayer.sendMessage(player.getName()+"さんから、"+money+"送金されました。");

        return true;
    }
}
