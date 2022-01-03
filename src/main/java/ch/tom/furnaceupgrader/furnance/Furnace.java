package ch.tom.furnaceupgrader.furnance;

import org.bukkit.Location;

import java.util.UUID;

public class Furnace {

    private Integer id;
    private int x;
    private int y;
    private int z;
    private String world;
    private UUID owner;
    private Integer level;

    public Furnace(Integer id, Location location, int x, int y, int z, String world, UUID owner, Integer level) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.owner = owner;
        this.level = level;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
