package com.madpc.coffee.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.madpc.coffee.lib.Reference;

public class ItemCustom extends Item {
    public String iconName = "";
    
    public ItemCustom(int par1) {
        super();
    }
    
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
    }
}
