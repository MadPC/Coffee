package com.madpc.coffee.helper;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.madpc.coffee.item.ModItems;

public class CoffeeHelper {
    
    public static String getCoffeeName(ItemStack stack) {
        if (stack.getItem() != ModItems.coffee) return "";
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
    
    public static boolean isSpice(Item item) {
        return item == Items.milk_bucket|| item == Items.sugar
                || item == Items.spider_eye;
    }
    
    public static ItemStack getResult(ItemStack... spices) {
        ItemStack result = new ItemStack(ModItems.coffee, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        for (ItemStack spice : spices) {
            if (spice == null) continue;
            if (spice.getItem() == Items.milk_bucket)
                tag.setInteger("milk", tag.getInteger("milk") + spice.stackSize);
            else if (spice.getItem() == Items.spider_eye)
                tag.setInteger("poison", tag.getInteger("poison") + spice.stackSize);
            else if (spice.getItem() == Items.sugar)
                tag.setInteger("sugar", tag.getInteger("sugar") + spice.stackSize);
        }
        result.setTagCompound(tag);
        return result;
    }
}
