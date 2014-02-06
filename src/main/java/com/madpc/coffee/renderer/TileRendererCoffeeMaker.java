package com.madpc.coffee.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.madpc.coffee.lib.Textures;
import com.madpc.coffee.model.ModelCoffeeMaker;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

import cpw.mods.fml.client.FMLClientHandler;

public class TileRendererCoffeeMaker extends TileEntitySpecialRenderer {
    public ModelCoffeeMaker model;
    
    public TileRendererCoffeeMaker() {
        model = new ModelCoffeeMaker();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float rot) {
        render((TileEntityCoffeeMaker) tileEntity, x, y, z, rot);
    }
    
    private void render(TileEntityCoffeeMaker tile, double x, double y, double z, float rot) {
        int rotation = 0;
        //if (tile.worldObj != null) rotation = tile.getBlockMetadata();
        
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_COFFEEMAKER);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y + 1, (float) z + 1);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
        model.renderAll();
        GL11.glPopMatrix();
    }
}
