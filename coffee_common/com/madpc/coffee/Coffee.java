package com.madpc.coffee;

import net.minecraft.block.Block;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import com.madpc.coffee.block.ModBlocks;
import com.madpc.coffee.item.ModItems;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.potion.PotionCaffeine;
import com.madpc.coffee.tileentity.TileEntityCoffeeMaker;
import com.madpc.coffee.world.BiomeGenCoffee;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Coffee {
    
    public static final Potion caffeine = new PotionCaffeine(21, false, 0x00FF00).setPotionName("Caffeine");
    
    public static BiomeGenBase coffeeBiome;
    
    
    public static Block BlockCoffeaLeaves;
    
    @Instance("Coffee")
    public static Coffee instance;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        ModBlocks.init();
        
        TileEntity.addMapping(TileEntityCoffeeMaker.class, "Coffee Maker");
        
        coffeeBiome = new BiomeGenCoffee(122).func_76733_a(0x8EDA61);
        GameRegistry.addBiome(coffeeBiome);
        
        MinecraftForge.EVENT_BUS.register(new EventHooks());
    }
}
