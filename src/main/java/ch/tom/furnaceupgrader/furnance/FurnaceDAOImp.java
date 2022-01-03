package ch.tom.furnaceupgrader.furnance;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import org.bukkit.Location;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FurnaceDAOImp implements FurnaceDAO {
    private FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    @Override
    public void save(Furnace furnace) {
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "INSERT INTO `furnace` (id, x,y,z,world,owner_uuid, level) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, furnace.getId());
            preparedStatement.setInt(2, furnace.getX());
            preparedStatement.setInt(3, furnace.getY());
            preparedStatement.setInt(4, furnace.getZ());
            preparedStatement.setString(5, furnace.getWorld());
            preparedStatement.setString(6, furnace.getOwner().toString());
            preparedStatement.setInt(7, furnace.getLevel());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Furnace furnace) {
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "UPDATE `furnace` SET level=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, furnace.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Furnace furnace) {
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "DELETE from `furnace` WHERE id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, furnace.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Furnace get(int id) {
        Furnace furnace = new Furnace();
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "SELECT * FROM `furnace` WHERE id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                furnace.setId(rs.getInt("id"));
                furnace.setLevel(rs.getInt("level"));
                furnace.setOwner(UUID.fromString(rs.getString("owner_uuid")));
                furnace.setWorld(rs.getString("world"));
                furnace.setX(rs.getInt("x"));
                furnace.setY(rs.getInt("y"));
                furnace.setZ(rs.getInt("z"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return furnace;
    }

    @Override
    public Furnace getFromUUID(UUID uuid) {
        Furnace furnace = new Furnace();
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "SELECT * FROM `furnace` WHERE owner_uuid=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                furnace.setId(rs.getInt("id"));
                furnace.setLevel(rs.getInt("level"));
                furnace.setOwner(UUID.fromString(rs.getString("owner_uuid")));
                furnace.setWorld(rs.getString("world"));
                furnace.setX(rs.getInt("x"));
                furnace.setY(rs.getInt("y"));
                furnace.setZ(rs.getInt("z"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return furnace;
    }

    @Override
    public Furnace getFromLocation(Location location) {
        Furnace furnace = new Furnace();
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "SELECT * FROM `furnace` WHERE x=? AND y=? AND z=? AND world=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(location.getX()));
            preparedStatement.setString(2, String.valueOf(location.getY()));
            preparedStatement.setString(3, String.valueOf(location.getZ()));
            preparedStatement.setString(4, location.getWorld().getName());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                furnace.setId(rs.getInt("id"));
                furnace.setLevel(rs.getInt("level"));
                furnace.setOwner(UUID.fromString(rs.getString("owner_uuid")));
                furnace.setWorld(rs.getString("world"));
                furnace.setX(rs.getInt("x"));
                furnace.setY(rs.getInt("y"));
                furnace.setZ(rs.getInt("z"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return furnace;
    }

    @Override
    public List<Furnace> list() {
        List<Furnace> list = new ArrayList<Furnace>();

        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "SELECT * FROM `furnace` WHERE id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Furnace furnace = new Furnace();
                furnace.setId(rs.getInt("id"));
                furnace.setLevel(rs.getInt("level"));
                furnace.setOwner(UUID.fromString(rs.getString("owner_uuid")));
                furnace.setWorld(rs.getString("world"));
                furnace.setX(rs.getInt("x"));
                furnace.setY(rs.getInt("y"));
                furnace.setZ(rs.getInt("z"));
                list.add(furnace);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
