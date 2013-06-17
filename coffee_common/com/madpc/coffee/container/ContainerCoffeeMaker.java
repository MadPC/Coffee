package com.madpc.coffee.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

public class ContainerCoffeeMaker extends Container {

    public ContainerCoffeeMaker(EntityPlayer player, TileEntityCoffeeMaker entity) {
        super();
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
    
}
