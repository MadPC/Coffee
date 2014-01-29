package com.madpc.coffee.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
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
    public int progress = 0;
    public int maxProgress = 200;
    public int waterLevel = 0;
    public int maxWaterLevel = 16;
    public String customName;
    
    public final int[] slotsDefault = {
            0, 1, 2, 3, 4, 5, 6
    };
    public final int[] slotsBottom = {
        7
    };
    
    public String getCustomName() {
        return customName;
    }
    
    public void setCustomName(String s) {
        this.customName = s;
    }
    
    public int getProgressScaled(int i) {
        return progress * i / maxProgress;
    }
    
    public int getWaterScaled(int i) {
        return waterLevel * i / maxWaterLevel;
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
        ItemStack r = this.getStackInSlot(slot);
        if (r != null) this.setInventorySlotContents(slot, null);
        return r;
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) stack.stackSize = this.getInventoryStackLimit();
    }
    
    @Override
    public String func_145825_b() {
        return this.func_145818_k_() ? this.getCustomName() : "container.coffeeMaker";
    }
    
    @Override
    public boolean func_145818_k_() {
        return this.customName != null;
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
    
    public boolean canMakeCoffee() {
        if (this.inventory[0] == null) return false;
        if (this.inventory[1] == null) return false;
        if (this.waterLevel <= 0) return false;
        if (this.inventory[7] == null) return true;
        ItemStack coffee = CoffeeHelper.getResult(this.inventory[3], this.inventory[4], this.inventory[5], this.inventory[6]);
        if (!this.inventory[7].isItemEqual(coffee)) return false;
        int result = this.inventory[7].stackSize + coffee.stackSize;
        return result <= this.getInventoryStackLimit()
                && result <= coffee.getMaxStackSize();
    }
    
    @Override
    public void func_145845_h() {
        boolean invChanged = false;
        if (this.func_145837_r()) System.out.println("Invalid Tile Entity");
        
        if (!this.field_145850_b.isRemote) {
            if (this.waterLevel < this.maxWaterLevel && this.inventory[2] != null && this.inventory[2].getItem() == Items.water_bucket) {
                ++this.waterLevel;
                this.inventory[2] = new ItemStack(Items.bucket, 0, 1);
                invChanged = true;
            }
            
            if (this.canMakeCoffee()) {
                if (++this.progress >= this.maxProgress) {
                    this.progress = 0;
                    
                    // Brew the coffee
                    ItemStack result = CoffeeHelper.getResult(this.inventory[3], this.inventory[4], this.inventory[5], this.inventory[6]);
                    if (this.inventory[7] == null) this.inventory[7] = result.copy();
                    else this.inventory[7].stackSize += result.stackSize;
                    --this.inventory[0].stackSize;
                    if (this.inventory[0].stackSize <= 0) this.inventory[0] = null;
                    this.inventory[1].attemptDamageItem(1, field_145850_b.rand);
                    --this.waterLevel;
                    
                    invChanged = true;
                }
            }
        }
        
        if (invChanged) {
            this.onInventoryChanged();
        }
    }
   
    
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == 0) return slotsBottom;
        return slotsDefault;
    }
    
    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int j) {
        return this.isItemValidForSlot(slot, stack);
    }
    
    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int j) {
        return false;
    }
    
    @Override
    public void func_145839_a(NBTTagCompound tag) {
        super.func_145839_a(tag);
        NBTTagList invTag = tag.func_150295_c("inventory", 0);
        
        for (int i = 0; i < invTag.tagCount(); i++) {
            NBTTagCompound itemTag = (NBTTagCompound) invTag.func_150305_b(i);
            byte slot = itemTag.getByte("slot");
            if (slot > 0 && slot <= this.getInventoryStackLimit()) inventory[slot] = ItemStack.loadItemStackFromNBT(itemTag);
        }
        
        this.waterLevel = tag.getInteger("waterLevel");
        //this.maxWaterLevel = tag.getInteger("maxWaterLevel");
        this.progress = tag.getInteger("progress");
        //this.maxProgress = tag.getInteger("maxProgress");
    }
    
    @Override
    public void func_145841_b(NBTTagCompound tag) {
        super.func_145841_b(tag);
        
        NBTTagList invTag = new NBTTagList();
        for (int slot = 0; slot < inventory.length; slot++) {
            if (inventory[slot] == null) continue;
            NBTTagCompound itemTag = inventory[slot].writeToNBT(new NBTTagCompound());
            itemTag.setByte("slot", (byte) slot);
            invTag.appendTag(itemTag);
        }
        
        tag.setTag("inventory", invTag);
        tag.setInteger("waterLevel", this.waterLevel);
        tag.setInteger("maxWaterLevel", this.maxWaterLevel);
        tag.setInteger("progress", this.progress);
        tag.setInteger("maxProgress", this.maxProgress);
    }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		switch (slot) {
            case 0:
                return stack.getItem() == ModItems.coffeeBeans;
            case 1:
                return stack.getItem() == ModItems.coffeeFilter;
            case 2:
                return stack.getItem() == Items.water_bucket;
            case 3:
            case 4:
            case 5:
            case 6:
                return CoffeeHelper.isSpice(stack.getItem());
            default:
                return false;
        }
	}
}
