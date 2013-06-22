package com.madpc.coffee.item;

import net.minecraft.item.Item;

import com.madpc.coffee.Coffee;

public class ModItems {
    public static Item coffee;
    public static Item coffeeBeans;
    public static Item coffeeFilter;
    
    public static void init() {
        coffee = new ItemCoffee(1200).setUnlocalizedName("coffee").setCreativeTab(Coffee.tabsCoffee);
        coffeeBeans = new ItemCustom(1201).setUnlocalizedName("coffeeBeans").setCreativeTab(Coffee.tabsCoffee);
        coffeeFilter = new ItemCustom(1202).setMaxDamage(8).setUnlocalizedName("coffeeFilter").setCreativeTab(Coffee.tabsCoffee);
    }
}
