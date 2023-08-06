package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.SeitiUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandOpenMenu extends CommandBase {

    private static final String commandName = "open";

    public CommandOpenMenu() {
        super(commandName, 0, 0, true);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
        Player player = (Player) sender;
        SeitiUI.openMenu(player);
        return true;
    }
}
