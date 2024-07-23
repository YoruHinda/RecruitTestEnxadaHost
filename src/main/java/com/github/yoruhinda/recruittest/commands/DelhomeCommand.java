package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelhomeCommand implements CommandExecutor {
    private HomeRepository homeRepository;

    public DelhomeCommand(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 1) {
                player.sendMessage("/delhome HomeName");
                return true;
            }
            if (deleteDefaultHome(args, player)) return true;
            if (deleteHomeByName(args, player)) return true;
        }
        return true;
    }

    private boolean deleteDefaultHome(String[] args, Player player) {
        String defaultHomeName = "home";
        if (args.length == 0) {
            if (homeRepository.findHome(player.getUniqueId(), defaultHomeName) != null) {
                homeRepository.deleteHome(player.getUniqueId(), defaultHomeName);
                return true;
            }
            player.sendMessage("Default Home Not Set!");
            return true;
        }
        return false;
    }

    private boolean deleteHomeByName(String[] args, Player player) {
        String homeName = args[0];
        if (homeRepository.findHome(player.getUniqueId(), homeName) != null) {
            homeRepository.deleteHome(player.getUniqueId(), homeName);
            return true;
        }
        player.sendMessage("Home Not Set!");
        return false;
    }
}
