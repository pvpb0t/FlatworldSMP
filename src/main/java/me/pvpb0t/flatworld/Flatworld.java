package me.pvpb0t.flatworld;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Flatworld extends JavaPlugin {

    private int threeXChance;
    private int fiveXChance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        threeXChance = getConfig().getInt("3xchance");
        fiveXChance = getConfig().getInt("5xchance");
        getServer().getPluginManager().registerEvents(new EventListener(threeXChance,fiveXChance), this);
    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
