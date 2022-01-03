package ch.tom.furnaceupgrader.furnance;

import ch.tom.furnaceupgrader.FurnaceUpgrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FurnaceDAOImp implements FurnaceDAO {
    private FurnaceService furnaceService = new FurnaceService();
    private FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    @Override
    public void save(Furnace furnace) {
        try {
            Connection con = plugin.getMySQl().getConnection();
            String sql = "INSERT INTO `furnace` (furnace_id, x,y,z,world,owner_uuid, level) VALUES(?,?,?,?,?,?,?)";
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

    }

    @Override
    public Furnace get(int id) {
        return null;
    }

    @Override
    public List<Furnace> list() {
        return null;
    }
}
