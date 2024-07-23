package com.github.yoruhinda.recruittest.repository;

import com.github.yoruhinda.recruittest.models.Home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeRepository {
    private Connection connection;

    public HomeRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveHome(Home home) {
        String insertSQL = "INSERT INTO home (player, name, world, x, y, z) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, home.getPlayer().toString());
            preparedStatement.setString(2, home.getName());
            preparedStatement.setString(3, home.getWorld());
            preparedStatement.setDouble(4, home.getX());
            preparedStatement.setDouble(5, home.getY());
            preparedStatement.setDouble(6, home.getZ());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Home> findAllHomes(UUID playerUUID){
        String selectSQL = "SELECT * FROM home WHERE player = ?";
        List<Home> homeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, playerUUID.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Home home = new Home(resultSet.getString("name"), UUID.fromString(resultSet.getString("player")), resultSet.getString("world"), resultSet.getFloat("x"), resultSet.getFloat("y"), resultSet.getFloat("z"));
                homeList.add(home);
            }
            return homeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Home findHome(UUID playerUUID, String homeName){
        String selectSQL = "SELECT * FROM home WHERE player = ? AND name = ?";
        Home home = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, playerUUID.toString());
            preparedStatement.setString(2, homeName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                home = new Home(resultSet.getString("name"), UUID.fromString(resultSet.getString("player")), resultSet.getString("world"), resultSet.getFloat("x"), resultSet.getFloat("y"), resultSet.getFloat("z"));
            }
            return home;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return home;
    }

    public void updateHome(UUID playerUUID, String homeName, String world, double x, double y, double z){
        String updateSQL = "UPDATE home SET world = ?, x = ?, y = ?, z = ? WHERE player = ? AND name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, world);
            preparedStatement.setDouble(2, x);
            preparedStatement.setDouble(3, y);
            preparedStatement.setDouble(4, z);
            preparedStatement.setString(5, playerUUID.toString());
            preparedStatement.setString(6, homeName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHome(UUID playerUUID, String homeName){
        String updateSQL = "DELETE FROM home WHERE player = ? AND name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, playerUUID.toString());
            preparedStatement.setString(2, homeName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
