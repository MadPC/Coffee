package com.madpc.coffee.coffeemaker;

import net.minecraft.block.Block;
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
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

import com.madpc.coffee.CoffeeHelper;
import com.madpc.coffee.item.ModItems;

public class TileEntityCoffeeMaker extends TileEntity implements
        ISidedInventory, ITankContainer {
    
    /**
     * 0 = Beans, 1 = Filter, 2 = Water, 3 - 6 = spices, 7 = output
     */
    public ItemStack[] inventory = new ItemStack[8];
    public int progress = 0;
    public int maxProgress = 200;
    public int maxWaterLevel = 4000;
    public LiquidTank water = new LiquidTank(new LiquidStack(Item.bucketWater, 0), this.maxWaterLevel);
    public String customName;
    
    public final int[] slotsDefault = {
            0, 1, 2, 3, 4, 5, 6
    };
    public final int[] slotsBottom = {
        7
    };
    
    public String getCustomName() {
        return this.customName;
    }
    
    public void setCustomName(String s) {
        this.customName = s;
    }
    
    public int getProgressScaled(int i) {
        return this.progress * i / this.maxProgress;
    }
    
    public int getWaterScaled(int i) {
        return this.getAmount(this.water.getLiquid()) * i / 4000;
    }
    
    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }
    
    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.inventory[slot] == null) return null;
        
        ItemStack r;
        if (amount > this.inventory[slot].stackSize) {
            r = this.inventory[slot];
            this.inventory[slot] = null;
            return r;
        }
        
        r = this.inventory[slot].splitStack(amount);
        if (this.inventory[slot].stackSize == 0) this.inventory[slot] = null;
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
        this.inventory[slot] = stack;
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
    
    public int getAmount(LiquidStack liquid) {
        return liquid == null ? 0 : liquid.amount;
    }
    
    public boolean canMakeCoffee() {
        if (this.inventory[0] == null) return false;
        if (this.inventory[1] == null) return false;
        if (this.getAmount(this.water.getLiquid()) < 1000) return false;
        if (this.inventory[7] == null) return true;
        ItemStack coffee = CoffeeHelper.getResult(this.inventory[3], this.inventory[4], this.inventory[5], this.inventory[6]);
        if (!this.inventory[7].isItemEqual(coffee)) return false;
        int result = this.inventory[7].stackSize + coffee.stackSize;
        return result <= this.getInventoryStackLimit() && result <= coffee.getMaxStackSize();
    }
    
    @Override
    public void updateEntity() {
        boolean invChanged = false;
        if (this.isInvalid()) System.out.println("Invalid Tile Entity");
        
        if (this.worldObj.isRemote) return;
        if (this.getAmount(this.water.getLiquid()) + 1000 <= this.maxWaterLevel && this.inventory[2] != null && this.inventory[2].itemID == Item.bucketWater.itemID) {
            this.water.fill(new LiquidStack(Item.bucketWater, 1000), true);
            this.inventory[2] = new ItemStack(Item.bucketEmpty.itemID, 1, 0);
            invChanged = true;
        }
        
        if (this.canMakeCoffee()) if (++this.progress >= this.maxProgress) {
            this.progress = 0;
            
            // Brew the coffee
            ItemStack result = CoffeeHelper.getResult(this.inventory[3], this.inventory[4], this.inventory[5], this.inventory[6]);
            if (this.inventory[7] == null) this.inventory[7] = result.copy();
            else this.inventory[7].stackSize += result.stackSize;
            --this.inventory[0].stackSize;
            if (this.inventory[0].stackSize <= 0) this.inventory[0] = null;
            this.inventory[1].attemptDamageItem(1, this.worldObj.rand);
            this.water.drain(1000, true);
            
            invChanged = true;
        }
        
        if (invChanged) this.onInventoryChanged();
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
        if (side == 0) return this.slotsBottom;
        return this.slotsDefault;
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
            if (slot <= this.getInventoryStackLimit()) this.inventory[slot] = ItemStack.loadItemStackFromNBT(itemTag);
        }
        
        this.water.fill(new LiquidStack(Item.bucketWater, tag.getInteger("waterLevel")), true);
        this.progress = tag.getInteger("progress");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        NBTTagList invTag = new NBTTagList();
        for (int slot = 0; slot < this.inventory.length; slot++) {
            if (this.inventory[slot] == null) continue;
            NBTTagCompound itemTag = this.inventory[slot].writeToNBT(new NBTTagCompound());
            itemTag.setByte("slot", (byte) slot);
            invTag.appendTag(itemTag);
        }
        
        tag.setTag("inventory", invTag);
        tag.setInteger("waterLevel", this.getAmount(this.water.getLiquid()));
        tag.setInteger("progress", this.progress);
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        this.readFromNBT(packet.customParam1);
    }
    
    @Override
    public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
        return this.fill(0, resource, doFill);
    }
    
    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
        if (tankIndex != 0 || resource == null || !resource.isLiquidEqual(new ItemStack(Block.waterStill))) return 0;
        return this.water.fill(new LiquidStack(Item.bucketWater, this.getAmount(resource)), doFill);
    }
    
    @Override
    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }
    
    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
        return null;
    }
    
    @Override
    public ILiquidTank[] getTanks(ForgeDirection direction) {
        ForgeDirection dir = ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
        switch (direction) {
            case NORTH:
                return dir == ForgeDirection.SOUTH ? new ILiquidTank[] {
                    this.water
                } : null;
            case SOUTH:
                return dir == ForgeDirection.NORTH ? new ILiquidTank[] {
                    this.water
                } : null;
            case EAST:
                return dir == ForgeDirection.EAST ? new ILiquidTank[] {
                    this.water
                } : null;
            case WEST:
                return dir == ForgeDirection.WEST ? new ILiquidTank[] {
                    this.water
                } : null;
            default:
                return new ILiquidTank[] {
                    this.water
                };
        }
    }
    
    @Override
    public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
        return null;
    }
}
