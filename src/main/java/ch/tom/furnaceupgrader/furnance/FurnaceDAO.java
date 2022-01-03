package ch.tom.furnaceupgrader.furnance;

import java.util.List;

public interface FurnaceDAO {
    public void save(Furnace furnace);
    public void update(Furnace furnace);
    public void delete(Furnace furnace);
    public Furnace get(int id);
    public List<Furnace> list();
}
