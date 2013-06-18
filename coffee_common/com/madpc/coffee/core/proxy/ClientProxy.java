package com.madpc.coffee.core.proxy;

import net.minecraftforge.client.MinecraftForgeClient;

import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.renderer.ItemRendererCoffeeMaker;
import com.madpc.coffee.renderer.RenderCoffeeMaker;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerTileEntities() {
        super.registerTileEntities();
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoffeeMaker.class, new RenderCoffeeMaker());
    }
    
    @Override
    public void registerRenderers() {
        super.registerRenderers();
        
        Reference.coffeeMakerRenderer = RenderingRegistry.getNextAvailableRenderId();
        
        MinecraftForgeClient.registerItemRenderer(Reference.coffeeMaker, new ItemRendererCoffeeMaker());
    }
}
