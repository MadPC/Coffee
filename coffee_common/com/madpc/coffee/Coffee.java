package com.madpc.coffee;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import com.madpc.coffee.block.ModBlocks;
import com.madpc.coffee.core.proxy.CommonProxy;
import com.madpc.coffee.creativetab.CreativeTabCoffee;
import com.madpc.coffee.item.ModItems;
import com.madpc.coffee.lib.Reference;
import com.madpc.coffee.potion.PotionCaffeine;
import com.madpc.coffee.world.BiomeGenCoffee;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Coffee {
    
    public static final Potion caffeine = new PotionCaffeine(21, false, 0x00FF00).setPotionName("Caffeine");
    public static BiomeGenBase coffeeBiome;
    public static Block BlockCoffeaLeaves;
    
    @Instance(Reference.MOD_ID)
    public static Coffee instance;
    
    @SidedProxy(serverSide = "com.madpc.coffee.core.proxy.CommonProxy", clientSide = "com.madpc.coffee.core.proxy.ClientProxy")
    public static CommonProxy proxy;
    public static CreativeTabs tabsCoffee = new CreativeTabCoffee(CreativeTabs.getNextID(), Reference.MOD_ID);
    public static Configuration config;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        LocalizationHandler.loadLanguages();
        
        Coffee.config = new Configuration(new File(event.getModConfigurationDirectory(), "Coffee.conf"));
        config.load();
        
        try {
            ModItems.init();
            ModBlocks.init();
        } finally {
            if (config.hasChanged()) config.save();
        }
        
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.coffeeBeans, 1), new Object[] {
                "B", 'B', ModItems.coffeaBerry
        });
        
        Coffee.proxy.registerTileEntities();
        Coffee.proxy.registerRenderers();
        
        Coffee.coffeeBiome = new BiomeGenCoffee(122).func_76733_a(0x8EDA61);
        GameRegistry.addBiome(Coffee.coffeeBiome);
        BiomeDictionary.registerBiomeType(Coffee.coffeeBiome, Type.JUNGLE);
        
        MinecraftForge.EVENT_BUS.register(new EventHooks());
    }
    
    @Init
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(Coffee.instance, Coffee.proxy);
    }
}
