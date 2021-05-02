package me.outsparkled.itemblacklist.events;

import me.outsparkled.itemblacklist.ItemBlacklist;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Locale;

public class PlaceBlacklist implements Listener {

    Plugin plugin = ItemBlacklist.getPlugin(ItemBlacklist.class);

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        ItemStack placedBlock = new ItemStack(e.getBlock().getType());

        Player player = e.getPlayer();

        if(player.hasPermission("ItemBlacklist.Blocks.bypass")){
            return;
        }


        List<String> banned_blocks_list = plugin.getConfig().getStringList("banned-blocks");
        String[] banned_blocks = new String[banned_blocks_list.size()];
        banned_blocks_list.toArray(banned_blocks);

        for(String banned_block : banned_blocks){
            if (placedBlock.getType() == Material.matchMaterial(banned_block.toUpperCase(Locale.ROOT))) {
                e.setCancelled(true);
            }
        }
    }
}