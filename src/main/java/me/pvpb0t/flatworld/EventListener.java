package me.pvpb0t.flatworld;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
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
    public void onVillagerTrade(VillagerAcquireTradeEvent event) {
        MerchantRecipe trade = event.getRecipe();
        ItemStack result = trade.getResult();
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
            event.getEntity().getInventory().addItem(new ItemStack(Material.EMERALD, amount));
        }
    }
}
