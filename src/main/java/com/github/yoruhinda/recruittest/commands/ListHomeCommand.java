package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListHomeCommand implements CommandExecutor {
    public ListHomeCommand(HomeRepository homeRepository) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
