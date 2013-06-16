package com.madpc.coffee.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
    public static Item coffee;
    
    public static void init() {
        coffee = new ItemCoffee(1200).setUnlocalizedName("coffee").setCreativeTab(CreativeTabs.tabFood);
    }
}
