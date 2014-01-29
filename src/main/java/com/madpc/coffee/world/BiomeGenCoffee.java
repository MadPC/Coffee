package com.madpc.coffee.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BiomeGenCoffee extends BiomeGenBase {
	 private WorldGenTaiga2 field_150634_aD;
    
    public BiomeGenCoffee(int par1) {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 50;
        this.theBiomeDecorator.grassPerChunk = 25;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.field_150634_aD = new WorldGenTaiga2(false);
        
        this.waterColorMultiplier = 14745518;
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.setBiomeName("Coffee, Not Tea");
    }
    
    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(3) > 0 ? this.field_150634_aD : super.func_150567_a(p_150567_1_));
    }
    
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new WorldGenTallGrass(Blocks.tallgrass, 1);
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
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
        return BiomeGenBase.swampland.getModdedBiomeGrassColor(original);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getModdedBiomeFoliageColor(int original) {
        return BiomeGenBase.swampland.getModdedBiomeFoliageColor(original);
    }
}
