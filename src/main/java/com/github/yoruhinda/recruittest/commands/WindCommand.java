package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WindCommand implements CommandExecutor {
    private Main main = Main.getInstance();
    private FileConfiguration config = main.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            boolean set = args[0].equalsIgnoreCase("set");
            if(set && args[1].equalsIgnoreCase("velocity")){
                config.set("wind-velocity", Integer.valueOf(args[2]));
            }
            if(set && args[1].equalsIgnoreCase("force")){
                config.set("wind-force", Integer.valueOf(args[2]));
            }
            if(set && args[1].equalsIgnoreCase("particles")){
                if(args[2].equalsIgnoreCase("true")){
                    config.set("wind-particles", Boolean.valueOf(args[2]));
                    config.set("wind-particles-effect", args[3]);
                }else {
                    config.set("wind-particles", Boolean.valueOf(args[2]));
                }
            }
            main.saveConfig();
        }
        return true;
    }
}
