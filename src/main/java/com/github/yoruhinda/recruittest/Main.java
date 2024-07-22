package com.github.yoruhinda.recruittest;

import com.github.yoruhinda.recruittest.commands.WindCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginCommand("wind").setExecutor(new WindCommand());
        getLogger().info("Plugin Enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disable");
    }
}
