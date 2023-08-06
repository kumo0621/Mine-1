package com.github.kumo0621.mine;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * アイテムレジストリ
 */
public enum SeitiItems {
    BEGINNER_PICKAXE(new SeitiTool(Component.text("初心者のツルハシ"), Material.DIAMOND_PICKAXE, "beginner_pickaxe", 0, Enchantment.DIG_SPEED, 0, SeitiTool.ToolType.PICKAXE)),
    INTERMEDIATE_PICKAXE(new PurchasableSeitiTool(Component.text("中級者のツルハシ"), Material.DIAMOND_PICKAXE, "intermediate_pickaxe", 0, Enchantment.DIG_SPEED, 0, PurchasableSeitiTool.ToolType.PICKAXE, 1000)),
    HARD_PICKAXE(new PurchasableSeitiTool(Component.text("上級者のツルハシ"), Material.DIAMOND_PICKAXE, "hard_pickaxe", 0, Enchantment.DIG_SPEED, 1, PurchasableSeitiTool.ToolType.PICKAXE, 7000)),
    VETERAN_PICKAXE(new PurchasableSeitiTool(Component.text("ベテランのツルハシ"), Material.DIAMOND_PICKAXE, "veteran_pickaxe", 0, Enchantment.DIG_SPEED, 3, PurchasableSeitiTool.ToolType.PICKAXE, 10000)),
    VERY_VETERAN_PICKAXE(new PurchasableSeitiTool(Component.text("すごくベテランのツルハシ"), Material.DIAMOND_PICKAXE, "very_veteran_pickaxe", 0, Enchantment.DIG_SPEED, 5, PurchasableSeitiTool.ToolType.PICKAXE,20000)),
    GREAT_PICKAXE(new PurchasableSeitiTool(Component.text("すごくすごいベテランのツルハシ"), Material.DIAMOND_PICKAXE, "great_pickaxe", 0, Enchantment.DIG_SPEED, 7, PurchasableSeitiTool.ToolType.PICKAXE, 40000)),
    KING_PICKAXE(new PurchasableSeitiTool(Component.text("帝王のツルハシ"), Material.DIAMOND_PICKAXE, "king_pickaxe", 0, Enchantment.DIG_SPEED, 9, PurchasableSeitiTool.ToolType.PICKAXE, 60000)),
    VERY_KING_PICKAXE(new PurchasableSeitiTool(Component.text("すごい帝王のツルハシ"), Material.DIAMOND_PICKAXE, "very_king_pickaxe", 0, Enchantment.DIG_SPEED, 12, PurchasableSeitiTool.ToolType.PICKAXE, 80000)),
    HADES_PICKAXE(new PurchasableSeitiTool(Component.text("冥王のツルハシ"), Material.DIAMOND_PICKAXE, "hades_pickaxe", 0, Enchantment.DIG_SPEED, 15, PurchasableSeitiTool.ToolType.PICKAXE, 100000)),
    LUCKY_PICKAXE(new EnchantableSeitiItem(Component.text("爆裂ツルハシ"), Material.IRON_PICKAXE, "lucky_pickaxe", 0, Enchantment.DIG_SPEED, 20)),
    RARE_PICKAXE(new SeitiTool(Component.text("レアツルハシ"), Material.DIAMOND_PICKAXE, "rare_pickaxe", 0, Enchantment.DIG_SPEED, 20, SeitiTool.ToolType.PICKAXE)),
    
    BEGINNER_SHOVEL(new SeitiTool(Component.text("初心者のシャベル"), Material.DIAMOND_SHOVEL, "beginner_shovel", 0, Enchantment.DIG_SPEED, 0, SeitiTool.ToolType.SHOVEL)),
    INTERMEDIATE_SHOVEL(new PurchasableSeitiTool(Component.text("中級者のシャベル"), Material.DIAMOND_SHOVEL, "intermediate_shovel", 0, Enchantment.DIG_SPEED, 0, PurchasableSeitiTool.ToolType.SHOVEL, 1000)),

    BEGINNER_AXE(new SeitiTool(Component.text("初心者の斧"), Material.DIAMOND_AXE, "beginner_axe", 1, Enchantment.DIG_SPEED, 0, SeitiTool.ToolType.AXE)),
    INTERMEDIATE_AXE(new PurchasableSeitiTool(Component.text("中級者の斧"), Material.DIAMOND_AXE, "intermediate_axe", 1, Enchantment.DIG_SPEED, 0, PurchasableSeitiTool.ToolType.AXE, 1000)),
    
    ACCESSORY_SPEED(new PurchasableSeitiItem(Component.text("移動速度上昇アクセサリー（右クリック）"), Material.IRON_PICKAXE, "accessory_speed", 0, 500000)),
    ACCESSORY_MINE(new PurchasableSeitiItem(Component.text("採掘速度上昇アクセサリー（右クリック）"), Material.IRON_PICKAXE, "accessory_mine", 0, 500000)),
    STEAK(new PurchasableSeitiItem(Component.text("飯"), Material.COOKED_BEEF, "steak", 0, 1)),
    SPONGE(new PurchasableSeitiItem(Component.text("スポンジ"), Material.SPONGE, "sponge", 0, 1)),
    SHULKER_BOX(new PurchasableSeitiItem(Component.text("シュルカーボックス"), Material.SHULKER_BOX, "shulker_box", 0, 1000)),
    TNT(new PurchasableSeitiItem(Component.text("TNTを右クリックで召喚"), Material.SHULKER_BOX, "tnt", 0, 100)),
    SELLER(new PurchasableSeitiItem(Component.text("売却を右クリックでできる"), Material.PAPER, "seller", 0, 500)),
    BLOCKBREAK(new PurchasableSeitiItem(Component.text("右クリックで範囲採掘できる"), Material.PAPER, "blockbreak", 0, 100)),
    
    PICKAXEMENU(new SeitiItem(Component.text("ピッケル強化メニュー"), Material.STONE, "pickaxemenu", 0)),
    SHOVELMENU(new SeitiItem(Component.text("シャベル強化メニュー"), Material.DIRT, "shovelmenu", 0)),
    AXEMENU(new SeitiItem(Component.text("オノ強化メニュー"), Material.OAK_LOG, "axemenu", 0)),
    MISCMENU(new SeitiItem(Component.text("雑貨メニュー"), Material.ANVIL, "miscmenu", 0));
    
    
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
     * @param itemStack 変換したいアイテム
     * @return 変換された固有アイテム
     */
    @Nullable
    public static ISeitiItem toSeitiItem(ItemStack itemStack){
        SeitiItems iocItem = Arrays.stream(SeitiItems.values()).filter(e-> e.get().isSimilar(itemStack)).findFirst().orElse(null);
        return iocItem == null ? null : iocItem.get();
    }

    /**
     * 固有アイテムの召喚コマンドを固有アイテムに変換する
     * @param command 召喚コマンド
     * @return 変換された固有アイテム
     */
    @Nullable
    public static ISeitiItem commandToSeitiItem(String command){
        SeitiItems iocItem = Arrays.stream(SeitiItems.values()).filter(e -> e.get().getInternalName().equals(command)).findFirst().orElse(null);
        return iocItem == null ? null : iocItem.get();
    }
}
