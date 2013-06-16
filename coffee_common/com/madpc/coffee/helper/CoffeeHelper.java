package com.madpc.coffee.helper;

import net.minecraft.item.ItemStack;

import com.madpc.coffee.item.ModItems;

public class CoffeeHelper {
    
    
    public static String getCoffeeName(ItemStack stack) {
        if (stack.itemID != ModItems.coffee.itemID) return "";
        String name = "Coffee";
        return name;
    }
    
}
