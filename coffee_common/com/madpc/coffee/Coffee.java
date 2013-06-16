package com.madpc.coffee;

import com.madpc.coffee.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Coffee {
    
    @Instance("Coffee")
    public static Coffee instance;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        
    }
}
