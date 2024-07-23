package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.models.Home;
import com.github.yoruhinda.recruittest.repository.HomeRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListHomeCommand implements CommandExecutor {
    private HomeRepository homeRepository;

    public ListHomeCommand(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length >= 1){
                player.sendMessage("/homes");
                return true;
            }
            if(homeRepository.findAllHomes(player.getUniqueId()).isEmpty()){
                player.sendMessage("You Don't have homes set");
                return true;
            }
            showAllHomes(player);
        }
        return true;
    }

    private void showAllHomes(Player player) {
        player.sendMessage("--------------------");
        for (Home home : homeRepository.findAllHomes(player.getUniqueId())) {
            player.sendMessage(home.getName());
        }
        player.sendMessage("--------------------");
    }
}
