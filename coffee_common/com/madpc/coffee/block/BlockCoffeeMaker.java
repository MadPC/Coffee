package com.madpc.coffee.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;
import com.madpc.coffee.util.LogHelper;

public class BlockCoffeeMaker extends BlockContainer {
    
    public BlockCoffeeMaker(int par1) {
        super(par1, Material.rock);
        setUnlocalizedName("Coffee Pro 1000");
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityCoffeeMaker();
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {
        //dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    @Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) return false;
        if (!worldObj.isRemote) {
            TileEntityCoffeeMaker entity = (TileEntityCoffeeMaker) worldObj.getBlockTileEntity(x, y, z);
            
            System.out.println("Opening GUI");
            if (entity != null) player.openGui(Coffee.instance, Reference.guiCoffeeMakerID, worldObj, x, y, z);
        }
        
        return true;
    }
}
