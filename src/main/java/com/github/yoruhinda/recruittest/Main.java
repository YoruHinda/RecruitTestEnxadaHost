package com.github.yoruhinda.recruittest;

import com.github.yoruhinda.recruittest.commands.DelhomeCommand;
import com.github.yoruhinda.recruittest.commands.HomeCommand;
import com.github.yoruhinda.recruittest.commands.ListHomeCommand;
import com.github.yoruhinda.recruittest.commands.SethomeCommand;
import com.github.yoruhinda.recruittest.connection.ConnectionFactory;
import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public final class Main extends JavaPlugin {
    private Connection connection;
    private HomeRepository homeRepository;

    @Override
    public void onEnable() {
        connection = ConnectionFactory.connection(this);
        homeRepository = new HomeRepository(connection);
        getServer().getPluginCommand("sethome").setExecutor(new SethomeCommand(homeRepository));
        getServer().getPluginCommand("delhome").setExecutor(new DelhomeCommand(homeRepository));
        getServer().getPluginCommand("homes").setExecutor(new ListHomeCommand(homeRepository));
        getServer().getPluginCommand("home").setExecutor(new HomeCommand(homeRepository));
        getLogger().info("Plugin Enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disable");
        try {
            connection.close();
        } catch (SQLException e) {
            getLogger().info("Database Connection Close Failed!");
        }
    }
}
