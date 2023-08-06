package com.github.kumo0621.mine.commands;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import com.github.kumo0621.mine.CommandBase;
import com.github.kumo0621.mine.Mine;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandSetMoney extends CommandBase implements Listener {

    private static final String commandName = "set";

    public CommandSetMoney() {
        super(commandName, 2, 2, true);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable String[] arguments) {
        Player targetPlayer = Bukkit.getPlayer(arguments[0]);
        if(targetPlayer == null){
            sender.sendMessage("プレイヤーを指定してください");
            return true;
        }
        
        int money;
        try{
            money = Integer.parseInt(arguments[1]);
        }catch (NumberFormatException e){
            sender.sendMessage("数値を指定してください");
            return true;
        }
        
        Mine.getInstance().getSeitiConfig().setMoney(targetPlayer, money);
        
        sender.sendMessage(targetPlayer.getName() + "さんの所持金を「" + money + "」にしました。");
        return true;
    }

    @EventHandler
    public void AsyncTabCompleteEvent(AsyncTabCompleteEvent e) {
        if (e.getBuffer().startsWith("/" + commandName + " ")) {
            List<String> suggestions = new ArrayList<>();
            String pureBuffer = e.getBuffer().replace("/" + commandName + " ", "");
            Bukkit.getOnlinePlayers().forEach(s -> {
                if(s.getName().startsWith(pureBuffer))
                    suggestions.add(s.getName());
            });
            e.setCompletions(suggestions);
        }
    }
}
