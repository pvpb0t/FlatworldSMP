package me.pvpb0t.flatworld.controllers;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SlimeControl extends Control implements Listener {



    public SlimeControl(boolean enable){
    super(enable);
    }


    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if(this.isEnabled()){
            if(e.getEntity().getName().equalsIgnoreCase("slime")){
                e.setCancelled(true);
            }
            if(e.getEntityType() == EntityType.SLIME)
                e.setCancelled(true);
        }

    }

}
