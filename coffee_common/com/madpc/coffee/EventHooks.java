package com.madpc.coffee;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EventHooks {
    
    @ForgeSubscribe
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.entityLiving.isPotionActive(Coffee.caffeine)) {
            if (event.entityLiving.worldObj.rand.nextInt(20) == 0) {
                PotionEffect caffeine = event.entityLiving.getActivePotionEffect(Coffee.caffeine);
                if (caffeine.duration > 200)
                    event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, caffeine.duration - 200, 1));
                else
                    event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, caffeine.duration, 1));
            }
        }
    }
}
