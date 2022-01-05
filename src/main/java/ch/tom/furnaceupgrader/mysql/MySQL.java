package ch.tom.furnaceupgrader.mysql;

import ch.tom.furnaceupgrader.FurnaceUpgrade;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL {

    public Connection connection;
    private FurnaceUpgrade plugin = FurnaceUpgrade.getInstance();

    public void startConnection() {
        Properties properties = new Properties();
        try {
            properties.put("user",plugin.getConfig().getString("MySQL.username"));
            properties.put("password",plugin.getConfig().getString("MySQL.password"));
            properties.put("autoReconnect", "true");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/main", properties);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            this.startConnection();
            return connection;
        }
    }
}
