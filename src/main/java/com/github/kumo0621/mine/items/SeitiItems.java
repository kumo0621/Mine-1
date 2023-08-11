package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * アイテムレジストリ
 */
public enum SeitiItems {
    BEGINNER_PICKAXE(new SeitiTool(Component.text("初心者のツルハシ"), Material.DIAMOND_PICKAXE, "beginner_pickaxe", 1, SeitiTool.ToolType.PICKAXE).addEnchantment( Enchantment.DIG_SPEED, 0)),
    INTERMEDIATE_PICKAXE(new PurchasableSeitiTool(Component.text("中級者のツルハシ"), Material.DIAMOND_PICKAXE, "intermediate_pickaxe", 2, PurchasableSeitiTool.ToolType.PICKAXE, 1000).addEnchantment(Enchantment.DIG_SPEED, 1)),
    HARD_PICKAXE(new PurchasableSeitiTool(Component.text("上級者のツルハシ"), Material.DIAMOND_PICKAXE, "hard_pickaxe", 3, PurchasableSeitiTool.ToolType.PICKAXE, 7000).addEnchantment(Enchantment.DIG_SPEED, 2)),
    VETERAN_PICKAXE(new PurchasableSeitiTool(Component.text("ベテランのツルハシ"), Material.DIAMOND_PICKAXE, "veteran_pickaxe", 4, PurchasableSeitiTool.ToolType.PICKAXE, 10000).addEnchantment(Enchantment.DIG_SPEED, 3)),
    VERY_VETERAN_PICKAXE(new PurchasableSeitiTool(Component.text("すごくベテランのツルハシ"), Material.DIAMOND_PICKAXE, "very_veteran_pickaxe", 5, PurchasableSeitiTool.ToolType.PICKAXE, 20000).addEnchantment(Enchantment.DIG_SPEED, 5)),
    GREAT_PICKAXE(new PurchasableSeitiTool(Component.text("すごくすごいベテランのツルハシ"), Material.DIAMOND_PICKAXE, "great_pickaxe", 6, PurchasableSeitiTool.ToolType.PICKAXE, 40000).addEnchantment(Enchantment.DIG_SPEED, 7)),
    KING_PICKAXE(new PurchasableSeitiTool(Component.text("帝王のツルハシ"), Material.DIAMOND_PICKAXE, "king_pickaxe", 7, PurchasableSeitiTool.ToolType.PICKAXE, 60000).addEnchantment(Enchantment.DIG_SPEED, 9)),
    VERY_KING_PICKAXE(new PurchasableSeitiTool(Component.text("すごい帝王のツルハシ"), Material.DIAMOND_PICKAXE, "very_king_pickaxe", 8, PurchasableSeitiTool.ToolType.PICKAXE, 80000).addEnchantment(Enchantment.DIG_SPEED, 12)),
    HADES_PICKAXE(new PurchasableSeitiTool(Component.text("冥王のツルハシ"), Material.DIAMOND_PICKAXE, "hades_pickaxe", 9, PurchasableSeitiTool.ToolType.PICKAXE, 100000).addEnchantment(Enchantment.DIG_SPEED, 15)),
    LUCKY_PICKAXE(new PurchasableSeitiTool(Component.text("爆裂ツルハシ"), Material.IRON_PICKAXE, "lucky_pickaxe", 10, PurchasableSeitiTool.ToolType.PICKAXE, 1000000).addEnchantment(Enchantment.DIG_SPEED, 20)),
    SILK_TOUCH_PICKAXE(new PurchasableSeitiTool(Component.text("シルクツルハシ"), Material.DIAMOND_PICKAXE, "SILK_TOUCH_pickaxe", 11, PurchasableSeitiTool.ToolType.PICKAXE, 2000000).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.SILK_TOUCH, 1)),
    SILK_TOUCH2_PICKAXE(new PurchasableSeitiTool(Component.text("シルクレベル2ツルハシ"), Material.DIAMOND_PICKAXE, "SILK_TOUCH2_pickaxe", 12, PurchasableSeitiTool.ToolType.PICKAXE, 3000000).addEnchantment(Enchantment.DIG_SPEED, 8).addEnchantment(Enchantment.SILK_TOUCH, 1)),
    SILK_TOUCH3_PICKAXE(new PurchasableSeitiTool(Component.text("シルクレベル3ツルハシ"), Material.DIAMOND_PICKAXE, "SILK_TOUCH3_pickaxe", 13, PurchasableSeitiTool.ToolType.PICKAXE, 4000000).addEnchantment(Enchantment.DIG_SPEED, 12).addEnchantment(Enchantment.SILK_TOUCH, 1)),
    lucky2_PICKAXE(new PurchasableSeitiTool(Component.text("ラッキーツルハシ"), Material.DIAMOND_PICKAXE, "lucky_pickaxe", 14, PurchasableSeitiTool.ToolType.PICKAXE, 5000000).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1)),
    lucky_SILK_PICKAXE(new PurchasableSeitiTool(Component.text("ラッキーシルクツルハシ"), Material.DIAMOND_PICKAXE, "lucky_SILK_pickaxe", 15, PurchasableSeitiTool.ToolType.PICKAXE, 9000000).addEnchantment(Enchantment.DIG_SPEED, 8).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2)),
    lucky_SILK2_PICKAXE(new PurchasableSeitiTool(Component.text("ラッキーシルク魔界ツルハシ"), Material.DIAMOND_PICKAXE, "lucky_SILK2_pickaxe", 16, PurchasableSeitiTool.ToolType.PICKAXE, 10000000).addEnchantment(Enchantment.DIG_SPEED, 15).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3)),

    SILK_TOUCH(new EnchantableBookSeitiItem(Component.text("シルクタッチ"), Material.ENCHANTED_BOOK, "シルクタッチ", 0, SeitiTool.ToolType.PICKAXE, 10000).addEnchantment(Enchantment.SILK_TOUCH, 0)),

    BEGINNER_SHOVEL(new SeitiTool(Component.text("初心者のシャベル"), Material.DIAMOND_SHOVEL, "beginner_shovel", 1, SeitiTool.ToolType.SHOVEL).addEnchantment(Enchantment.DIG_SPEED, 0)),
    INTERMEDIATE_SHOVEL(new PurchasableSeitiTool(Component.text("中級者のシャベル"), Material.DIAMOND_SHOVEL, "intermediate_shovel", 2, PurchasableSeitiTool.ToolType.SHOVEL, 1000).addEnchantment(Enchantment.DIG_SPEED, 2)),
    Senior_SHOVEL(new PurchasableSeitiTool(Component.text("上級者のシャベル"), Material.DIAMOND_SHOVEL, "Senior_shovel", 3, PurchasableSeitiTool.ToolType.SHOVEL, 20000).addEnchantment(Enchantment.DIG_SPEED, 3)),
    very_SHOVEL(new PurchasableSeitiTool(Component.text("すごいのシャベル"), Material.DIAMOND_SHOVEL, "very_shovel", 4, PurchasableSeitiTool.ToolType.SHOVEL, 50000).addEnchantment(Enchantment.DIG_SPEED, 6)),
    Mecha_SHOVEL(new PurchasableSeitiTool(Component.text("めっちゃのシャベル"), Material.DIAMOND_SHOVEL, "Mecha_shovel", 5, PurchasableSeitiTool.ToolType.SHOVEL, 80000).addEnchantment(Enchantment.DIG_SPEED, 9)),
    Ultra_SHOVEL(new PurchasableSeitiTool(Component.text("ウルトラシャベル"), Material.DIAMOND_SHOVEL, "Ultra_shovel", 6, PurchasableSeitiTool.ToolType.SHOVEL, 100000).addEnchantment(Enchantment.DIG_SPEED, 12)),
    Fire_SHOVEL(new PurchasableSeitiTool(Component.text("ファイヤーシャベル"), Material.DIAMOND_SHOVEL, "Fire_shovel", 7, PurchasableSeitiTool.ToolType.SHOVEL, 100000).addEnchantment(Enchantment.DIG_SPEED, 5)),

    BEGINNER_AXE(new SeitiTool(Component.text("初心者の斧"), Material.DIAMOND_AXE, "beginner_axe", 1, SeitiTool.ToolType.AXE).addEnchantment(Enchantment.DIG_SPEED, 0)),
    INTERMEDIATE_AXE(new PurchasableSeitiTool(Component.text("中級者の斧"), Material.DIAMOND_AXE, "intermediate_axe", 2, PurchasableSeitiTool.ToolType.AXE, 1000).addEnchantment(Enchantment.DIG_SPEED, 1)),
    Senior_AXE(new PurchasableSeitiTool(Component.text("上級者の斧"), Material.DIAMOND_AXE, "Senior_axe", 3, PurchasableSeitiTool.ToolType.AXE, 20000).addEnchantment(Enchantment.DIG_SPEED, 2)),
    very_AXE(new PurchasableSeitiTool(Component.text("すごいの斧"), Material.DIAMOND_AXE, "very_axe", 4, PurchasableSeitiTool.ToolType.AXE, 40000).addEnchantment(Enchantment.DIG_SPEED, 3)),
    Mecha_AXE(new PurchasableSeitiTool(Component.text("めっちゃの斧"), Material.DIAMOND_AXE, "Mecha_axe", 5, PurchasableSeitiTool.ToolType.AXE, 80000).addEnchantment(Enchantment.DIG_SPEED, 4)),
    Ultra_AXE(new PurchasableSeitiTool(Component.text("ウルトラの斧"), Material.DIAMOND_AXE, "Ultra_axe", 6, PurchasableSeitiTool.ToolType.AXE, 100000).addEnchantment(Enchantment.DIG_SPEED, 5)),

    ACCESSORY_SPEED(new MoveSpeedAccessory()),
    ACCESSORY_MINE(new MineSpeedAccessory()),
    Fire_MINE(new FireAccessory()),
    STEAK(new PurchasableSeitiItem(Component.text("飯"), Material.COOKED_BEEF, "steak", 0, 1)),
    COAL(new PurchasableSeitiItem(Component.text("燃料"), Material.COAL, "steak", 0, 16)),
    SPONGE(new PurchasableSeitiItem(Component.text("スポンジ"), Material.SPONGE, "sponge", 0, 1)),
    SHULKER_BOX(new PurchasableSeitiItem(Component.text("シュルカーボックス"), Material.SHULKER_BOX, "shulker_box", 0, 1000)),
    TNT(new TNTSummoner()),
    SELLER(new RemoteSeller()),
    BLOCKBREAK(new AreaMiner()),
    BLOCKSET(new BlockSet()),
    PICKAXEMENU(new SeitiItem(Component.text("§fツルハシ強化メニュー"), Material.STONE, "pickaxemenu", 0)),
    SHOVELMENU(new SeitiItem(Component.text("§fシャベル強化メニュー"), Material.DIRT, "shovelmenu", 0)),
    AXEMENU(new SeitiItem(Component.text("§fオノ強化メニュー"), Material.OAK_LOG, "axemenu", 0)),
    MISCMENU(new SeitiItem(Component.text("§f雑貨メニュー"), Material.ANVIL, "miscmenu", 0)),
    BOOKMENU(new SeitiItem(Component.text("§f本メニュー"), Material.ENCHANTED_BOOK, "bookmenu", 0));



    private final ISeitiItem seitiItem;

    SeitiItems(ISeitiItem ocItem) {
        this.seitiItem = ocItem;
    }

    public ISeitiItem get() {
        return seitiItem;
    }

    /**
     * アイテムが固有アイテムであった場合その実体を固有アイテムクラスに変換する<br>
     * この際実体特有のNBTなどは失われる
     *
     * @param itemStack 変換したいアイテム
     * @return 変換された固有アイテム
     */
    @Nullable
    public static ISeitiItem toSeitiItem(ItemStack itemStack) {
        SeitiItems iocItem = Arrays.stream(SeitiItems.values()).filter(e -> e.get().isSimilar(itemStack)).findFirst().orElse(null);
        return iocItem == null ? null : iocItem.get();
    }

    /**
     * 固有アイテムの召喚コマンドを固有アイテムに変換する
     *
     * @param command 召喚コマンド
     * @return 変換された固有アイテム
     */
    @Nullable
    public static ISeitiItem commandToSeitiItem(String command) {
        SeitiItems iocItem = Arrays.stream(SeitiItems.values()).filter(e -> e.get().getInternalName().equals(command)).findFirst().orElse(null);
        return iocItem == null ? null : iocItem.get();
    }
}
