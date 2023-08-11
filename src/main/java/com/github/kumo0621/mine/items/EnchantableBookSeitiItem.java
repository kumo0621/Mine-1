package com.github.kumo0621.mine.items;

import com.github.kumo0621.mine.ItemCreator;
import lombok.Getter;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;

public class EnchantableBookSeitiItem extends SeitiItem implements IPurchasableItem{

    @Getter
    private final int price;

    /**
     * 　固有アイテムの型を作成する
     *
     * @param displayName            作りたい固有アイテムの名前(ユーザーが読むので必ず日本語にすること)
     * @param material        作りたい固有アイテムの元となるバニラアイテム
     * @param internalName    作りたい固有アイテムの内部的な名前<br>
     *                        召喚コマンドで使われるので必ず半角英数字にしてスペースの代わりに_を使うこと
     * @param customModelData 固有アイテムにセットするカスタムモデルデータ
     */
    public EnchantableBookSeitiItem(TextComponent displayName, Material material, String internalName, int customModelData, SeitiTool.ToolType toolType,int price) {
        super(displayName, material, internalName, customModelData);
        itemStackTemplate = createItem();
        this.price = price;
    }

    private ItemCreator createItem() {
        return new ItemCreator(getMaterial())
                .setName(getName())
                .setStrNBT("SeitiID", getInternalName())
                .setCustomModelData(getCustomModelData())
                .setUnbreakable(true);
    }
}
