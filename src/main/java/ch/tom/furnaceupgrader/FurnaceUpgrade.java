package ch.tom.furnaceupgrader;

import ch.tom.furnaceupgrader.events.PlayerBlockBreak;
import ch.tom.furnaceupgrader.events.PlayerBlockPlace;
import ch.tom.furnaceupgrader.events.PlayerInteract;
import ch.tom.furnaceupgrader.furnance.Furnace;
import ch.tom.furnaceupgrader.furnance.FurnaceDAO;
import ch.tom.furnaceupgrader.furnance.FurnaceDAOImp;
import ch.tom.furnaceupgrader.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FurnaceUpgrade extends JavaPlugin {

    private static FurnaceUpgrade instance;
    private MySQL mySQl = new MySQL();
    private FurnaceDAOImp furnaceDAOImp = new FurnaceDAOImp();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        mySQl.startConnection();
        onInit(Bukkit.getPluginManager());
    }

    @Override
    public void onDisable() {
        mySQl.stopConnection();
    }

    private void onInit(PluginManager pluginManager) {
        pluginManager.registerEvents(new PlayerBlockBreak(), this);
        pluginManager.registerEvents(new PlayerBlockPlace(), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
    }

    public static FurnaceUpgrade getInstance() {
        return instance;
    }

    public MySQL getMySQl() {
        return mySQl;
    }

    public FurnaceDAOImp getFurnaceDAOImp() {
        return furnaceDAOImp;
    }
}
