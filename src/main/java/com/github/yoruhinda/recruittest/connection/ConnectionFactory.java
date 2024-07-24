package com.github.yoruhinda.recruittest.connection;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    public static Connection connection(Plugin plugin) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "create table home (id int NOT NULL AUTO_INCREMENT , player varchar(255), name varchar(30), world varchar(30), x float, y float, z float, PRIMARY KEY ( id ))";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            plugin.getLogger().info("Connection failed");
        }
        return null;
    }
}
