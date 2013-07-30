package com.madpc.coffee;

import net.minecraft.creativetab.CreativeTabs;

import com.madpc.coffee.item.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabCoffee extends CreativeTabs {
    
    public CreativeTabCoffee(int par1, String par2Str) {
        
        super(par1, par2Str);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {
        
        return ModItems.coffee.itemID;
    }
    
}
