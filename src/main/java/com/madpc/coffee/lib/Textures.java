package com.madpc.coffee.lib;


import net.minecraft.util.ResourceLocation;

import com.madpc.coffee.helper.ResourceLocationHelper;

public class Textures
{

	public static final String				GUI_SHEET_LOCATION	= "textures/gui/";
	public static final String 				BLOCK_SHEET_LOCATION = "textures/blocks/";

	public static final ResourceLocation	GUI_COFFEEMAKER		= ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "coffeeMaker.png");
	public static final ResourceLocation	MODEL_COFFEEMAKER	= ResourceLocationHelper.getResourceLocation(BLOCK_SHEET_LOCATION + "coffeeMaker.png");
}
