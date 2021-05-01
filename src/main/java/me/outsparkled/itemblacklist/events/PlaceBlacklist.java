//PlaceBlacklist

package me.outsparkled.itemblacklist.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PlaceBlacklist implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        //blockPlaceEvent
        ItemStack placedBlock = new ItemStack(e.getBlock().getType());
        //get ItemStack of placed block

        Player player = e.getPlayer();
        //get player who placed the block

        if(player.hasPermission("BetterEssentials.PlaceBlacklist.bypass")){
            return;
            //if player has bypass permission, don't check block
        }

        if (placedBlock.getType() == Material.BARRIER) {
            e.setCancelled(true);
        } else if (placedBlock.getType() == Material.BEDROCK) {
            e.setCancelled(true);
        } else if (placedBlock.getType() == Material.SPAWNER) {
            e.setCancelled(true);
        } else if (placedBlock.getType() == Material.END_PORTAL_FRAME) {
            e.setCancelled(true);
        }
        //if ItemStack is blacklisted, cancel

    }
}