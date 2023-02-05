package me.pvpb0t.flatworld;

import me.pvpb0t.flatworld.controllers.SlimeControl;
import me.pvpb0t.flatworld.controllers.ThunderControl;
import me.pvpb0t.flatworld.controllers.TimeControl;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;
import java.util.Random;

public class Flatworld extends JavaPlugin {
    private int threeXChance;
    private int fiveXChance;
    private boolean disableSlime;
    private boolean disableThunder;
    private boolean syncTime;



    public static final String pluginName="Flatworld";

    private TimeControl timeControl;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        threeXChance = getConfig().getInt("3xchance");
        fiveXChance = getConfig().getInt("5xchance");
        disableThunder = getConfig().getBoolean("disable-thunder");
        disableSlime = getConfig().getBoolean("disable-slime");
        syncTime=getConfig().getBoolean("sync-time");
        timeControl=new TimeControl(syncTime);
        getServer().getPluginManager().registerEvents(new VillagerTradingListener(threeXChance, fiveXChance), this);
        getServer().getPluginManager().registerEvents(new FarmListener(), this);
        getServer().getPluginManager().registerEvents(new SlimeControl(disableSlime), this);
        getServer().getPluginManager().registerEvents(new ThunderControl(disableThunder), this);

        // Start task to update time/season
        startTimeTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void startTimeTask() {
        getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                timeControl.updateTime();
            }
        }, 0L, 20L);
    }

}
