package com.madpc.coffee.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCoffea extends WorldGenerator {
    
    public WorldGenCoffea() {}
    
    public WorldGenCoffea(boolean par1) {
        super(par1);
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        return false;
    }
    
    
    
}
