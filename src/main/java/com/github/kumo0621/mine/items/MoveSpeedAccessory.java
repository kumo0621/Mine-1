package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MoveSpeedAccessory extends PurchasableSeitiItem implements IBuffItem {

    /**
     * 　固有アイテムの型を作成する
     */
    public MoveSpeedAccessory() {
        super(Component.text("移動速度上昇アクセサリー"), Material.GRAY_DYE, "accessory_speed", 1, 500000);
    }

    @Override
    public void applyBuff(Player player) {
        PotionEffect hasteEffect = new PotionEffect(PotionEffectType.SPEED, buffTime, 2);
        player.addPotionEffect(hasteEffect);
    }
}
