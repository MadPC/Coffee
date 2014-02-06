package com.madpc.coffee.core.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.madpc.coffee.block.ModBlocks;
import com.madpc.coffee.renderer.ItemRendererCustom;
import com.madpc.coffee.renderer.TileRendererCoffeeMaker;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerTileEntities() {
        super.registerTileEntities();
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoffeeMaker.class, new TileRendererCoffeeMaker());
    }
    
    @Override
    public void registerRenderers() {
        super.registerRenderers();
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.coffeeMaker), new ItemRendererCustom(TileEntityCoffeeMaker.class));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCoffeeMaker.class, new TileRendererCoffeeMaker());
    }
}
