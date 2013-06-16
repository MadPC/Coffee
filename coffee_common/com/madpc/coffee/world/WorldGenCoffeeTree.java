package com.madpc.coffee.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCoffeeTree extends WorldGenerator {
    
    public WorldGenCoffeeTree() {
        // TODO Auto-generated constructor stub
    }
    
    public WorldGenCoffeeTree(boolean par1) {
        super(par1);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean generate(World worldObj, Random rand, int x, int y, int z) {
        int l;
        
        for (l = rand.nextInt(4) + 5; worldObj.getBlockMaterial(x, y - 1, z) == Material.water; --y) {
            ;
        }
        
        boolean flag = true;
        
        if (y >= 1 && y + l + 1 <= 128) {
            int i1;
            int j1;
            int k1;
            int l1;
            
            for (i1 = y; i1 <= y + 1 + l; ++i1) {
                byte b0 = 1;
                
                if (i1 == y) {
                    b0 = 0;
                }
                
                if (i1 >= y + 1 + l - 2) {
                    b0 = 3;
                }
                
                for (j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 128) {
                            l1 = worldObj.getBlockId(j1, i1, k1);
                            
                            if (l1 != 0 && (Block.blocksList[l1] != null && !Block.blocksList[l1].isLeaves(worldObj, j1, i1, k1))) {
                                if (l1 != Block.waterStill.blockID && l1 != Block.waterMoving.blockID) {
                                    flag = false;
                                } else if (i1 > y) {
                                    flag = false;
                                }
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }
            
            if (!flag) {
                return false;
            } else {
                i1 = worldObj.getBlockId(x, y - 1, z);
                
                if ((i1 == Block.grass.blockID || i1 == Block.dirt.blockID) && y < 128 - l - 1) {
                    this.setBlock(worldObj, x, y - 1, z, Block.dirt.blockID);
                    int i2;
                    int j2;
                    
                    for (j2 = y - 3 + l; j2 <= y + l; ++j2) {
                        j1 = j2 - (y + l);
                        k1 = 2 - j1 / 2;
                        
                        for (l1 = x - k1; l1 <= x + k1; ++l1) {
                            i2 = l1 - x;
                            
                            for (int k2 = z - k1; k2 <= z + k1; ++k2) {
                                int l2 = k2 - z;
                                
                                Block block = Block.blocksList[worldObj.getBlockId(l1, j2, k2)];
                                
                                if ((Math.abs(i2) != k1 || Math.abs(l2) != k1 || rand.nextInt(2) != 0 && j1 != 0) && (block == null || block.canBeReplacedByLeaves(worldObj, l1, j2, k2))) {
                                    this.setBlock(worldObj, l1, j2, k2, Block.leaves.blockID);
                                }
                            }
                        }
                    }
                    
                    for (j2 = 0; j2 < l; ++j2) {
                        j1 = worldObj.getBlockId(x, y + j2, z);
                        
                        Block block = Block.blocksList[j1];
                        
                        if (j1 == 0 || (block != null && block.isLeaves(worldObj, x, y + j2, z)) || j1 == Block.waterMoving.blockID || j1 == Block.waterStill.blockID) {
                            this.setBlock(worldObj, x, y + j2, z, Block.wood.blockID);
                        }
                    }
                    
                    for (j2 = y - 3 + l; j2 <= y + l; ++j2) {
                        j1 = j2 - (y + l);
                        k1 = 2 - j1 / 2;
                        
                        for (l1 = x - k1; l1 <= x + k1; ++l1) {
                            for (i2 = z - k1; i2 <= z + k1; ++i2) {
                                Block block = Block.blocksList[worldObj.getBlockId(l1, j2, i2)];
                                if (block != null && block.isLeaves(worldObj, l1, j2, i2)) {
                                    if (rand.nextInt(4) == 0 && worldObj.getBlockId(l1 - 1, j2, i2) == 0) {
                                        this.generateVines(worldObj, l1 - 1, j2, i2, 8);
                                    }
                                    
                                    if (rand.nextInt(4) == 0 && worldObj.getBlockId(l1 + 1, j2, i2) == 0) {
                                        this.generateVines(worldObj, l1 + 1, j2, i2, 2);
                                    }
                                    
                                    if (rand.nextInt(4) == 0 && worldObj.getBlockId(l1, j2, i2 - 1) == 0) {
                                        this.generateVines(worldObj, l1, j2, i2 - 1, 1);
                                    }
                                    
                                    if (rand.nextInt(4) == 0 && worldObj.getBlockId(l1, j2, i2 + 1) == 0) {
                                        this.generateVines(worldObj, l1, j2, i2 + 1, 4);
                                    }
                                }
                            }
                        }
                    }
                    
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
    
    private void generateVines(World worldObj, int x, int y, int z, int dir) {
        this.setBlockAndMetadata(worldObj, x, y, z, Block.vine.blockID, dir);
        int vines = 4;
        
        while (worldObj.getBlockId(x, y, z) != 0 || vines <= 0) {
            --y;
            this.setBlockAndMetadata(worldObj, x, y, z, Block.vine.blockID, dir);
            --vines;
        }
    }
    
}
