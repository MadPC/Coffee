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
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.inventory.isInvNameLocalized() ? this.inventory.getInvName() : StatCollector.translateToLocal(this.inventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2
                - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/coffee/textures/gui/coffeeMaker.png");
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        
        int progress = this.inventory.getProgressScaled(24);
        this.drawTexturedModalRect(x + 79, y + 34, 176, 14, progress + 1, 16);
    }
    
}
