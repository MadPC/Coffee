package com.madpc.coffee.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.madpc.coffee.Coffee;

public class ItemCoffee extends Item {
    
    public ItemCoffee(int id) {
        super(id);
    }
    
    @Override
    public ItemStack onEaten(ItemStack stack, World worldObj, EntityPlayer player) {
        --stack.stackSize;
        worldObj.playSoundAtEntity(player, "random.burp", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        // Add effects
        int amplifier = 0;
        if (player.isPotionActive(Coffee.caffeine)) amplifier = player.getActivePotionEffect(Coffee.caffeine).getAmplifier() + 1;
        player.addPotionEffect(new PotionEffect(Coffee.caffeine.id, 1000, amplifier)); // Duration will be 6000
        return stack;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.drink;
    }
    
    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return "Coffee";
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }
    
    /*@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        
    }*/
}
