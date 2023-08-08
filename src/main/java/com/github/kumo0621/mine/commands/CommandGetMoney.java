package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 所持金を確認するコマンド
 */
public class CommandGetMoney extends CommandBase {

    private static final String commandName = "money";

    public CommandGetMoney() {
        super(commandName, 0, 0, true);
        setPermission("");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
        int money = Mine.getInstance().getMoneyHandler().getMoney((Player) sender);
        sender.sendMessage("あなたの所持金は「" + money + "」です。");
        return true;
    }
}