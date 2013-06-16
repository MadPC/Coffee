package com.madpc.coffee.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCoffee extends ItemFood {

    public ItemCoffee(int id) {
        super(id, 1, 0.0F, false);
    }
    
    @Override
    public ItemStack onEaten(ItemStack stack, World worldObj, EntityPlayer player)
    {
        --stack.stackSize;
        player.getFoodStats().addStats(this);
        worldObj.playSoundAtEntity(player, "random.burp", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        // Add effects
        player.addPotionEffect(new PotionEffect(1, 200, 0));
        return stack;
    }
}
