package com.github.kumo0621.mine.commands;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import com.github.kumo0621.mine.items.ISeitiItem;
import com.github.kumo0621.mine.items.SeitiItems;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * プレイヤーにカスタムアイテムを渡すコマンド
 */
public class CommandSeitiGive implements Listener {

    private final String commandName = "minegive";

    public CommandSeitiGive() {
        Mine.getInstance().getServer().getPluginManager().registerEvents(this, Mine.getInstance());

        new CommandBase(commandName, 1, 3, true) {

            @Override
            public boolean onCommand(@NotNull CommandSender sender, String[] arguments) {
                ISeitiItem item = SeitiItems.commandToSeitiItem(arguments[0]);
                if (item != null) {
                    if (arguments.length > 1) {
                        item.onGiveCommand(sender, Arrays.copyOfRange(arguments, 1, arguments.length));
                    } else {
                        item.onGiveCommand(sender, null);
                    }
                } else {
                    sendUsage(sender);
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/ocgive {itemName} {arguments}";
            }
        };
    }

    @EventHandler
    public void AsyncTabCompleteEvent(AsyncTabCompleteEvent event) {
        if (event.getBuffer().startsWith("/" + commandName + "")) {
            List<String> suggestions = new ArrayList<>();
            Arrays.stream(SeitiItems.values()).forEach(v -> suggestions.add(v.get().getInternalName()));
            event.setCompletions(suggestions);
        }
    }
}