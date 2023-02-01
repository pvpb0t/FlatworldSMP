package me.pvpb0t.flatworld;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SlimeControl implements Listener {

    public SlimeControl(){

    }


    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if(e.getEntity().getName().equalsIgnoreCase("slime")){
            e.setCancelled(true);
        }
        if(e.getEntityType() == EntityType.SLIME)
            e.setCancelled(true);
    }

}
