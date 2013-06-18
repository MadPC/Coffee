// Date: 17-06-2013 22:56:48
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package com.madpc.coffee.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoffeeMaker extends ModelBase {
    //fields
    ModelRenderer Base;
    ModelRenderer Back;
    ModelRenderer Front_1;
    ModelRenderer Front_2;
    ModelRenderer Front_3;
    ModelRenderer Coffee_hole;
    
    public ModelCoffeeMaker() {
        textureWidth = 256;
        textureHeight = 32;
        
        Base = new ModelRenderer(this, 125, 0);
        Base.addBox(0F, 0F, 0F, 16, 2, 16);
        Base.setRotationPoint(0F, 14F, 0F);
        Base.setTextureSize(256, 32);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Back = new ModelRenderer(this, 66, 0);
        Back.addBox(0F, 0F, 0F, 16, 15, 8);
        Back.setRotationPoint(0F, 0F, 8F);
        Back.setTextureSize(256, 32);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
        Front_1 = new ModelRenderer(this, 16, 23);
        Front_1.addBox(0F, 0F, 0F, 16, 5, 2);
        Front_1.setRotationPoint(0F, 0F, 6F);
        Front_1.setTextureSize(256, 32);
        Front_1.mirror = true;
        setRotation(Front_1, 0F, 0F, 0F);
        Front_2 = new ModelRenderer(this, 24, 10);
        Front_2.addBox(0F, 0F, 0F, 16, 4, 1);
        Front_2.setRotationPoint(0F, 1F, 5F);
        Front_2.setTextureSize(256, 32);
        Front_2.mirror = true;
        setRotation(Front_2, 0F, 0F, 0F);
        Front_3 = new ModelRenderer(this, 0, 0);
        Front_3.addBox(0F, 0F, 0F, 16, 3, 2);
        Front_3.setRotationPoint(0F, 2F, 3F);
        Front_3.setTextureSize(256, 32);
        Front_3.mirror = true;
        setRotation(Front_3, 0F, 0F, 0F);
        Coffee_hole = new ModelRenderer(this, 4, 10);
        Coffee_hole.addBox(0F, 0F, 0F, 3, 1, 3);
        Coffee_hole.setRotationPoint(6F, 5F, 5F);
        Coffee_hole.setTextureSize(256, 32);
        Coffee_hole.mirror = true;
        setRotation(Coffee_hole, 0F, 0F, 0F);
    }
    
    public void render(float f5) {
        Base.render(f5);
        Back.render(f5);
        Front_1.render(f5);
        Front_2.render(f5);
        Front_3.render(f5);
        Coffee_hole.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
