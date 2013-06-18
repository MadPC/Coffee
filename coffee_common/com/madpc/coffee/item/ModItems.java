package com.madpc.coffee.item;

import net.minecraft.item.Item;

public class ModItems {
    public static Item coffee;
    public static Item coffeeBeans;
    public static Item coffeeFilter;
    
    public static void init() {
        coffee = new ItemCoffee(1200);
        coffeeBeans = new ItemCustom(1201);
        coffeeFilter = new ItemCustom(1202);
    }
}
