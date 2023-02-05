package me.pvpb0t.flatworld.controllers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

public class ThunderControl extends Control implements Listener {

    public ThunderControl(boolean enable) {
        super(enable);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onLightning(LightningStrikeEvent e){
        if(this.isEnabled())
            e.setCancelled(true);
    }

}
