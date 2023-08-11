package com.github.kumo0621.mine;

import com.github.kumo0621.mine.items.*;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SeitiUI {

    public static void openMenu(Player player) {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text("メニュー"))
                .rows(1)
                .pageSize(9)
                .disableAllInteractions()
                .create();


        GuiItem PickAxeMenu = new GuiItem(SeitiItems.PICKAXEMENU.get().getItemStack(),
                event -> openToolMenu(player, PurchasableSeitiTool.ToolType.PICKAXE, "ツルハシ強化メニュー"));
        gui.setItem(0, PickAxeMenu);

        GuiItem ShovelMenu = new GuiItem(SeitiItems.SHOVELMENU.get().getItemStack(),
                event -> openToolMenu(player, PurchasableSeitiTool.ToolType.SHOVEL, "シャベル強化メニュー"));
        gui.setItem(1, ShovelMenu);

        GuiItem AxeMenu = new GuiItem(SeitiItems.AXEMENU.get().getItemStack(),
                event -> openToolMenu(player, PurchasableSeitiTool.ToolType.AXE, "斧強化メニュー"));
        gui.setItem(2, AxeMenu);

        GuiItem MiscMenu = new GuiItem(SeitiItems.MISCMENU.get().getItemStack(),
                event -> openMisc(player));
        gui.setItem(3, MiscMenu);

        GuiItem BookMenu = new GuiItem(SeitiItems.BOOKMENU.get().getItemStack(),
                event -> openBook(player));
        gui.setItem(4, BookMenu);

        gui.open(player);
    }

    public static void openToolMenu(Player player, PurchasableSeitiTool.ToolType type, String name) {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text(name))
                .rows(3)
                .pageSize(27)
                .disableAllInteractions()
                .create();

        Object[] objects = Arrays.stream(SeitiItems.values()).map(SeitiItems::get).filter(iSeitiItem -> iSeitiItem instanceof PurchasableSeitiTool
                && ((PurchasableSeitiTool) iSeitiItem).getToolType() == type).toArray();
        PurchasableSeitiTool[] tools = Arrays.copyOf(objects, objects.length, PurchasableSeitiTool[].class);

        for (int i = 0; i < tools.length; i++) {
            int finalI = i;
            GuiItem pickaxeItem = new GuiItem(new ItemCreator(tools[i].getItemStack()).setLore(Component.text(tools[i].getPrice() + "Gで開放")).create(),
                    event -> {
                        if (Mine.getInstance().getMoneyHandler().purchase(player, tools[finalI]))
                            player.sendMessage("ツールを強化しました");
                    });
            gui.setItem(i, pickaxeItem);
        }

        gui.open(player);
    }

    public static void openMisc(Player player) {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text("雑貨メニュー"))
                .rows(3)
                .pageSize(27)
                .disableAllInteractions()
                .create();

        Object[] objects = Arrays.stream(SeitiItems.values()).map(SeitiItems::get).filter(iSeitiItem -> iSeitiItem instanceof PurchasableSeitiItem).toArray();
        PurchasableSeitiItem[] miscItems = Arrays.copyOf(objects, objects.length, PurchasableSeitiItem[].class);

        for (int i = 0; i < miscItems.length; i++) {
            int finalI = i;
            GuiItem miscItem = new GuiItem(new ItemCreator(miscItems[i].getItemStack()).setLore(Component.text(miscItems[i].getPrice() + "Gで開放")).create(),
                    event -> {
                        if (Mine.getInstance().getMoneyHandler().purchase(player, miscItems[finalI]))
                            player.sendMessage(miscItems[finalI].getName().content() + "を買いました");
                    });
            gui.setItem(i, miscItem);
        }

        gui.open(player);
    }

    public static void openBook(Player player) {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text("本メニュー"))
                .rows(3)
                .pageSize(27)
                .disableAllInteractions()
                .create();

        Object[] objects = Arrays.stream(SeitiItems.values()).map(SeitiItems::get).filter(iSeitiItem -> iSeitiItem instanceof EnchantableBookSeitiItem).toArray();
        EnchantableBookSeitiItem[] miscItems = Arrays.copyOf(objects, objects.length, EnchantableBookSeitiItem[].class);

        for (int i = 0; i < miscItems.length; i++) {
            int finalI = i;
            GuiItem miscItem = new GuiItem(new ItemCreator(miscItems[i].getItemStack()).setLore(Component.text(miscItems[i].getPrice() + "Gで開放")).create(),
                    event -> {
                        if (Mine.getInstance().getMoneyHandler().purchase(player, miscItems[finalI]))
                            player.sendMessage(miscItems[finalI].getName().content() + "を買いました");
                    });
            gui.setItem(i, miscItem);
        }

        gui.open(player);
    }
}
