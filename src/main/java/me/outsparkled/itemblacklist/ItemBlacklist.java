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


public final class ItemBlacklist extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("OutsparkledsEssentials has started");



        getServer().getPluginManager().registerEvents(new PlaceBlacklist(), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer(this, new Runnable() {
            @Override
            public void run() {

                //System.out.println("task has run");

                for(Player player : getServer().getOnlinePlayers()){
                    if(player.hasPermission("BetterEssentials.ItemBlacklist.bypass")){
                        return;
                    }

                    //player.sendMessage("checking...");
                    Inventory inv = player.getInventory();
                    ItemStack offHand = player.getEquipment().getItemInOffHand();
                    ItemStack cursor = player.getItemOnCursor();
                    ItemStack air = new ItemStack(Material.AIR, 1);


                    boolean found = false;
                    for (ItemStack stack : inv.getContents()) {
                        if(stack != null && stack.getType() == Material.BARRIER) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.BEDROCK) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.COMMAND_BLOCK) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.COMMAND_BLOCK_MINECART) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.CHAIN_COMMAND_BLOCK) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.REPEATING_COMMAND_BLOCK) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.END_PORTAL_FRAME) {
                            inv.removeItem(stack);
                            found = true;
                        }else if(stack != null && stack.getType() == Material.SPAWNER) {
                            inv.removeItem(stack);
                            found = true;
                        }

                    }

                    if(offHand != null && offHand.getType() == Material.BARRIER) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.BEDROCK) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.COMMAND_BLOCK) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.COMMAND_BLOCK_MINECART) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.CHAIN_COMMAND_BLOCK) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.REPEATING_COMMAND_BLOCK) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.END_PORTAL_FRAME) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }else if(offHand != null && offHand.getType() == Material.SPAWNER) {
                        player.getEquipment().setItemInOffHand(air);
                        found = true;
                    }


                    if(cursor != null && cursor.getType() == Material.BARRIER) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.BEDROCK) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.COMMAND_BLOCK) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.COMMAND_BLOCK_MINECART) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.CHAIN_COMMAND_BLOCK) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.REPEATING_COMMAND_BLOCK) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.END_PORTAL_FRAME) {
                        player.setItemOnCursor(air);
                        found = true;
                    }else if(cursor != null && cursor.getType() == Material.SPAWNER) {
                        player.setItemOnCursor(air);
                        found = true;
                    }

                    if(found) {
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 2F, 1F);
                        player.sendMessage(ChatColor.RED + "Illegal items were removed from your inventory!");
                    }











                }
            }
        }, 0L, 20L);


    }
}

