package com.madpc.coffee.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    
    public static Block coffeeMaker;
    
    public static void init() {
        coffeeMaker = new BlockCoffeeMaker(200).setCreativeTab(CreativeTabs.tabBlock);
        
        GameRegistry.registerBlock(coffeeMaker, "coffeeMaker");
    }
}
