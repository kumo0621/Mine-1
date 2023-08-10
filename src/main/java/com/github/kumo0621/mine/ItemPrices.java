package com.github.kumo0621.mine;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;

import java.util.Arrays;

public enum ItemPrices {
    DIRT(Material.DIRT, 5),
    ENDSTONE(Material.END_STONE,  6),
    OBSIDIAN(Material.OBSIDIAN, 8),
    AMETHYST_SHARD(Material.AMETHYST_SHARD,3),
    NETHERRACK(Material.NETHERRACK, 1),
    GRASS_BLOCK(Material.GRASS_BLOCK, 10),
    STONE(Material.STONE, 5),
    COAL(Material.COAL, 10),
    LAPIS_LAZULI(Material.LAPIS_LAZULI, 17),
    GOLD_INGOT(Material.GOLD_INGOT, 15),
    IRON_INGOT(Material.IRON_INGOT, 14),
    DEEPSLATE(Material.DEEPSLATE, 5),
    DIAMOND(Material.DIAMOND, 10),
    REDSTONE_WIRE(Material.REDSTONE_WIRE, 6),
    DEEPSLATE_COAL_ORE(Material.DEEPSLATE_COAL_ORE, 15),
    DEEPSLATE_IRON_ORE(Material.DEEPSLATE_IRON_ORE, 15),
    DEEPSLATE_COPPER_ORE(Material.DEEPSLATE_COPPER_ORE, 4),
    COPPER_ORE(Material.COPPER_ORE, 4),
    GOLD_ORE(Material.GOLD_ORE, 20),
    REDSTONE(Material.REDSTONE_BLOCK, 15),
    EMERALD_ORE(Material.EMERALD_ORE, 20),
    DEEPSLATE_EMERALD_ORE(Material.DEEPSLATE_EMERALD_ORE, 20),
    DEEPSLATE_LAPIS_ORE(Material.DEEPSLATE_LAPIS_ORE, 14),
    DEEPSLATE_DIAMOND_ORE(Material.DEEPSLATE_DIAMOND_ORE, 20),
    OAK_LOG(Material.OAK_LOG, 10),
    JUNGLE_LOG(Material.JUNGLE_LOG, 10),
    ACACIA_LOG(Material.ACACIA_LOG, 10),
    BIRCH_LOG(Material.BIRCH_LOG, 10),
    SPRUCE_LOG(Material.SPRUCE_LOG, 10),
    COBBLED_DEEPSLATE(Material.COBBLED_DEEPSLATE, 5),
    GRANITE(Material.GRANITE, 5),
    DIORITE(Material.DIORITE,5),
    ANDESITE(Material.ANDESITE ,5),
    COBBLESTONE(Material.COBBLESTONE, 5);


    private final Material material;
    private final int price;

    ItemPrices(Material material, int price) {
        this.material = material;
        this.price = price;
    }

    public static int getprice(ItemStack itemStack) {
        if (itemStack == null)
            return 0;

        ItemPrices itemPrices = Arrays.stream(ItemPrices.values()).filter(e -> e.material == itemStack.getType()).findFirst().orElse(null);
        if (itemPrices == null)
            return 0;

        return itemPrices.price * itemStack.getAmount();
    }
}