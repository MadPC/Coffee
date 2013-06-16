package com.madpc.coffee;

import net.minecraft.world.biome.BiomeGenBase;

import com.madpc.coffee.item.ModItems;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.potion.PotionCaffeine;
import com.madpc.coffee.world.BiomeGenCoffee;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Coffee {
    
    public static final PotionCaffeine caffeine = new PotionCaffeine(21, false, 0x00FF00);
    public static BiomeGenBase coffeeBiome;
    
    @Instance("Coffee")
    public static Coffee instance;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        
        coffeeBiome = new BiomeGenCoffee(122).setBiomeName("");
    }
}
