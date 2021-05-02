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

public final class ItemBlacklist extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("ItemBlacklist has started");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlaceBlacklist(), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskTimer(
            this,
            new Runnable() {

                @Override
                public void run() {
                    List<String> banned_items_list = getConfig().getStringList("banned-items");

                    for (Player player : getServer().getOnlinePlayers()) {
                        if (player.hasPermission("ItemBlacklist.Items.bypass")) {
                            return;
                        }

                        for (String banned_item : banned_items_list) {
                            Inventory inv = player.getInventory();
                            ItemStack offHand =
                                player.getEquipment() != null
                                        ? player.getEquipment().getItemInOffHand()
                                        : null;
                            ItemStack cursor = player.getItemOnCursor();
                            ItemStack air = new ItemStack(Material.AIR, 1);

                            boolean found = false;
                            for (ItemStack stack : inv.getContents()) {
                                if (isBanned(banned_item, stack)) {
                                    inv.removeItem(stack);
                                    found = true;
                                }
                            }

                            if (isBanned(banned_item, offHand)) {
                                player.getEquipment().setItemInOffHand(air);
                                found = true;
                            }

                            if (isBanned(banned_item, cursor)) {
                                player.setItemOnCursor(air);
                                found = true;
                            }

                            informPlayerIfNeeded(player, found);
                        }
                    }
                }
            },
            0L,
            getConfig().getLong("check-delay")
        );
    }

    private void informPlayerIfNeeded(Player player, boolean found) {
        if (found) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 2F, 1F);
            player.sendMessage(ChatColor.RED + "Illegal items were removed from your inventory!");
        }
    }

    private boolean isBanned(String banned_item, ItemStack stack) {
        return stack != null && stack.getType() == Material.matchMaterial(banned_item.toUpperCase(Locale.ROOT));
    }
}

