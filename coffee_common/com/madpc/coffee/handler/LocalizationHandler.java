package com.madpc.coffee.handler;

import com.madpc.coffee.lib.Localizations;
import com.madpc.coffee.util.LocalizationHelper;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler {
    
    public static void loadLanguages() {
        for (String localizationFile : Localizations.localeFiles) {
            LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
        }
    }
    
}
