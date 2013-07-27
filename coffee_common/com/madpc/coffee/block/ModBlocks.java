package com.madpc.coffee.block;

import net.minecraft.block.Block;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    
    public static Block coffeeMaker;
    public static Block coffeaLeaves;
    
    public static void init() {
        ModBlocks.coffeeMaker = new BlockCoffeeMaker(Coffee.config.getBlock("CoffeeMaker", 1200).getInt()).setHardness(1.0F).setStepSound(Block.soundStoneFootstep);
        ModBlocks.coffeaLeaves = new BlockCoffeaLeaves(Coffee.config.getBlock("CoffeaLeaves", 1201).getInt()).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
        coffeaLeaves.blockParticleGravity = -1.0F;
        
        GameRegistry.registerBlock(ModBlocks.coffeeMaker, Strings.COFFEE_MAKER_NAME);
        GameRegistry.registerBlock(ModBlocks.coffeaLeaves, Strings.COFFEE_LEAVES_NAME);
    }
}
