package com.madpc.coffee.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.madpc.coffee.block.ModBlocks;

public class WorldGenCoffea extends WorldGenerator {
    
    public WorldGenCoffea(boolean par1) {
        super(par1);
    }
    
    @Override
    public boolean generate(World worldObj, Random rand, int x, int y, int z) {
        Block block = null;
        do {
            block = Block.blocksList[worldObj.getBlockId(x, y, z)];
            if (block != null && !block.isLeaves(worldObj, x, y, z)) break;
            y--;
        } while (y > 0);
        
        int idHere = worldObj.getBlockId(x, y, z);
        
        if (idHere == Block.dirt.blockID || idHere == Block.grass.blockID) {
            ++y;
            this.setBlockAndMetadata(worldObj, x, y, z, Block.wood.blockID, 3);
            
            for (int j1 = y; j1 <= y + 2; ++j1) {
                int k1 = j1 - y;
                int l1 = 2 - k1;
                
                for (int i2 = x - l1; i2 <= x + l1; ++i2) {
                    int j2 = i2 - x;
                    
                    for (int k2 = z - l1; k2 <= z + l1; ++k2) {
                        int l2 = k2 - z;
                        
                        block = Block.blocksList[worldObj.getBlockId(i2, j1, k2)];
                        
                        if ((Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0) && (block == null || block.canBeReplacedByLeaves(worldObj, i2, j1, k2))) this.setBlockAndMetadata(worldObj, i2, j1, k2, ModBlocks.coffeaLeaves.blockID, 0);
                    }
                }
            }
        }
        
        return true;
    }
}
