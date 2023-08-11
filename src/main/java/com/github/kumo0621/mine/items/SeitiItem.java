package com.github.kumo0621.mine.items;

import com.github.kumo0621.mine.ItemCreator;
import lombok.Data;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Data
public class SeitiItem implements ISeitiItem {

    /**
     * アイテムの表示名。金床で変更できる
     */
    private final TextComponent name;

    /**
     * アイテムの元となるバニラアイテムの種類
     */
    private final Material material;

    /**
     * アイテムのインスタンスのテンプレート、つまり型<br>
     * アイテムを作製した時これがまず渡されるので必要に応じてインスタンスの固有データを流し込む<br>
     * 注意：絶対に変更してはいけないので必ずクローンして使うこと
     */
    protected ItemCreator itemStackTemplate;

    /**
     * 主に召喚コマンドで用いられる内部的なアイテム名<br>
     */
    private final String internalName;

    /**
     * アイテムのカスタムモデルデータ(0に設定した場合なし)
     */
    private final int customModelData;

    /**
     * 　固有アイテムの型を作成する
     *
     * @param name            作りたい固有アイテムの名前(ユーザーが読むので必ず日本語にすること)
     * @param material        作りたい固有アイテムの元となるバニラアイテム
     * @param internalName    作りたい固有アイテムの内部的な名前<br>
     *                        召喚コマンドで使われるので必ず半角英数字にしてスペースの代わりに_を使うこと
     * @param customModelData 固有アイテムにセットするカスタムモデルデータ
     */
    @ParametersAreNonnullByDefault
    public SeitiItem(TextComponent name, Material material, String internalName, int customModelData) {
        this.name = name;
        this.material = material;
        this.internalName = internalName;
        this.customModelData = customModelData;
        itemStackTemplate = createItem();
    }

    /**
     * 固有アイテムの型の実体を作製する内部的な関数
     *
     * @return 作られたアイテムの型の実体
     */
    private ItemCreator createItem() {
        return new ItemCreator(material)
                .setName(name)
                .setStrNBT("SeitiID", internalName)
                .setCustomModelData(customModelData)
                .setUnbreakable(false);
    }

    public void onGiveCommand(CommandSender sender, String[] argments) {
        Player player = (Player) sender;
        player.getInventory().addItem(getItemStack());
    }

    @Nonnull
    public ItemStack getItemStack() {
        return itemStackTemplate.create();
    }

    public boolean isSimilar(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType().equals(Material.AIR))
            return false;

        String ID = new ItemCreator(itemStack).getStrNBT("SeitiID");

        if (ID == null)
            return false;

        return ID.equals(internalName);
    }
    /**
     * エンチャントを追加
     *
     * @param enchantment  エンチャ
     * @param enchantLevel エンチャレベル
     * @return this
     */
    public SeitiItem addEnchantment(Enchantment enchantment, int enchantLevel) {
        itemStackTemplate.addEnchantment(enchantment, enchantLevel);
        return this;
    }
}