package com.madpc.coffee.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.madpc.coffee.helper.CoffeeHelper;
import com.madpc.coffee.item.ModItems;

public class TileEntityCoffeeMaker extends TileEntity implements
        ISidedInventory {
    
    /**
     * 0 = Beans, 1 = Filter, 2 = Water, 3 - 6 = spices, 7 = output
     */
    public ItemStack[] inventory = new ItemStack[8];
    public int waterLevel = 0;
    public final int maxWaterLevel = 0;
    
    public int getProgressScaled(int i) {
        return 0;
    }
    
    @Override
    public int getSizeInventory() {
        return inventory.length;
    }
    
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (inventory[slot] == null) return null;
        
        ItemStack r;
        if (amount > inventory[slot].stackSize) {
            r = inventory[slot];
            inventory[slot] = null;
            return r;
        }
        
        r = inventory[slot].splitStack(amount);
        if (inventory[slot].stackSize == 0) inventory[slot] = null;
        return r;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) stack.stackSize = this.getInventoryStackLimit();
    }
    
    @Override
    public String getInvName() {
        return "container.coffeeMaker";
    }
    
    @Override
    public boolean isInvNameLocalized() {
        return false;
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }
    
    @Override
    public void openChest() {
        
    }
    
    @Override
    public void closeChest() {
        
    }
    
    @Override
    public boolean isStackValidForSlot(int slot, ItemStack stack) {
        switch (slot) {
            case 0:
                return stack.itemID == ModItems.coffeeBeans.itemID;
            case 1:
                return stack.itemID == ModItems.coffeeFilter.itemID;
            case 2:
                return stack.itemID == Item.bucketWater.itemID;
            case 3:
            case 4:
            case 5:
            case 6:
                return CoffeeHelper.isSpice(stack.itemID);
            default:
                return false;
        }
    }
    
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return null;
    }
    
    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int j) {
        return this.isStackValidForSlot(slot, stack);
    }
    
    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int j) {
        return false;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList invTag = tag.getTagList("inventory");
        
        for (int i = 0; i < invTag.tagCount(); i++) {
            NBTTagCompound itemTag = (NBTTagCompound) invTag.tagAt(i);
            byte slot = itemTag.getByte("slot");
            if (slot > 0 && slot <= this.getInventoryStackLimit()) inventory[slot] = ItemStack.loadItemStackFromNBT(itemTag);
        }
        
        this.waterLevel = tag.getInteger("waterLevel");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        NBTTagList invTag = new NBTTagList();
        for (int slot = 0; slot < inventory.length; slot++) {
            if (inventory[slot] == null) continue;
            NBTTagCompound itemTag = inventory[slot].writeToNBT(new NBTTagCompound());
            itemTag.setByte("slot", (byte) slot);
            invTag.appendTag(itemTag);
        }
        
        tag.setTag("inventory", invTag);
        tag.setInteger("waterLevel", this.waterLevel);
    }
}
