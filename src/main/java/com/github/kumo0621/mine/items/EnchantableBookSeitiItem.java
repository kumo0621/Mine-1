package com.github.kumo0621.mine.items;

import com.github.kumo0621.mine.ItemCreator;
import lombok.Getter;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantableBookSeitiItem extends SeitiItem implements IPurchasableItem{

    @Getter
    private final Enchantment enchantment;
    @Getter
    private final int enchantLevel;
    @Getter
    private final int price;
    /**
     * 　固有アイテムの型を作成する
     *
     * @param name            作りたい固有アイテムの名前(ユーザーが読むので必ず日本語にすること)
     * @param material        作りたい固有アイテムの元となるバニラアイテム
     * @param internalName    作りたい固有アイテムの内部的な名前<br>
     *                        召喚コマンドで使われるので必ず半角英数字にしてスペースの代わりに_を使うこと
     * @param customModelData 固有アイテムにセットするカスタムモデルデータ
     * @param enchantment     エンチャ
     * @param enchantLevel    エンチャレベル
     */
    public EnchantableBookSeitiItem(TextComponent name, Material material, String internalName, int customModelData, Enchantment enchantment, int enchantLevel, SeitiTool.ToolType toolType,int price) {
        super(name, material, internalName, customModelData);
        this.enchantment = enchantment;
        this.enchantLevel = enchantLevel;
        itemStackTemplate = createItem();
        this.price = price;
    }

    @Override
    protected ItemStack createItem() {
        if (enchantment == null)
            return null;

        return new ItemCreator(getMaterial())
                .setName(getName())
                .setStrNBT("SeitiID", getInternalName())
                .setCustomModelData(getCustomModelData())
                .addEnchantment(enchantment, enchantLevel)
                .setUnbreakable(true)
                .create();
    }
}
