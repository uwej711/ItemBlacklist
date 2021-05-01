package me.outsparkled.itemblacklist;



import me.outsparkled.itemblacklist.events.PlaceBlacklist;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Locale;
import java.util.Objects;


public final class ItemBlacklist extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("ItemBlacklist has started");
        getConfig().options().copyDefaults();
        saveDefaultConfig();



        getServer().getPluginManager().registerEvents(new PlaceBlacklist(), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer(this, new Runnable() {
            @Override
            public void run() {

                List<String> banned_items_list = getConfig().getStringList("banned-items");
                String[] banned_items = new String[banned_items_list.size()];
                banned_items_list.toArray(banned_items);

                for(Player player : getServer().getOnlinePlayers()){
                    if(player.hasPermission("ItemBlacklist.Items.bypass")){
                        return;
                    }

                    for(String banned_item : banned_items){



                        //player.sendMessage("checking...");
                        Inventory inv = player.getInventory();
                        ItemStack offHand = player.getEquipment().getItemInOffHand();
                        ItemStack cursor = player.getItemOnCursor();
                        ItemStack air = new ItemStack(Material.AIR, 1);




                        boolean found = false;
                        for (ItemStack stack : inv.getContents()) {
                            if(stack != null && stack.getType() == Material.matchMaterial(banned_item.toUpperCase(Locale.ROOT))) {
                                inv.removeItem(stack);
                                found = true;
                            }

                        }

                        if(offHand != null && offHand.getType() == Material.matchMaterial(banned_item.toUpperCase(Locale.ROOT))) {
                            player.getEquipment().setItemInOffHand(air);
                            found = true;
                        }


                        if(cursor != null && cursor.getType() == Material.matchMaterial(banned_item.toUpperCase(Locale.ROOT))) {
                            player.setItemOnCursor(air);
                            found = true;
                        }

                        if(found) {
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 2F, 1F);
                            player.sendMessage(ChatColor.RED + "Illegal items were removed from your inventory!");
                        }











                    }

                }


            }
        }, 0L, getConfig().getLong("check-delay"));


    }
}

