package com.madpc.coffee.helper;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import com.madpc.coffee.item.ModItems;

public class CoffeeHelper {
    
    public static String getCoffeeName(ItemStack stack) {
        if (stack.itemID != ModItems.coffee.itemID) return "";
        String name = "Coffee";
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.getInteger("poison") > 0) name = "Poisonous " + name;
            if (tag.getBoolean("decaf")) name = "Decaf " + name;
        }
        return name;
    }
    
    public static void addInformation(ItemStack stack, List lines) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        
        NBTTagCompound tag = stack.getTagCompound();
        if (tag.getBoolean("decaf")) lines.add("Not nearly as cool as caffinated");
        else lines.add("Gives a nice little buzz");
        
        int n;
        
        n = tag.getInteger("milk");
        if (n > 0) lines.add("Fills you up");
        
        n = tag.getInteger("sugar");
        if (n > 0) lines.add("Makes you hyper");
        
        n = tag.getInteger("poison");
        if (n > 0) lines.add("May or may not kill you"); 
    }
}
