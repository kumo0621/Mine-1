package com.github.kumo0621.mine.commands;

import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandHome extends CommandBase {

    private static final String commandName = "home";

    public CommandHome() {
        super(commandName, 0, 0, true);
        setPermission("");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
        Player player = (Player) sender;
        Location targetLocation = Mine.getInstance().getData().getHome(); // B地点の座標を指定
        if (targetLocation == null) {
            player.sendMessage("homeが設定されていません");
            return true;
        }
        player.teleport(targetLocation);
        return true;
    }
}
