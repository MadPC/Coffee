package com.madpc.coffee;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.madpc.coffee.item.ModItems;

public class RecipeCoffee implements IRecipe {
    
    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        boolean coffee = false;
        boolean hasSpice = false;
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            ItemStack slotStack = inv.getStackInSlot(slot);
            if (slotStack != null) {
                if (!coffee && slotStack.itemID == ModItems.coffee.itemID) coffee = true;
                if (CoffeeHelper.isSpice(slotStack.itemID)) hasSpice = true;
            }
        }
        
        return coffee && hasSpice;
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack coffee = null;
        List<ItemStack> spices = new ArrayList<ItemStack>();
        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            ItemStack slotStack = inv.getStackInSlot(slot);
            if (slotStack != null) if (coffee == null && slotStack.itemID == ModItems.coffee.itemID) coffee = slotStack;
            else if (CoffeeHelper.isSpice(slotStack.itemID)) spices.add(slotStack);
        }
        
        return CoffeeHelper.addEffects(coffee, spices);
    }
    
    @Override
    public int getRecipeSize() {
        return 0;
    }
    
    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
    
}
