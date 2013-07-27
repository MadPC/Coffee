package com.madpc.coffee.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.item.ModItems;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.lib.Strings;

import cpw.mods.fml.client.FMLClientHandler;

public class BlockCoffeaLeaves extends BlockLeaves implements IShearable {
    public Icon fastIcon;
    public Icon fancyIcon;
    
    public BlockCoffeaLeaves(int par1) {
        super(par1);
        this.setTickRandomly(true);
        this.setCreativeTab(Coffee.tabsCoffee);
        this.setUnlocalizedName(Strings.COFFEE_LEAVES_NAME);
    }
    
    @Override
    public void registerIcons(IconRegister register) {
        this.fastIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName2() + "_opaque");
        this.fancyIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName2());
    }
    
    @Override
    public Icon getIcon(int par1, int par2) {
        if (this.isFancy()) return this.fancyIcon;
        else return this.fastIcon;
    }
    
    @Override
    public void getSubBlocks(int id, CreativeTabs tab, List blocksList) {
        blocksList.add(new ItemStack(id, 1, 0));
    }
    
    @Override
    public boolean isOpaqueCube() {
        return !this.isFancy();
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        int i1 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return !this.isFancy() && i1 == this.blockID ? false : par5 == 0 && this.minY > 0.0D ? true : par5 == 1 && this.maxY < 1.0D ? true : par5 == 2 && this.minZ > 0.0D ? true : par5 == 3 && this.maxZ < 1.0D ? true : par5 == 4 && this.minX > 0.0D ? true : par5 == 5 && this.maxX < 1.0D ? true : !par1IBlockAccess.isBlockOpaqueCube(par2, par3, par4);
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return ModItems.coffeaBerry.itemID;
    }
    
    public boolean isFancy() {
        return FMLClientHandler.instance().getClient().gameSettings.fancyGraphics;
    }
}