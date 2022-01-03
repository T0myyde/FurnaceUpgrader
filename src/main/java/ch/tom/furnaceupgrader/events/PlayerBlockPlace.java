package ch.tom.furnaceupgrader.events;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import ch.tom.furnaceupgrader.furnance.Furnace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class PlayerBlockPlace implements Listener {

    FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.FURNACE) {
            Block block = event.getBlock();
            plugin.getFurnaceDAOImp().save(new Furnace(0,block.getX(), block.getY(), block.getZ(), block.getWorld().toString(), event.getPlayer().getUniqueId(), 0));
        }
    }
}
