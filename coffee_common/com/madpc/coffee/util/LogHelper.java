package com.madpc.coffee.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.madpc.coffee.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {
    
    public static Logger logger = Logger.getLogger(Reference.MOD_ID);
    
    public static void init() {
        LogHelper.logger.setParent(FMLLog.getLogger());
        
    }
    
    public static void log(Level logLevel, String message) {
        LogHelper.logger.log(logLevel, message);
    }
    
    public static void info(String message) {
        LogHelper.logger.log(Level.INFO, message);
    }
    
    public static void log(Level level, boolean bool) {
        LogHelper.logger.log(level, bool ? "true" : "false");
    }
    
}
