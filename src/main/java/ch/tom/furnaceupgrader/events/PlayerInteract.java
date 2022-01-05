package ch.tom.furnaceupgrader.events;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import ch.tom.furnaceupgrader.furnance.Furnace;
import ch.tom.furnaceupgrader.utils.ItemCreator;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


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

                for (int i = 0; i < 27; i ++) {
                    furnaceInventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE, 1).setDisplayName(null).create());
                }
                furnaceInventory.setItem(16, new ItemCreator(Material.DIAMOND, 1)
                        .setDisplayName("§aUpgrade§8")
                        .create());

                furnaceInventory.setItem(10, new ItemCreator(Material.FURNACE, 1)
                        .setDisplayName("§aid§8: §7" + furnace.getId())
                        .create());

                furnaceInventory.setItem(13, new ItemCreator(Material.EXPERIENCE_BOTTLE, 1)
                        .setDisplayName("§aLevel§8: §7" + furnace.getLevel())
                        .create());

                player.openInventory(furnaceInventory);
                event.setCancelled(true);
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
                if (player.getInventory().contains(Material.DIAMOND, 5)) {
                    int furnaceLevel = furnace.getLevel();
                    int newLevel = furnaceLevel + 1;
                    if (newLevel <= 5) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 5));
                        furnace.setLevel(newLevel);
                        plugin.getFurnaceService().update(furnace);
                        player.sendMessage("§aDein offen wurde ein Level hochgestuft");
                        Block block = Bukkit.getWorld("world").getBlockAt(furnaceLocation);

                        org.bukkit.block.Furnace furnaceBlock = (org.bukkit.block.Furnace) block.getState();
                        furnaceBlock.setCookTime((short) (furnaceBlock.getCookTime() + 30));

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            player.playSound(furnaceLocation, Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                            all.spawnParticle(Particle.FIREWORKS_SPARK, furnaceLocation, 100);
                        }

                    } else {
                        player.sendMessage("§aMaximale Upgrade Stuffe erreicht");
                    }
                } else {
                    player.sendMessage("§cDu hast keine Diamanten");
                }
            }
        }
    }

    @EventHandler
    public void onFurnace(FurnaceStartSmeltEvent event) {
        Block block = event.getBlock();
        org.bukkit.block.Furnace furnaceBlock = (org.bukkit.block.Furnace) block.getState();
        Furnace furnace = plugin.getFurnaceService().getFromLocation(event.getBlock().getLocation());

        if (furnaceBlock.getCookTime() < 200) {
                switch (furnace.getLevel()) {
                    case 1:
                        furnaceBlock.setCookTime((short) (furnaceBlock.getCookTime() + 20));
                        break;
                    case 2:
                        furnaceBlock.setCookTime((short) (furnaceBlock.getCookTime() + 60));
                        break;
                    case 3:
                        furnaceBlock.setCookTime((short) (furnaceBlock.getCookTime() + 80));
                        break;
                    case 4:
                        furnaceBlock.setCookTime((short) (furnaceBlock.getCookTime() + 90));
                        break;
                    case 5:
                        furnaceBlock.setCookTime((short) (furnaceBlock.getCookTime() + 100));
                        break;
                    default:
                        break;

                }
                furnaceBlock.update(true);


        }

    }
}
