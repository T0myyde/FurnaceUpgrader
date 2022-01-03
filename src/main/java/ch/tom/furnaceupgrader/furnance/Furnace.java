package ch.tom.furnaceupgrader.furnance;

import org.bukkit.Location;

import java.util.Objects;
import java.util.UUID;

public class Furnace {

    private Integer id;
    private int x;
    private int y;
    private int z;
    private String world;
    private UUID owner;
    private Integer level;
    public Furnace() {
    }
    public Furnace(Integer id, int x, int y, int z, String world, UUID owner, Integer level) {
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

    @Override
    public String toString() {
        return "Furnace{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", world='" + world + '\'' +
                ", owner=" + owner +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furnace furnace = (Furnace) o;
        return x == furnace.x && y == furnace.y && z == furnace.z && Objects.equals(id, furnace.id) && Objects.equals(world, furnace.world) && Objects.equals(owner, furnace.owner) && Objects.equals(level, furnace.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, z, world, owner, level);
    }
}
