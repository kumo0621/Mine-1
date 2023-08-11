package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MineSpeedAccessory extends PurchasableSeitiItem implements IBuffItem {

    /**
     * 　固有アイテムの型を作成する
     */
    public MineSpeedAccessory() {
        super(Component.text("採掘速度上昇アクセサリー"), Material.GRAY_DYE, "accessory_mine", 2, 500000);
    }

    @Override
    public void applyBuff(Player player) {
        PotionEffect hasteEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, buffTime, 2);
        player.addPotionEffect(hasteEffect);
    }
}
