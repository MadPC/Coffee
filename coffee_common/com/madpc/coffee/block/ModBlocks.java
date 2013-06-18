package com.madpc.coffee.block;

import net.minecraft.block.Block;

import com.madpc.coffee.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    
    public static Block coffeeMaker;
    public static Block coffeeLeaves;
    
    public static void init() {
        coffeeMaker = new BlockCoffeeMaker(200);
        coffeeLeaves = new BlockCoffeeLeaves(201);
        
        GameRegistry.registerBlock(coffeeMaker, Strings.COFFEE_MAKER_NAME);
        GameRegistry.registerBlock(coffeeLeaves, Strings.COFFEE_LEAVES_NAME);
    }
}
