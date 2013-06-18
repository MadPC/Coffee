package com.madpc.coffee.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.madpc.coffee.model.ModelCoffeeMaker;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

public class RenderCoffeeMaker extends TileEntitySpecialRenderer {
    
    private ModelCoffeeMaker model = new ModelCoffeeMaker();
    
    public void renderTileEntityAt(TileEntityCoffeeMaker tileEntity, double x, double y, double z, float rot) {
        int i = 0;
        
        if (tileEntity.func_70309_m()) {
            i = tileEntity.getBlockMetadata();
        }
        
        this.bindTextureByName("/mods/coffee/textures/blocks/coffeeMaker.png");
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short short1 = 0;
        
        if (i == 2) {
            short1 = 180;
        }
        
        if (i == 3) {
            short1 = 0;
        }
        
        if (i == 4) {
            short1 = 90;
        }
        
        if (i == 5) {
            short1 = -90;
        }
        
        GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        this.model.render(0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double x, double y, double z, float rot) {
        this.renderTileEntityAt((TileEntityCoffeeMaker) par1TileEntity, x, y, z, rot);
    }
}
