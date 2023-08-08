package com.github.kumo0621.mine;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * カスタムコマンドを作製するための基礎クラス
 * このクラスを新たに宣言してコンストラクタに名前とコマンド引数、内部に実行した時の結果の関数を定義することでオリジナルのコマンドを作ることができる
 */
public abstract class CommandBase extends BukkitCommand implements CommandExecutor {

    /**
     * このコマンドが要求する最小の引数の数
     */
    private final int minArguments;

    /**
     * このコマンドが要求する最大の引数の数
     */
    private final int maxArguments;

    /**
     * このコマンドがプレイヤーしか実行できないかどうか
     */
    private final boolean playerOnly;

    @ParametersAreNonnullByDefault
    public CommandBase(String command) {
        this(command, 0);
    }

    @ParametersAreNonnullByDefault
    public CommandBase(String command, boolean playerOnly) {
        this(command, 0, playerOnly);
    }

    @ParametersAreNonnullByDefault
    public CommandBase(String command, int requiredArguments) {
        this(command, requiredArguments, requiredArguments);
    }

    @ParametersAreNonnullByDefault
    public CommandBase(String command, int minArguments, int maxArguments) {
        this(command, minArguments, maxArguments, false);
    }

    @ParametersAreNonnullByDefault
    public CommandBase(String command, int requiredArguments, boolean playerOnly) {
        this(command, requiredArguments, requiredArguments, playerOnly);
    }

    /**
     * 引数からコマンドを作製する
     *
     * @param command      コマンド名
     * @param minArguments 要求する最小の引数の数
     * @param maxArguments 要求する最大の引数の数
     * @param playerOnly   プレイヤーだけが実行できるか
     */
    @ParametersAreNonnullByDefault
    public CommandBase(String command, int minArguments, int maxArguments, boolean playerOnly) {
        super(command);

        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;
        setPermission("seiti." + command);

        CommandMap commandMap = Bukkit.getCommandMap();
        if (!commandMap.register(command, "Mine", this))
            commandMap.getKnownCommands().replace(command, this);
    }

    /**
     * コマンド使用者にこのコマンドの仕様について送る
     *
     * @param sender コマンドの使用者
     */
    @ParametersAreNonnullByDefault
    public void sendUsage(CommandSender sender) {
        sender.sendMessage(Component.text(getUsage()));
    }

    /**
     * コマンドが適切な条件の下にあるか確認してから実行する
     *
     * @param sender    コマンドの実行者
     * @param alias     コマンドの別名
     * @param arguments コマンドの引数リスト
     * @return 常にtrue
     */
    public boolean execute(CommandSender sender, String alias, String[] arguments) {
        if (arguments.length < minArguments || ((maxArguments < arguments.length) && maxArguments != -1)) {
            sendUsage(sender);
            return true;
        }

        if (playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(Component.text("The sender must be player.").color(NamedTextColor.RED));
            return true;
        }

        if (!testPermission(sender))
            return true;

        if (!onCommand(sender, arguments)) {
            sendUsage(sender);
        }

        return true;
    }

    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String alias, @Nullable String[] arguments) {
        this.onCommand(sender, arguments);
        return true;
    }

    /**
     * コマンドが実行された時の挙動をここに定義する
     *
     * @param sender    コマンドの実行者
     * @param arguments コマンドの引数
     * @return falseを返すことでコマンドの使用方法をプレイヤーに周知できる
     */
    @ParametersAreNonnullByDefault
    public abstract boolean onCommand(CommandSender sender, @Nullable String[] arguments);
}