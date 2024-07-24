package com.github.yoruhinda.recruittest;

import com.github.yoruhinda.recruittest.commands.*;
import com.github.yoruhinda.recruittest.connection.ConnectionFactory;
import com.github.yoruhinda.recruittest.listeners.WindChargeListener;
import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public final class Main extends JavaPlugin {
    private Connection connection;
    private HomeRepository homeRepository;
    private static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        connection = ConnectionFactory.connection(this);
        homeRepository = new HomeRepository(connection);
        getServer().getPluginCommand("sethome").setExecutor(new SetHomeCommand(homeRepository));
        getServer().getPluginCommand("delhome").setExecutor(new DelHomeCommand(homeRepository));
        getServer().getPluginCommand("homes").setExecutor(new ListHomeCommand(homeRepository));
        getServer().getPluginCommand("home").setExecutor(new HomeCommand(homeRepository));
        getServer().getPluginCommand("wind").setExecutor(new WindCommand());
        getServer().getPluginManager().registerEvents(new WindChargeListener(), this);
        saveDefaultConfig();
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

    public static Main getInstance(){
        return INSTANCE;
    }
}
