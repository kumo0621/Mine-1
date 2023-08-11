package com.github.kumo0621.mine.items;

import lombok.Getter;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;

public class SeitiTool extends EnchantableSeitiItem {

    @Getter
    private final ToolType toolType;

    /**
     * 　固有アイテムの型を作成する
     *
     * @param name            作りたい固有アイテムの名前(ユーザーが読むので必ず日本語にすること)
     * @param material        作りたい固有アイテムの元となるバニラアイテム
     * @param internalName    作りたい固有アイテムの内部的な名前<br>
     *                        召喚コマンドで使われるので必ず半角英数字にしてスペースの代わりに_を使うこと
     * @param customModelData 固有アイテムにセットするカスタムモデルデータ
     */
    public SeitiTool(TextComponent name, Material material, String internalName, int customModelData, ToolType toolType) {
        super(name, material, internalName, customModelData);
        this.toolType = toolType;
    }

    public enum ToolType {
        PICKAXE, SHOVEL, AXE
    }
}
