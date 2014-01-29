package com.madpc.coffee.item;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.lib.Strings;

public class ItemCoffeeBeans extends ItemCustom {

    public ItemCoffeeBeans(int id) {
        super(id);
        this.setCreativeTab(Coffee.tabsCoffee);
        this.setUnlocalizedName(Strings.COFFEE_BEAN_NAME);
        
    }

}
