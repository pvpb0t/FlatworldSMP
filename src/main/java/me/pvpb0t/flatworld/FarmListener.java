package me.pvpb0t.flatworld;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
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
            Material blockType = block.getType();
            final Player player = e.getPlayer();

            if(blockType == Material.CARROTS || blockType == Material.WHEAT || blockType == Material.BEETROOTS || blockType == Material.POTATOES) {
                ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
                Material itemType = item.getType();

                // check that the player is holding a valid seed/crop
                if (itemType == Material.CARROT || itemType == Material.BEETROOT_SEEDS || itemType == Material.WHEAT_SEEDS || itemType == Material.POTATO) {
                    BlockData blockData = block.getBlockData();
                    // check that the crop is fully grown
                    if (blockData instanceof Ageable && ((Ageable) blockData).getAge() == ((Ageable) blockData).getMaximumAge()) {

                        // check that the seed/crop being held matches the block being clicked
                        if (blockType == Material.CARROTS && itemType == Material.CARROT ||
                                blockType == Material.BEETROOTS && itemType == Material.BEETROOT_SEEDS ||
                                blockType == Material.WHEAT && itemType == Material.WHEAT_SEEDS ||
                                blockType == Material.POTATOES && itemType == Material.POTATO) {

                            // add the farmed crop to the player's inventory
                            e.getPlayer().getInventory().addItem(new ItemStack(blockType));
                            // remove the seed/crop from the player's inventory
                            int amount = e.getPlayer().getInventory().getItemInMainHand().getAmount();
                            if (amount > 1) {
                                e.getPlayer().getInventory().getItemInMainHand().setAmount(amount - 1);
                            } else {
                                e.getPlayer().getInventory().setItemInMainHand(null);
                            }
                            // replant the crop
                            if (itemType == Material.CARROT) {
                                block.setType(Material.CARROTS);
                                block.setBlockData(Material.CARROTS.createBlockData());
                            } else if (itemType == Material.BEETROOT_SEEDS) {
                                block.setType(Material.BEETROOTS);
                                block.setBlockData(Material.BEETROOTS.createBlockData());
                            } else if (itemType == Material.WHEAT_SEEDS) {
                                block.setType(Material.WHEAT);
                                block.setBlockData(Material.WHEAT.createBlockData());
                            }else if (itemType == Material.POTATO) {
                                block.setType(Material.POTATOES);
                                block.setBlockData(Material.POTATOES.createBlockData());
                            }
                            //e.getPlayer().getInventory().addItem(new ItemStack(blockType,3));
                            player.getInventory().addItem(new ItemStack(itemType, 3));
                            player.playSound(player.getLocation(), Sound.BLOCK_GRASS_PLACE, 1, 2);
                            player.playEffect(block.getLocation(), Effect.VILLAGER_PLANT_GROW, null);


                        }else {
//if the player is not holding the correct item then dont plant the new crop and dont drop any items
                            e.getPlayer().sendMessage("You need to hold the correct seed to replant the crop");
                        }
                    }else {
//if the crop is not fully grown then dont replant and dont drop any items
                        e.getPlayer().sendMessage("The crop is not fully grown yet");
                    }
                }
            }
        }
    }


}
