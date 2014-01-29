package com.madpc.coffee.renderer;

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
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		
	}
}