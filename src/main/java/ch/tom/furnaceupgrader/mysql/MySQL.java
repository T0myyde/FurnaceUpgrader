package ch.tom.furnaceupgrader.mysql;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQL {
    private MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();
    private Connection connection;
    private FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    public void startConnection() {
        try {
            if (connection == null) {
                dataSource.setServerName(plugin.getConfig().getString("MySQL.host"));
                dataSource.setPortNumber(plugin.getConfig().getInt("MySQL.port"));
                dataSource.setDatabaseName(plugin.getConfig().getString("MySQL.database"));
                dataSource.setUser(plugin.getConfig().getString("MySQL.username"));
                dataSource.setPassword(plugin.getConfig().getString("MySQL.password"));

                connection = dataSource.getConnection();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
