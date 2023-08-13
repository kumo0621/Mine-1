package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandTransaction extends CommandBase {

    private static final String commandName = "sendmoney";


    public CommandTransaction() {
        super(commandName, 2, 2, true);
        setPermission("");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {

        Player player = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(arguments[0]);

        int sendAmount;

        int senderPlayerMoney = Mine.getInstance().getMoneyHandler().getMoney((Player) sender);
        int targetPlayerMoney = Mine.getInstance().getMoneyHandler().getMoney(targetPlayer);

        if (targetPlayer == null) {
            sender.sendMessage("プレイヤーを指定してください");
            return true;
        }
        if (player.getName().equals(targetPlayer.getName())) {
            sender.sendMessage("自分に送金することはできません");
            return true;
        }

        try {
            sendAmount = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException numberFormatException) {
            sender.sendMessage("数値を指定してください");
            return true;
        }

        if (sendAmount <= 0) {
            sender.sendMessage("1以上の数値を指定してください");
            return true;
        }

        if (senderPlayerMoney < sendAmount) {
            sender.sendMessage("お金が足りません");
            return true;
        }

        if (targetPlayerMoney + sendAmount <= targetPlayerMoney) {
            sender.sendMessage("相手はこれ以上のお金を持てません");
            return true;
        }

        senderPlayerMoney -= sendAmount;
        targetPlayerMoney += sendAmount;
        Mine.getInstance().getMoneyHandler().setMoney(player, senderPlayerMoney);
        Mine.getInstance().getMoneyHandler().setMoney(targetPlayer, targetPlayerMoney);

        sender.sendMessage(((Player) sender).getPlayer().getName() + "さんは、" + targetPlayer.getName() + "さんに" + sendAmount + "送金しました");
        targetPlayer.sendMessage(player.getName() + "さんから、" + sendAmount + "G送金されました");

        return true;
    }
}
