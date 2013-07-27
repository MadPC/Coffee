package com.madpc.coffee.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.madpc.coffee.Coffee;
import com.madpc.coffee.CoffeeHelper;
import com.madpc.coffee.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoffee extends ItemCustom {
    
    public ItemCoffee(int id) {
        super(id);
        this.setCreativeTab(Coffee.tabsCoffee);
        this.setUnlocalizedName(Strings.COFFEE_NAME);
    }
    
    @Override
    public ItemStack onEaten(ItemStack stack, World worldObj, EntityPlayer player) {
        --stack.stackSize;
        worldObj.playSoundAtEntity(player, "random.burp", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound tag = stack.getTagCompound();
        int amplifier = 0;
        
        // Add caffeine
        if (!tag.getBoolean("decaf")) {
            if (player.isPotionActive(Coffee.caffeine)) amplifier = player.getActivePotionEffect(Coffee.caffeine).getAmplifier() + 1;
            player.addPotionEffect(new PotionEffect(Coffee.caffeine.id, 6000, amplifier));
        }
        
        // Add sugar
        if (tag.getInteger("sugar") > 0) {
            amplifier = tag.getInteger("sugar") - 1;
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600 * (amplifier + 1), amplifier));
        }
        
        // Add poison
        if (tag.getInteger("poison") > 0) {
            amplifier = tag.getInteger("poison") - 1;
            player.addPotionEffect(new PotionEffect(Potion.poison.id, 200 * (amplifier + 1), amplifier));
        }
        
        // Add milk
        if (tag.getInteger("milk") > 0) player.getFoodStats().addStats(tag.getInteger("milk"), 0.1F);
        
        return stack;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.drink;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean par4) {
        CoffeeHelper.addInformation(stack, lines);
    }
    
    @Override
    public String getItemDisplayName(ItemStack stack) {
        return CoffeeHelper.getCoffeeName(stack);
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List items) {
        
        items.add(new ItemStack(id, 1, 0));
        
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("decaf", true);
        ItemStack coffee = new ItemStack(id, 1, 0);
        coffee.setTagCompound(tag);
        items.add(coffee);
        
        tag = new NBTTagCompound();
        tag.setInteger("sugar", 1);
        coffee = new ItemStack(id, 1, 0);
        coffee.setTagCompound(tag);
        items.add(coffee);
        
        tag = new NBTTagCompound();
        tag.setInteger("poison", 1);
        coffee = new ItemStack(id, 1, 0);
        coffee.setTagCompound(tag);
        items.add(coffee);
        
        tag = new NBTTagCompound();
        tag.setInteger("milk", 1);
        coffee = new ItemStack(id, 1, 0);
        coffee.setTagCompound(tag);
        items.add(coffee);
    }
}
