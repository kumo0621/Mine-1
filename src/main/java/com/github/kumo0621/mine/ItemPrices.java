package com.github.kumo0621.mine;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum ItemPrices {

    // 地上地形系
    DIRT(Material.DIRT, 5),
    GRASS_BLOCK(Material.GRASS_BLOCK, 10),
    SAND(Material.SAND, 5),
    SANDSTONE(Material.SANDSTONE, 5),
    RED_SAND(Material.RED_SAND, 5),
    RED_SANDSTONE(Material.RED_SANDSTONE, 5),
    CLAY(Material.CLAY, 5),
    GRAVEL(Material.GRAVEL, 5),

    // 地下地形系
    STONE(Material.STONE, 5),
    COBBLESTONE(Material.COBBLESTONE, 5),
    DEEPSLATE(Material.DEEPSLATE, 5),
    COBBLED_DEEPSLATE(Material.COBBLED_DEEPSLATE, 5),
    ENDSTONE(Material.END_STONE,  20),
    NETHERRACK(Material.NETHERRACK, 1),
    OBSIDIAN(Material.OBSIDIAN, 20),
    ANDESITE(Material.ANDESITE, 5),
    DIORITE(Material.DIORITE, 5),
    GRANITE(Material.GRANITE, 5),
    BASALT(Material.BASALT, 5),
    BLACKSTONE(Material.BLACKSTONE, 5),

    // 鉱石
    COAL(Material.COAL, 15),
    COAL_ORE(Material.COAL_ORE, 15),
    IRON_INGOT(Material.IRON_INGOT, 14),
    IRON_ORE(Material.IRON_ORE, 14),
    GOLD_INGOT(Material.GOLD_INGOT, 30),
    GOLD_ORE(Material.GOLD_ORE, 30),
    LAPIS_LAZULI(Material.LAPIS_LAZULI, 17),
    LAPIS_ORE(Material.LAPIS_ORE, 17),
    REDSTONE_WIRE(Material.REDSTONE_WIRE, 6),
    REDSTONE_ORE(Material.REDSTONE_ORE, 6),
    COPPER_ORE(Material.COPPER_ORE, 4),
    COPPER_INGOT(Material.COPPER_INGOT, 4),
    DIAMOND_ORE(Material.DIAMOND_ORE, 20),
    DIAMOND(Material.DIAMOND, 20),
    EMERALD(Material.EMERALD, 20),
    EMERALD_ORE(Material.EMERALD_ORE, 20),
    DEEPSLATE_COAL_ORE(Material.DEEPSLATE_COAL_ORE, 15),
    DEEPSLATE_IRON_ORE(Material.DEEPSLATE_IRON_ORE, 15),
    DEEPSLATE_GOLD_ORE(Material.DEEPSLATE_GOLD_ORE, 30),
    DEEPSLATE_LAPIS_ORE(Material.DEEPSLATE_LAPIS_ORE, 14),
    DEEPSLATE_REDSTONE_ORE(Material.DEEPSLATE_REDSTONE_ORE, 6),
    DEEPSLATE_COPPER_ORE(Material.DEEPSLATE_COPPER_ORE, 4),
    DEEPSLATE_DIAMOND_ORE(Material.DEEPSLATE_DIAMOND_ORE, 20),
    DEEPSLATE_EMERALD_ORE(Material.DEEPSLATE_EMERALD_ORE, 20),
    AMETHYST_SHARD(Material.AMETHYST_SHARD,3),
    AMETHYST_BLOCK(Material.AMETHYST_BLOCK, 3),
    NETHER_QUARTZ(Material.QUARTZ, 3),
    NETHER_QUARTZ_ORE(Material.NETHER_QUARTZ_ORE, 3),
    NETHER_GOLD_ORE(Material.NETHER_GOLD_ORE, 3),
    ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS, 3),
    NETHERITE_SCRAP(Material.NETHERITE_SCRAP, 3),
    NETHERITE_INGOT(Material.NETHERITE_INGOT, 3),
    
    // 木系
    OAK_LOG(Material.OAK_LOG, 10),
    JUNGLE_LOG(Material.JUNGLE_LOG, 10),
    ACACIA_LOG(Material.ACACIA_LOG, 10),
    BIRCH_LOG(Material.BIRCH_LOG, 10),
    SPRUCE_LOG(Material.SPRUCE_LOG, 10);

    private final Material material;
    private final int price;

    ItemPrices(Material material, int price) {
        this.material = material;
        this.price = price;
    }

    public static int getPrice(ItemStack itemStack) {
        if (itemStack == null)
            return 0;

        ItemPrices itemPrices = Arrays.stream(ItemPrices.values()).filter(e -> e.material == itemStack.getType()).findFirst().orElse(null);
        if (itemPrices == null)
            return 0;

        return itemPrices.price * itemStack.getAmount();
    }
}