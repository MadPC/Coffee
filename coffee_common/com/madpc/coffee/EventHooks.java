package com.madpc.coffee;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.madpc.coffee.potion.DamageSourceCaffeine;

public class EventHooks {
    
    @ForgeSubscribe
    public void onEntityUpdate(LivingUpdateEvent event) {
        if (event.entityLiving.isPotionActive(Coffee.caffeine)) {
            PotionEffect caffeine = event.entityLiving.getActivePotionEffect(Coffee.caffeine);
            int duration = caffeine.getDuration();
            int amplifier = caffeine.getAmplifier();
            if (duration > 600) {
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, amplifier));
                switch (amplifier) {
                    case 0:
                        break;
                    case 1:
                    case 2:
                        event.entityLiving.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2, 0));
                        break;
                    default:
                        event.entityLiving.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2, 1));
                        break;
                }
            } else {
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2, amplifier));
                switch (amplifier) {
                    case 0:
                        break;
                    case 1:
                    case 2:
                        event.entityLiving.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 2, 0));
                        break;
                    default:
                        event.entityLiving.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 2, 1));
                        break;
                }
            }
            
            if (amplifier == 4) {
                if (duration > 100) {
                    caffeine.duration = 100;
                    event.entityLiving.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
                } else if (duration == 1) {
                    event.entityLiving.attackEntityFrom(new DamageSourceCaffeine(), 1000);
                    event.entityLiving.worldObj.createExplosion(event.entityLiving, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, 2.0F, false);
                    event.entityLiving.removePotionEffect(Coffee.caffeine.getId());
                }
            }
        }
    }
}
