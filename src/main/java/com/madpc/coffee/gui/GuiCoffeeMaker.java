package com.madpc.coffee.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.madpc.coffee.container.ContainerCoffeeMaker;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

public class GuiCoffeeMaker extends GuiContainer {
    
    public TileEntityCoffeeMaker inventory;
    
    public GuiCoffeeMaker(EntityPlayer player, TileEntityCoffeeMaker entity) {
        super(new ContainerCoffeeMaker(player.inventory, entity));
        this.inventory = entity;
    }
    
    @Override
    protected void func_146979_b(int par1, int par2) {
        String s = StatCollector.translateToLocal(this.inventory.func_145825_b());
        this.field_146289_q.drawString(s, this.field_146999_f / 2
                - this.field_146289_q.getStringWidth(s) / 2, 6, 4210752);
        this.field_146289_q.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }
    
    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //bad fix here.
        this.field_146297_k.getTextureManager().bindTexture(null);
        int x = (this.field_146294_l - this.field_146999_f) / 2;
        int y = (this.field_146295_m - this.field_147000_g) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
        
        int progress = this.inventory.getProgressScaled(24);
        if (progress > 24) progress = 24;
        this.drawTexturedModalRect(x + 104, y + 33, 176, 14, progress + 1, 17);
        
        // Water level at 176, 31
        int waterLevel = this.inventory.getWaterScaled(16);
        if (waterLevel > 16) waterLevel = 16;
        this.drawTexturedModalRect(x + 15, y + 35 + (16 - waterLevel), 176, 31, 16, waterLevel);
    }
    
}
