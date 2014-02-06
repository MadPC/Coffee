package com.madpc.coffee.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.lib.Strings;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;

public class BlockCoffeeMaker extends BlockContainer {
    
    public BlockCoffeeMaker() {
        super(Material.rock);
        this.setBlockName(Strings.COFFEE_MAKER_NAME);
        this.setCreativeTab(Coffee.tabsCoffee);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int id) {
        return new TileEntityCoffeeMaker();
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public int getRenderType() {
        return -2;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block id, int meta) {
        //dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    @Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) return false;
        if (!worldObj.isRemote) {
            TileEntityCoffeeMaker entity = (TileEntityCoffeeMaker) worldObj.getTileEntity(x, y, z);
            
            System.out.println("Opening GUI");
            if (entity != null) player.openGui(Coffee.instance, Reference.coffeeMaker, worldObj, x, y, z);
        }
        
        return true;
    }
    
    public void func_94581_a(IIconRegister register) {
        register.registerIcon(Reference.MOD_ID + ":"
                + this.getUnlocalizedName());
    }
}
