package com.madpc.coffee.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.lib.Strings;
import com.madpc.coffee.world.WorldGenCoffea;

public class BlockCoffeaSapling extends BlockSapling {
    
    protected BlockCoffeaSapling(int par1) {
        super(par1);
        this.setUnlocalizedName(Strings.COFFEA_SAPLING_NAME);
        this.setCreativeTab(Coffee.tabsCoffee);
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }
    
    @Override
    public void growTree(World worldObj, int x, int y, int z, Random rand) {
        if (!worldObj.isRemote) {
            if (!TerrainGen.saplingGrowTree(worldObj, rand, x, y, z)) return;
            worldObj.setBlock(x, y, z, 0, 0, 4);
            if (!new WorldGenCoffea(true).generate(worldObj, rand, x, y - 1, z)) worldObj.setBlock(x, y, z, this.blockID, 0, 4);
        }
    }
    
    @Override
    public Icon getIcon(int par1, int par2) {
        return this.blockIcon;
    }
    
    @Override
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName2());
    }
}
