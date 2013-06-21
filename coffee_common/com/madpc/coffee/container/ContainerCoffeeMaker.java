package com.madpc.coffee.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.madpc.coffee.item.ModItems;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

public class ContainerCoffeeMaker extends Container {
    
    public EntityPlayer player;
    public TileEntityCoffeeMaker inventory;
    
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
        Slot slot = (Slot) this.inventorySlots.get(source);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            
            if (source < 8) {
                if (!this.mergeItemStack(slotStack, 8, inventorySlots.size(), false)) return null;
            } else {
                if (slotStack.itemID == ModItems.coffeeBeans.itemID) {
                    if (!this.mergeItemStack(slotStack, 0, 7, false)) return null;
                } else if (slotStack.itemID == ModItems.coffeeFilter.itemID) {
                    if (!this.mergeItemStack(slotStack, 1, 7, false)) return null;
                } else if (slotStack.itemID == Item.bucketWater.itemID) {
                    if (!this.mergeItemStack(slotStack, 2, 7, false)) return null;
                }
                
                if (!this.mergeItemStack(slotStack, 3, 7, false)) if (!this.mergeItemStack(slotStack, 4, 7, false)) if (!this.mergeItemStack(slotStack, 5, 7, false)) if (!this.mergeItemStack(slotStack, 6, 7, false)) return null;
            }
            
            if (slotStack.stackSize == 0) slot.putStack((ItemStack) null);
            else slot.onSlotChanged();
            
        }
        
        // I think this is wrong, but seems to make it work...
        return null;
    }
    
}
