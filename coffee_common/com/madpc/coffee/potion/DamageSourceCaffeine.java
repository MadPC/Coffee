package com.madpc.coffee.potion;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class DamageSourceCaffeine extends DamageSource {
    
    public static final String[] messages = {
            StatCollector.translateToLocal("coffee.death.1"),
            StatCollector.translateToLocal("coffee.death.2"),
            StatCollector.translateToLocal("coffee.death.3"),
            StatCollector.translateToLocal("coffee.death.4"),
            StatCollector.translateToLocal("coffee.death.5"),
            StatCollector.translateToLocal("coffee.death.6"),
            StatCollector.translateToLocal("coffee.death.7"),
            StatCollector.translateToLocal("coffee.death.8"),
            StatCollector.translateToLocal("coffee.death.9"),
            StatCollector.translateToLocal("coffee.death.10"),
    };
    
    public DamageSourceCaffeine() {
        super("coffee");
        this.setDamageBypassesArmor();
    }
    
    @Override
    public String getDeathMessage(EntityLiving entity) {
        return DamageSourceCaffeine.messages[entity.worldObj.rand.nextInt(DamageSourceCaffeine.messages.length)].replaceAll("<P>", entity.getEntityName());
    }
}
