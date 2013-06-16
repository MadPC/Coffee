package com.madpc.coffee.world;

import java.util.Random;

import net.minecraft.world.World;
//TODO Remove/Fix Commented out imports to work  
//import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
//import net.minecraft.world.gen.feature.WorldGenVines;
//import net.minecraft.world.gen.feature.WorldGenHugeTrees;
//import net.minecraft.world.gen.feature.WorldGenShrub;
//import net.minecraft.world.gen.feature.WorldGenTallGrass;
//import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCoffee extends BiomeGenBase {

    public BiomeGenCoffee(int par1) {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.setBiomeName("Coffee, Not Tea");
    }
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        //TODO Fix this to use Coffee tree
        return null;
        //return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : (par1Random.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (par1Random.nextInt(3) == 0 ? new WorldGenHugeTrees(false, 10 + par1Random.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true))));
    }
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        //TODO Fix to use what is needed
        return null;
        //return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        //TODO Add proper stuff here. This used to be vines for jungle
    }

}
