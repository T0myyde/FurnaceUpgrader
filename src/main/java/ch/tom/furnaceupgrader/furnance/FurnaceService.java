package ch.tom.furnaceupgrader.furnance;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class FurnaceService {

    private FurnaceRepositoryImp repository = new FurnaceRepositoryImp();
    public Furnace add (Furnace furnace) {
        this.repository.save(furnace);
        return furnace;
    }

    public Furnace get(int id) {
        Furnace furnace = this.repository.get(id);
        if (furnace.getId() != 0) {
            return furnace;
        } else {
            return null;
        }
    }

    public Furnace getFromLocation(Location location) {
        Furnace furnace = this.repository.getFromLocation(location);
        return furnace;
    }

    public void update(Furnace furnace) {
        this.repository.update(furnace);
    }

    public void delete(Furnace furnace) {
        this.repository.delete(furnace);
    }

    public List<Furnace> getList() {
        return this.repository.list();
    }
}
