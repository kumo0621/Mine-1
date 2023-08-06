package com.github.kumo0621.mine.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MoveSpeedAccessory extends PurchasableSeitiItem implements IBuffItem {

    /**
     * 　固有アイテムの型を作成する
     */
    public MoveSpeedAccessory() {
        super(Component.text("移動速度上昇アクセサリー"), Material.IRON_PICKAXE, "accessory_speed", 0, 500000);
    }

    @Override
    public void applyBuff(Player player) {
        PotionEffect hasteEffect = new PotionEffect(PotionEffectType.SPEED, buffTime, 2);
        player.addPotionEffect(hasteEffect);
    }
}
