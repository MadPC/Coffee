package com.madpc.coffee.coffeemaker;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.madpc.coffee.lib.Textures;

public class TileRendererCoffeeMaker extends TileEntitySpecialRenderer {
    public ModelCoffeeMaker model;
    
    public TileRendererCoffeeMaker() {
        this.model = new ModelCoffeeMaker();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float rot) {
        this.render((TileEntityCoffeeMaker) tileEntity, x, y, z, rot);
    }
    
    private void render(TileEntityCoffeeMaker tile, double x, double y, double z, float rot) {
        
        int dir = 0;
        if (tile.worldObj != null) dir = tile.getBlockMetadata();
        
        int rotation = 0;
        if (dir == 2) rotation = 180;
        else if (dir == 3) rotation = 0;
        else if (dir == 4) rotation = 90;
        else if (dir == 5) rotation = -90;
        
        this.bindTextureByName(Textures.MODEL_COFFEEMAKER);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.0F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        this.model.renderAll();
        GL11.glPopMatrix();
    }
}
