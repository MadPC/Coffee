package com.madpc.coffee.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.madpc.coffee.container.ContainerCoffeeMaker;
import com.madpc.coffee.gui.GuiCoffeeMaker;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {
    
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityCoffeeMaker.class, "tile.coffeeMaker");
    }
    
    public void registerRenderers() {}
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.coffeeMaker) return new ContainerCoffeeMaker(player.inventory, (TileEntityCoffeeMaker) world.func_147438_o(x, y, z));
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.coffeeMaker) return new GuiCoffeeMaker(player, (TileEntityCoffeeMaker) world.func_147438_o(x, y, z));
        return null;
    }
    
}
