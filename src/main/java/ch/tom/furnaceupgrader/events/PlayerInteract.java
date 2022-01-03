package ch.tom.furnaceupgrader.events;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import ch.tom.furnaceupgrader.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class PlayerInteract implements Listener {

    FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();
    Location furnaceLocation;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.isSneaking()) {
            if (Objects.requireNonNull(event.getClickedBlock()).getBlockData().getMaterial()  == Material.FURNACE) {
                Inventory furnaceInventory = Bukkit.createInventory(null, 9 * 3, "§6FurnaceUpgrader §8| §7T-Codes");

                for (int i = 0; i < 27; i++) {
                    furnaceInventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE, 1).create());
                }

                furnaceLocation = new Location(event.getClickedBlock().getWorld(), event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ());

                furnaceInventory.setItem(12, new ItemCreator(Material.FURNACE, 1)
                        .setDisplayName("§7id§8: §7"+plugin.getFurnaceDAOImp().getFromLocation(furnaceLocation).getId().toString())
                        .create());

                furnaceInventory.setItem(10, new ItemCreator(Material.EXPERIENCE_BOTTLE, 1)
                        .setDisplayName("§aLevel§8: §7"+plugin.getFurnaceDAOImp().getFromLocation(furnaceLocation).getLevel().toString())
                        .create());


                furnaceInventory.setItem(14, new ItemCreator(Material.GREEN_DYE, 1)
                        .setDisplayName("§aUpgrade§8:")
                        .create());

                player.openInventory(furnaceInventory);
            }
        }
    }

    @EventHandler
    public void onInvetoryClickEvent(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("§6FurnaceUpgrader §8| §7T-Codes")) {
            if (event.getCurrentItem().getType() == Material.GREEN_DYE) {
                plugin.getFurnaceDAOImp().update(plugin.getFurnaceDAOImp().getFromLocation(furnaceLocation));
            }
        }
    }
}
