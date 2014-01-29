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
        super(Material.field_151576_e);
        this.func_149663_c(Strings.COFFEE_MAKER_NAME);
        this.func_149647_a(Coffee.tabsCoffee);
    }
    
    @Override
    public TileEntity func_149915_a(World world, int id) {
        return new TileEntityCoffeeMaker();
    }
    
    @Override
    public boolean func_149662_c() {
        return false;
    }
    
    @Override
    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }
    
    @Override
    public boolean func_149686_d() {
        return false;
    }
    
    @Override
    public int func_149645_b() {
        return -2;
    }
    
    @Override
    public void func_149749_a(World world, int x, int y, int z, Block id, int meta) {
        //dropInventory(world, x, y, z);
        super.func_149749_a(world, x, y, z, id, meta);
    }
    
    @Override
    public boolean func_149727_a(World worldObj, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) return false;
        if (!worldObj.isRemote) {
            TileEntityCoffeeMaker entity = (TileEntityCoffeeMaker) worldObj.func_147438_o(x, y, z);
            
            System.out.println("Opening GUI");
            if (entity != null) player.openGui(Coffee.instance, Reference.coffeeMaker, worldObj, x, y, z);
        }
        
        return true;
    }
    
    public void func_94581_a(IIconRegister register) {
        register.registerIcon(Reference.MOD_ID + ":"
                + this.func_149739_a());
    }
}
