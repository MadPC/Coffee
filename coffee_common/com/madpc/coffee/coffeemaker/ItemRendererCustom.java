package com.madpc.coffee.coffeemaker;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererCustom implements IItemRenderer {
    
    public Class<? extends TileEntity> tile;
    
    public ItemRendererCustom(Class<? extends TileEntity> tile) {
        this.tile = tile;
    }
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        try {
            TileEntityRenderer.instance.renderTileEntityAt(this.tile.newInstance(), 0.0D, 0.0D, 0.0D, 0.0F);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}