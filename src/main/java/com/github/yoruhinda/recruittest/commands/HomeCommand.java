package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    public HomeCommand(HomeRepository homeRepository) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){

        }
        return false;
    }
}
