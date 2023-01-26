package me.pvpb0t.flatworld;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class EventListener implements Listener {

    private int threeXChance;
    private int fiveXChance;

    public EventListener(int threeXChance, int fiveXChance) {
        this.threeXChance = threeXChance;
        this.fiveXChance = fiveXChance;
    }

    @EventHandler
    public void onPlayerMakeTrade(final InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.MERCHANT) return;
        if (!(event.getInventory().getHolder() instanceof Villager)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getRawSlot() != 2) return;
        final Player player = (Player) event.getWhoClicked();
        final Villager villager = (Villager) event.getInventory().getHolder();


        //Get the items involved in the trade
        final ItemStack result = event.getCurrentItem();
        ItemStack ingredient1 = event.getInventory().getItem(0);
        ItemStack ingredient2 = event.getInventory().getItem(1);
        if (result == null || result.getType() == Material.AIR) return;
        if (ingredient1 == null) ingredient1 = new ItemStack(Material.AIR, 1);
        if (ingredient2 == null) ingredient2 = new ItemStack(Material.AIR, 1);

        if (result.getType() == Material.EMERALD) {
            int amount = result.getAmount();

            //Check if we should use 3x emeralds
            if (Math.random() * 100 < threeXChance) {
                amount *= 3;
            }
            //Check if we should use 5x emeralds
            if (Math.random() * 100 < fiveXChance) {
                amount *= 5;
            }


            player.getInventory().addItem(new ItemStack(Material.EMERALD, amount));
        }

    }




    }


