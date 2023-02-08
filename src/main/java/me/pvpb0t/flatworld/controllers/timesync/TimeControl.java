package me.pvpb0t.flatworld.controllers.timesync;

import me.pvpb0t.flatworld.Flatworld;
import me.pvpb0t.flatworld.controllers.Control;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Random;

public class TimeControl extends Control {
    public TimeControl(boolean toggle) {
        super(toggle);
    }

    private long lastMinute;
    private long lastMonth;
    private String parsedInGameTime;
    private long ingameTime;
    private Season ingameSeason;
    private String ingameWeather;
    private final String levelName= "world";

    public void updateTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int month = calendar.get(Calendar.MONTH) + 1;



        if (lastMinute != minute) {
            lastMinute = minute;
            int totalMinutes = hour * 60 + minute;
            int inGameTicks = (totalMinutes * 1000) % 24000;
            World survivalWorld = Bukkit.getWorld(levelName);

            assert survivalWorld != null;
            survivalWorld.setTime(inGameTicks);
            switch (month) {
                case 12:
                case 1:
                case 2:
                    ingameSeason = Season.Winter;
                    break;
                case 3:
                case 4:
                case 5:
                    ingameSeason = Season.Spring;
                    break;
                case 6:
                case 7:
                case 8:
                    ingameSeason = Season.Summer;
                    break;
                case 9:
                case 10:
                case 11:
                    ingameSeason = Season.Autumn;
                    break;
                default:
                    ingameSeason = Season.None;

                    break;
            }

            switch (ingameSeason) {
                case Winter:
                    Random rand = new Random();
                    int chance = rand.nextInt(100);
                    if (chance <= 90) {
                        ingameWeather = "Snowing";
                        survivalWorld.setStorm(true);
                        survivalWorld.setThundering(false);
                    } else {
                        ingameWeather = "Clear";
                        survivalWorld.setStorm(false);
                    }
                    break;
                case Spring:
                    rand = new Random();
                    chance = rand.nextInt(100);
                    if (chance <= 20) {
                        ingameWeather = "Raining";
                        survivalWorld.setStorm(true);
                        survivalWorld.setThundering(false);
                    } else {
                        ingameWeather = "Clear";
                        survivalWorld.setStorm(false);
                    }
                    break;
                case Summer:
                    rand = new Random();
                    chance = rand.nextInt(100);
                    if (chance <= 25) {
                        ingameWeather = "Raining";
                        survivalWorld.setStorm(true);
                        survivalWorld.setThundering(false);
                    } else {
                        ingameWeather = "Clear";
                        survivalWorld.setStorm(false);
                    }
                    break;
                case Autumn:
                    rand = new Random();
                    chance = rand.nextInt(100);
                    if (chance <= 75) {
                        ingameWeather = "Raining";
                        survivalWorld.setStorm(true);
                        survivalWorld.setThundering(false);
                    } else {
                        ingameWeather = "Clear";
                        survivalWorld.setStorm(false);
                    }
                    break;
                default:
                    ingameWeather = "Unknown";
                    survivalWorld.setStorm(false);
                    break;
            }

            generateSeason(survivalWorld, ingameSeason);



/*
            for (Chunk chunk : survivalWorld.getLoadedChunks()) {
                Bukkit.getLogger().info(ingameSeason);
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        Block block = chunk.getBlock(x, 0, z);
                        switch (ingameSeason) {
                            case "Winter":
                                block.setBiome(Biome.SNOWY_TAIGA);
                                break;
                            case "Spring":
                            case "Summer":
                                block.setBiome(Biome.PLAINS);
                                break;
                            case "Autumn":
                                block.setBiome(Biome.FOREST);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }*/



        }



        for (Player player : Bukkit.getOnlinePlayers()) {
            Component header = Component.text("Welcome to " + Flatworld.pluginName)
                    .color(NamedTextColor.GOLD)
                    .decoration(TextDecoration.BOLD, true);
            Component footer = Component.text("Time: " + parsedInGameTime + "  Season: " + ingameSeason + "  Weather: " + ingameWeather)
                    .color(NamedTextColor.AQUA)
                    .decoration(TextDecoration.ITALIC, true);
            player.sendPlayerListHeaderAndFooter(header, footer);
        }
    }


    public void generateSeason(World world, Season season) {
        for (Chunk chunk : world.getLoadedChunks()) {
            for (int x = 0; x < 16; x++) {
                for (int y = -64; y < 0; y++) {
                    for (int z = 0; z < 16; z++) {
                        Block block = chunk.getBlock(x, y, z);

                        if (block.getType() == Material.AIR)
                            continue;

                        switch (season) {
                            case Winter:
                                if (block.getType() == Material.GRASS_BLOCK && block.getBiome() == Biome.PLAINS) {
                                    Block above = chunk.getBlock(x, y + 1, z);
                                    if (above.getType() == Material.AIR) {
                                        above.setType(Material.SNOW);
                                    }
                                }
                                break;
                            case Summer:
                                Block above = chunk.getBlock(x, y + 1, z);
                                if (above.getType() == Material.SNOW) {
                                    above.setType(Material.AIR);
                                }
                                break;
                            case Autumn:
                            case Spring:
                            case None:
                                break;
                        }
                    }
                }
            }
        }
    }








}
