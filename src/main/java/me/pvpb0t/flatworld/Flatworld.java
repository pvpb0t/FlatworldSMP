package me.pvpb0t.flatworld;

import org.bukkit.plugin.java.JavaPlugin;

public final class Flatworld extends JavaPlugin {

    private int threeXChance;
    private int fiveXChance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        threeXChance = getConfig().getInt("3xchance");
        fiveXChance = getConfig().getInt("5xchance");
        getServer().getPluginManager().registerEvents(new VillagerTradingListener(threeXChance,fiveXChance), this);
        getServer().getPluginManager().registerEvents(new FarmListener(), this);

    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
