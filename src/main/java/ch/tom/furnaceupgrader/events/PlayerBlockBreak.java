package ch.tom.furnaceupgrader.events;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockBreak implements Listener {

    FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getState() instanceof Furnace) {
            plugin.getFurnaceService().delete(plugin.getFurnaceService().getFromLocation(event.getBlock().getLocation()));
        }
    }
}
