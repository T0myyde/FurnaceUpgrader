package ch.tom.furnaceupgrader;

import ch.tom.furnaceupgrader.mysql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public final class FurnaceUpgrade extends JavaPlugin {

    private static FurnaceUpgrade instance;
    private MySQL mySQl = new MySQL();

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }

    public static FurnaceUpgrade getInstance() {
        return instance;
    }

    public MySQL getMySQl() {
        return mySQl;
    }
}
