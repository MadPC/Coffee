package com.madpc.coffee.coffeemaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

import com.madpc.coffee.CoffeeHelper;
import com.madpc.coffee.item.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCoffeeMaker extends Container {
    
    public EntityPlayer player;
    public TileEntityCoffeeMaker inventory;
    public int lastProgress;
    public int lastWaterLevel;
    
    public ContainerCoffeeMaker(InventoryPlayer playerInv, TileEntityCoffeeMaker entity) {
        this.inventory = entity;
        
        this.addSlotToContainer(new Slot(entity, 0, 73, 24));
        this.addSlotToContainer(new Slot(entity, 1, 73, 44));
        this.addSlotToContainer(new Slot(entity, 2, 15, 35));
        this.addSlotToContainer(new Slot(entity, 3, 35, 25));
        this.addSlotToContainer(new Slot(entity, 4, 53, 25));
        this.addSlotToContainer(new Slot(entity, 5, 35, 43));
        this.addSlotToContainer(new Slot(entity, 6, 53, 43));
        this.addSlotToContainer(new Slot(entity, 7, 141, 34));
        
        // Inventory
        for (int y = 0; y < 3; ++y)
            for (int x = 0; x < 9; ++x)
                this.addSlotToContainer(new Slot(playerInv, y * 9 + x + 9, 8 + x * 18, 84 + y * 18));
        
        // Quick bar
        for (int slot = 0; slot < 9; ++slot)
            this.addSlotToContainer(new Slot(playerInv, slot, 8 + slot * 18, 142));
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.inventory.isUseableByPlayer(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int source) {
        ItemStack r = null;
        Slot slot = (Slot) this.inventorySlots.get(source);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            r = slotStack.copy();
            
            if (source < 8) {
                if (!this.mergeItemStack(slotStack, 34, 43, false)) return null;
                if (!this.mergeItemStack(slotStack, 8, 33, false)) return null;
                slot.onSlotChange(slotStack, r);
            } else if (slotStack.itemID == ModItems.coffeeBeans.itemID) {
                if (!this.mergeItemStack(slotStack, 0, 7, false)) return null;
            } else if (slotStack.itemID == ModItems.coffeeFilter.itemID) {
                if (!this.mergeItemStack(slotStack, 1, 7, false)) return null;
            } else if (slotStack.itemID == Item.bucketWater.itemID) {
                if (!this.mergeItemStack(slotStack, 2, 7, false)) return null;
            } else if (CoffeeHelper.isSpice(slotStack.itemID)) {
                if (!this.mergeItemStack(slotStack, 3, 7, false)) if (!this.mergeItemStack(slotStack, 4, 7, false)) if (!this.mergeItemStack(slotStack, 5, 7, false)) if (!this.mergeItemStack(slotStack, 6, 7, false)) return null;
            } else return null;
            
            if (slotStack.stackSize == 0) slot.putStack((ItemStack) null);
            else slot.onSlotChanged();
            
            slot.onPickupFromSlot(player, slotStack);
        }
        
        return r;
    }
    
    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, this.inventory.progress);
        crafter.sendProgressBarUpdate(this, 1, this.inventory.getAmount(this.inventory.water.getLiquid()));
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.lastProgress != this.inventory.progress) icrafting.sendProgressBarUpdate(this, 0, this.inventory.progress);
            icrafting.sendProgressBarUpdate(this, 1, this.inventory.getAmount(this.inventory.water.getLiquid()));
        }
        
        this.lastProgress = this.inventory.progress;
        this.lastWaterLevel = this.inventory.getAmount(this.inventory.water.getLiquid());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int property, int value) {
        switch (property) {
            case 0:
                this.inventory.progress = value;
                break;
            case 1:
                this.inventory.water.setLiquid(new LiquidStack(Item.bucketWater, value));
                break;
        }
    }
}
