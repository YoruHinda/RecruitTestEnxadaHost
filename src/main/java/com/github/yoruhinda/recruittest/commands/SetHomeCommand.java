package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.models.Home;
import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    private HomeRepository homeRepository;

    public SetHomeCommand(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 1) {
                player.sendMessage("/sethome HomeName!");
                return true;
            }
            if (defaultHomeVerify(args, player)) return true;
            if (createNewHomeAndUpdate(args, player)) return true;
        }
        return true;
    }

    private boolean defaultHomeVerify(String[] args, Player player) {
        String defaultHomeName = "home";
        if (args.length == 0) {
            if (homeRepository.findHome(player.getUniqueId(), defaultHomeName) != null) {
                homeRepository.updateHome(player.getUniqueId(), defaultHomeName, player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                player.sendMessage("Default home Updated!");
                return true;
            }
            homeRepository.saveHome(new Home(defaultHomeName, player.getUniqueId(), player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));
            player.sendMessage("Default Home Created!");
            return true;
        }
        return false;
    }

    private boolean createNewHomeAndUpdate(String[] args, Player player) {
        String homeName = args[0];
        if (homeRepository.findHome(player.getUniqueId(), homeName) != null) {
            homeRepository.updateHome(player.getUniqueId(), homeName, player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
            player.sendMessage(homeName + " home Updated!");
            return true;
        }
        Location playerLocation = player.getLocation();
        Home home = new Home(homeName, player.getUniqueId(), player.getWorld().getName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
        homeRepository.saveHome(home);
        player.sendMessage(homeName + " home Created!");
        return false;
    }
}