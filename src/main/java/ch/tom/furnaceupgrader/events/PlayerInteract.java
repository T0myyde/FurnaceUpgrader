package ch.tom.furnaceupgrader.events;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import ch.tom.furnaceupgrader.furnance.Furnace;
import ch.tom.furnaceupgrader.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


public class PlayerInteract implements Listener {

    Location furnaceLocation;

    FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.isSneaking()) {
            if (event.getClickedBlock().getBlockData().getMaterial() == Material.FURNACE) {
                furnaceLocation = new Location(event.getClickedBlock().getWorld(), event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ());
                Furnace furnace = plugin.getFurnaceService().getFromLocation(furnaceLocation);
                Inventory furnaceInventory = Bukkit.createInventory(null, 9 * 3, "§6FurnaceUpgrader §8| §7T-Codes");
                furnaceInventory.setItem(16, new ItemCreator(Material.DIAMOND, 1)
                        .setDisplayName("§aUpgrade§8")
                        .create());

                furnaceInventory.setItem(10, new ItemCreator(Material.EXPERIENCE_BOTTLE, 1)
                        .setDisplayName("§aLevel§8: §7" + furnace.getLevel())
                        .create());

                player.openInventory(furnaceInventory);
                event.setCancelled(true);
                System.out.println("interact: " + furnace);
            }
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("§6FurnaceUpgrader §8| §7T-Codes")) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            Furnace furnace = plugin.getFurnaceService().getFromLocation(furnaceLocation);
            if (event.getCurrentItem().getType() == Material.DIAMOND) {
                if (player.getInventory().contains(Material.DIAMOND)) {
                    int furnaceLevel = furnace.getLevel();
                    int newLevel = furnaceLevel + 1;
                    if (newLevel <= 5) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 5));
                        furnace.setLevel(newLevel);
                        plugin.getFurnaceService().update(furnace);
                        System.out.println(furnace.getLevel());
                        player.sendMessage("§aDein offen wurde ein Level hochgestufft");
                        System.out.println("Click: " + furnace);
                        Block block = Bukkit.getWorld("world").getBlockAt(furnaceLocation);

                        org.bukkit.block.Furnace furnaceBlock = (org.bukkit.block.Furnace) block.getState();


                    } else {
                        player.sendMessage("§cMaximlate Upgrade Stuffe erreicht");
                    }
                } else {
                    player.sendMessage("§aDu hast keine Diamanten");
                }
            }
        }
    }

    @EventHandler
    public void onFurnace(FurnaceBurnEvent event) {
            new BukkitRunnable() {
                private short speedUp = 10;
                @Override
                public void run() {
                    org.bukkit.block.Furnace furnace = (org.bukkit.block.Furnace) event.getBlock().getState();
                    furnace.setBurnTime((short) (furnace.getBurnTime() - speedUp + speedUp / 10));
                    if (furnace.getInventory().getSmelting() != null && furnace.getInventory().getSmelting().getType() != Material.AIR && (furnace.getCookTime() > 0 || furnace.getBurnTime() > 0)) {
                        if (furnace.getCookTimeTotal() -1 != furnace.getCookTime()) {
                            furnace.setCookTime((short) (furnace.getCookTime() + speedUp));
                            furnace.update();
                        } else {
                            //STOP AND START IN THE LAST TIK
                            cancel();
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 1);
        }
}
