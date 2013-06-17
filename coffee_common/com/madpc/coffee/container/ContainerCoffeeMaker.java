package com.madpc.coffee.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCoffeeMaker extends Container {
    
    public EntityPlayer player;
    public TileEntityCoffeeMaker inventory;
    
    public ContainerCoffeeMaker(InventoryPlayer playerInv, TileEntityCoffeeMaker entity) {
        this.inventory = entity;
        
        this.addSlotToContainer(new Slot(entity, 0, 44, 17));
        this.addSlotToContainer(new Slot(entity, 1, 80, 57));
        this.addSlotToContainer(new Slot(entity, 2, 8, 44));
        this.addSlotToContainer(new Slot(entity, 3, 35, 35));
        this.addSlotToContainer(new Slot(entity, 4, 53, 35));
        this.addSlotToContainer(new Slot(entity, 5, 35, 53));
        this.addSlotToContainer(new Slot(entity, 6, 53, 53));
        this.addSlotToContainer(new Slot(entity, 7, 125, 33));
        
        // Inventory
        for (int y = 0; y < 3; ++y)
            for (int x = 0; x < 9; ++x)
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
        
        // Quick bar
        for (int slot = 0; slot < 9; ++slot)
            this.addSlotToContainer(new Slot(playerInv, slot, 8 + slot * 18, 142));
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.inventory.isUseableByPlayer(player);
    }
    
    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        
    }
    
    public void detectAndSendChanges() {
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int source) {
        ItemStack r = null;
        Slot slot = (Slot) this.inventorySlots.get(source);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            r = slotStack.copy();
            
            if (source < 8)
                if (!this.mergeItemStack(slotStack, 8, inventorySlots.size(), false))
                    return null;
            
            slot.onPickupFromSlot(player, slotStack);
        }
        
        return r;
    }
    
}
