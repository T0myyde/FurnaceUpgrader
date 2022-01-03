package ch.tom.furnaceupgrader.furnance;

import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public interface FurnaceDAO {
    void save(Furnace furnace);
    void update(Furnace furnace);
    void delete(Furnace furnace);
    Furnace get(int id);
    Furnace getFromUUID(UUID uuid);
    Furnace getFromLocation(Location location);

    List<Furnace> list();
}
