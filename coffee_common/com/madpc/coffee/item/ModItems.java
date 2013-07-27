package com.madpc.coffee.item;

import net.minecraft.item.Item;

import com.madpc.coffee.Coffee;

public class ModItems {
    public static Item coffee;
    public static Item coffeeBeans;
    public static Item coffeeFilter;
    public static Item coffeaBerry;
    
    public static void init() {
        ModItems.coffee = new ItemCoffee(Coffee.config.getItem("Coffee", 4200).getInt()).setUnlocalizedName("coffee").setCreativeTab(Coffee.tabsCoffee);
        ModItems.coffeeBeans = new ItemCustom(Coffee.config.getItem("CoffeeBeans", 4201).getInt()).setUnlocalizedName("coffeeBeans").setCreativeTab(Coffee.tabsCoffee);
        ModItems.coffeeFilter = new ItemCustom(Coffee.config.getItem("CoffeeFilter", 4202).getInt()).setMaxDamage(8).setUnlocalizedName("coffeeFilter").setCreativeTab(Coffee.tabsCoffee);
        ModItems.coffeaBerry = new ItemCustom(Coffee.config.getItem("CoffeaBerry", 4203).getInt()).setUnlocalizedName("coffeaBerry").setCreativeTab(Coffee.tabsCoffee);
    }
}
