package com.madpc.coffee.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//TODO Remove/Fix Commented out imports to work  

public class BiomeGenCoffee extends BiomeGenBase {
    
    public BiomeGenCoffee(int par1) {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 50;
        this.theBiomeDecorator.grassPerChunk = 25;
        this.theBiomeDecorator.flowersPerChunk = 4;
        
        this.waterColorMultiplier = 14745518;
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.setBiomeName("Coffee, Not Tea");
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random rand) {
        return (WorldGenerator)(rand.nextInt(10) == 0 ? this.worldGeneratorBigTree : (rand.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (rand.nextInt(3) == 0 ? new WorldGenHugeTrees(false, 10 + rand.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true))));
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        //TODO Add proper stuff here. This used to be vines for jungle
        WorldGenVines worldgenvines = new WorldGenVines();
        
        for (int k = 0; k < 50; ++k) {
            int l = par3 + par2Random.nextInt(16) + 8;
            byte b0 = 64;
            int i1 = par4 + par2Random.nextInt(16) + 8;
            worldgenvines.generate(par1World, par2Random, l, b0, i1);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getModdedBiomeGrassColor(int original) {
        return BiomeGenBase.swampland.getBiomeGrassColor();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getModdedBiomeFoliageColor(int original) {
        return BiomeGenBase.swampland.getBiomeFoliageColor();
    }
}
