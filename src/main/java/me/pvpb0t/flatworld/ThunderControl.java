package me.pvpb0t.flatworld;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

public class ThunderControl implements Listener {

    public ThunderControl() {

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onLightning(LightningStrikeEvent e){
        e.setCancelled(true);
    }

}
