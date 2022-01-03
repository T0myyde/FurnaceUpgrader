package ch.tom.furnaceupgrader.events;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockBreak implements Listener {

    FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getFurnaceDAOImp().getFromLocation(event.getBlock().getLocation()) != null) {
            plugin.getFurnaceDAOImp().delete(plugin.getFurnaceDAOImp().getFromLocation(event.getBlock().getLocation()));
        }
     }
}
