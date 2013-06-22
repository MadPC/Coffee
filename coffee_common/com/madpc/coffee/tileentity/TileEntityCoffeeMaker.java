package com.madpc.coffee.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
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
    public String getInvName() {
        return this.isInvNameLocalized() ? this.getCustomName() : "container.coffeeMaker";
    }
    
    @Override
    public boolean isInvNameLocalized() {
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
    public void updateEntity() {
        boolean invChanged = false;
        if (this.isInvalid()) System.out.println("Invalid Tile Entity");
        
        if (!this.worldObj.isRemote) {
            if (this.waterLevel < this.maxWaterLevel && this.inventory[2] != null && this.inventory[2].itemID == Item.bucketWater.itemID) {
                ++this.waterLevel;
                this.inventory[2] = new ItemStack(Item.bucketEmpty, 0, 1);
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
                    this.inventory[1].attemptDamageItem(1, worldObj.rand);
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
        if (side == 0) return slotsBottom;
        return slotsDefault;
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
        //this.maxWaterLevel = tag.getInteger("maxWaterLevel");
        this.progress = tag.getInteger("progress");
        //this.maxProgress = tag.getInteger("maxProgress");
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
        tag.setInteger("maxWaterLevel", this.maxWaterLevel);
        tag.setInteger("progress", this.progress);
        tag.setInteger("maxProgress", this.maxProgress);
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        readFromNBT(packet.customParam1);
    }
}
