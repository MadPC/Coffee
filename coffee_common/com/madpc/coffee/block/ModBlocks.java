package com.madpc.coffee.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    
    public static Block coffeeMaker;
    public static Block coffeaLeaves;
    public static Block coffeaSapling;
    
    public static void init() {
        ModBlocks.coffeeMaker = new BlockCoffeeMaker(Coffee.config.getBlock("CoffeeMaker", 1200).getInt()).setHardness(1.0F).setStepSound(Block.soundStoneFootstep);
        ModBlocks.coffeaLeaves = new BlockCoffeaLeaves(Coffee.config.getBlock("CoffeaLeaves", 1201).getInt()).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
        ModBlocks.coffeaSapling = new BlockCoffeaSapling(Coffee.config.getBlock("CoffeaSapling", 1202).getInt()).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
        
        GameRegistry.registerBlock(ModBlocks.coffeeMaker, Strings.COFFEE_MAKER_NAME);
        GameRegistry.registerBlock(ModBlocks.coffeaLeaves, Strings.COFFEA_LEAVES_NAME);
        GameRegistry.registerBlock(ModBlocks.coffeaSapling, Strings.COFFEA_SAPLING_NAME);
        
        // Create item for sapling
        Item.itemsList[ModBlocks.coffeaSapling.blockID] = new ItemMultiTextureTile(ModBlocks.coffeaSapling.blockID - 256, ModBlocks.coffeaSapling, new String[] {
            "Coffea"
        }).setUnlocalizedName(Strings.COFFEA_SAPLING_NAME);
    }
}
