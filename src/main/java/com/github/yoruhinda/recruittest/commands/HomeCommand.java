package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.models.Home;
import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    private HomeRepository homeRepository;

    public HomeCommand(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 1) {
                player.sendMessage("/home HomeName");
                return true;
            }
            if (defaultHomeTeleport(args, player)) return true;
            if (customHomeTeleport(args, player)) return true;
        }
        return true;
    }

    private boolean defaultHomeTeleport(String[] args, Player player) {
        String defaultHomeName = "home";
        if (args.length == 0) {
            Home home = homeRepository.findHome(player.getUniqueId(), defaultHomeName);
            if (home != null) {
                player.teleport(new Location(Bukkit.getWorld(home.getWorld()), home.getX(), home.getY(), home.getZ()));
                player.sendMessage("Teleport to " + defaultHomeName);
                return true;
            }
            player.sendMessage("Default Home not exist!");
            return true;
        }
        return false;
    }

    private boolean customHomeTeleport(String[] args, Player player) {
        String homeName = args[0];
        Home home = homeRepository.findHome(player.getUniqueId(), homeName);
        if (home != null) {
            player.teleport(new Location(Bukkit.getWorld(home.getWorld()), home.getX(), home.getY(), home.getZ()));
            player.sendMessage("Teleport to " + homeName);
            return true;
        }
        player.sendMessage(homeName + " Not Exist!");
        return false;
    }
}
