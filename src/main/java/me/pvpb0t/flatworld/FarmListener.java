package me.pvpb0t.flatworld;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.pvpb0t.flatworld.VillagerTradingListener.log;

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
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
        Block block = e.getClickedBlock();
            assert block != null;
            log.info(block.toString());
        Material blockType = block.getType();
        log.info(blockType.name());

        if(blockType == Material.CARROTS || blockType == Material.WHEAT ||blockType == Material.BEETROOT|| blockType == Material.POTATOES) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            Material itemType = item.getType();
            log.info(itemType.name());

            if (itemType == Material.CARROT || itemType == Material.BEETROOT_SEEDS || itemType == Material.WHEAT_SEEDS || itemType == Material.POTATO) {
                BlockData blockData = block.getBlockData();
                // check if the crop is fully grown
                if (blockData instanceof Ageable && ((Ageable) blockData).getAge() == ((Ageable) blockData).getMaximumAge()) {

                    log.info(blockData.getAsString());
                    // set new data for the block
                    if (itemType == Material.CARROT) {
                        block.setType(Material.CARROTS);
                        block.setBlockData(Material.CARROTS.createBlockData());
                    } else if (itemType == Material.BEETROOT_SEEDS) {
                        block.setType(Material.BEETROOT_SEEDS);
                        block.setBlockData(Material.BEETROOTS.createBlockData());
                    } else if (itemType == Material.WHEAT_SEEDS) {
                        block.setType(Material.WHEAT_SEEDS);
                        block.setBlockData(Material.WHEAT.createBlockData());
                    } else if (itemType == Material.POTATO) {
                        block.setType(Material.POTATO);
                        block.setBlockData(Material.POTATOES.createBlockData());
                    }
                    e.getPlayer().getInventory().addItem(new ItemStack(blockType));
                    // remove the seed/crop from the player's inventory
                    int amount = e.getPlayer().getInventory().getItemInMainHand().getAmount();
                    if (amount > 1) {
                        e.getPlayer().getInventory().getItemInMainHand().setAmount(amount + 3);
                    } else {
                        e.getPlayer().getInventory().setItemInMainHand(null);
                    }
                }
            }
        }

        }
    }


}
