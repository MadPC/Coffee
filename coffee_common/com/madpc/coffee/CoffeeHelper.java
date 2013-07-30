package com.madpc.coffee;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import com.madpc.coffee.item.ModItems;

public class CoffeeHelper {
    
    public static String getCoffeeName(ItemStack stack) {
        if (stack.itemID != ModItems.coffee.itemID) return "";
        String name = StatCollector.translateToLocal("item.coffee.name");
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.getBoolean("decaf")) name = StatCollector.translateToLocal("coffee.effect.decaf") + name;
            if (tag.getInteger("poison") > 0) name = StatCollector.translateToLocal("coffee.effect.posion") + name;
            if (tag.getInteger("sugar") > 0) name = StatCollector.translateToLocal("coffee.effect.sugar") + name;
            if (tag.getInteger("milk") > 0) name = StatCollector.translateToLocal("coffee.effect.milk") + name;
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
    
    public static boolean isSpice(int id) {
        return id == Item.bucketMilk.itemID || id == Item.sugar.itemID || id == Item.spiderEye.itemID;
    }
    
    public static ItemStack getResult(List<ItemStack> spices) {
        ItemStack result = new ItemStack(ModItems.coffee.itemID, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        for (ItemStack spice : spices) {
            if (spice == null) continue;
            if (spice.itemID == Item.bucketMilk.itemID) tag.setInteger("milk", tag.getInteger("milk") + 1);
            else if (spice.itemID == Item.spiderEye.itemID) tag.setInteger("poison", tag.getInteger("poison") + 1);
            else if (spice.itemID == Item.sugar.itemID) tag.setInteger("sugar", tag.getInteger("sugar") + 2);
        }
        result.setTagCompound(tag);
        return result;
    }
    
    public static ItemStack getResult(ItemStack... spices) {
        List<ItemStack> lspices = new ArrayList<ItemStack>();
        for (ItemStack spice : spices)
            lspices.add(spice);
        return CoffeeHelper.getResult(lspices);
    }
    
    public static ItemStack addEffects(ItemStack coffee, List<ItemStack> spices) {
        ItemStack b = CoffeeHelper.getResult(spices);
        b.getTagCompound().setBoolean("decaf", true);
        return CoffeeHelper.combineEffects(coffee.copy(), b);
    }
    
    public static ItemStack combineEffects(ItemStack a, ItemStack b) {
        ItemStack r = a.copy();
        NBTTagCompound rtag = a.getTagCompound();
        if (rtag == null) rtag = new NBTTagCompound();
        NBTTagCompound btag = b.getTagCompound();
        if (btag != null) {
            rtag.setInteger("milk", rtag.getInteger("milk") + btag.getInteger("milk"));
            rtag.setInteger("sugar", rtag.getInteger("sugar") + btag.getInteger("sugar"));
            rtag.setInteger("poison", rtag.getInteger("poison") + btag.getInteger("poison"));
            rtag.setBoolean("decaf", rtag.getBoolean("decaf") && btag.getBoolean("decaf"));
        }
        
        r.setTagCompound(rtag);
        return r;
    }
}
