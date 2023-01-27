package me.pvpb0t.flatworld;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FarmListener implements Listener {

    public FarmListener(){

    }

    static {

    }

    //onClick
    //check if clicked block is farm crop (carrot, wheat, potato, etc)
    //if player.isHOlding a crop/seedable
    //if crop is fully grown
    //replace crop w the seed/crop the player is holding and place the grown crop farmed into the inventory of player
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        Material blockType = block.getType();

        if(blockType == Material.CARROT || blockType == Material.WHEAT ||blockType == Material.BEETROOT|| blockType == Material.POTATO) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            Material itemType = item.getType();

            if(itemType == Material.CARROT || itemType == Material.BEETROOT_SEEDS || itemType == Material.WHEAT_SEEDS || itemType == Material.POTATO) {
                BlockData blockData = block.getBlockData();
                // check if the crop is fully grown
                if(blockData instanceof Ageable && ((Ageable) blockData).getAge() == ((Ageable) blockData).getMaximumAge()) {
                    block.setType(itemType);
                    // set new data for the block
                    if (itemType == Material.CARROT) {
                        block.setBlockData(Material.CARROTS.createBlockData());
                    } else if (itemType == Material.BEETROOT_SEEDS) {
                        block.setBlockData(Material.BEETROOTS.createBlockData());
                    } else if (itemType == Material.WHEAT_SEEDS) {
                        block.setBlockData(Material.WHEAT.createBlockData());
                    } else if (itemType == Material.POTATO) {
                        block.setBlockData(Material.POTATOES.createBlockData());
                    }
                    e.getPlayer().getInventory().addItem(new ItemStack(blockType));
                    // remove the seed/crop from the player's inventory
                    int amount = e.getPlayer().getInventory().getItemInMainHand().getAmount();
                    if (amount > 1) {
                        e.getPlayer().getInventory().getItemInMainHand().setAmount(amount-1);
                    } else {
                        e.getPlayer().getInventory().setItemInMainHand(null);
                    }
                }
            }

        }
    }


}
