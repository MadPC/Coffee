package com.madpc.coffee.core.proxy;

import com.madpc.coffee.renderer.RenderCoffeeMaker;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerTileEntities() {
        super.registerTileEntities();
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoffeeMaker.class, new RenderCoffeeMaker());
    }
}
