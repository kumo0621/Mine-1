package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandSetHome extends CommandBase {

    private static final String commandName = "sethome";

    public CommandSetHome() {
        super(commandName, 0, 0, true);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
       Player player = (Player) sender;
       /* Location targetLocation = player.getLocation(); // B地点の座標を指定
        Mine.getInstance().getData().setHome(targetLocation);
        player.sendMessage("homeを現在の座標で設定しました");*/
        player.sendMessage("TBD");
        return true;
    }
}