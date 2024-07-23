package com.github.yoruhinda.recruittest.commands;

import com.github.yoruhinda.recruittest.Main;
import com.github.yoruhinda.recruittest.models.Home;
import com.github.yoruhinda.recruittest.repository.HomeRepository;
import com.github.yoruhinda.recruittest.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HomeCommand implements CommandExecutor {
    private HomeRepository homeRepository;
    private Main main = Main.getInstance();
    private FileConfiguration config = main.getConfig();

    public HomeCommand(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
        Cooldown.setupCooldown();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 1) {
                player.sendMessage("/home HomeName");
                return true;
            }
            if(config.getBoolean("cooldown")){
                if(Cooldown.checkCooldown(player)){
                    Cooldown.setCooldown(player, config.getInt("cooldown-time"));
                    if (defaultHomeTeleport(args, player)) return true;
                    if (customHomeTeleport(args, player)) return true;
                }else {
                    player.sendMessage("This is on cooldown for " + Math.round(((Double) Cooldown.cooldowns.get(player.getUniqueId()) - (double) System.currentTimeMillis()) / 1000.0D) + "s.");
                }
            }else {
                if (defaultHomeTeleport(args, player)) return true;
                if (customHomeTeleport(args, player)) return true;
            }
        }
        return true;
    }

    private boolean defaultHomeTeleport(String[] args, Player player) {
        String defaultHomeName = "home";
        if (args.length == 0) {
            Home home = homeRepository.findHome(player.getUniqueId(), defaultHomeName);
            if (home != null) {
                player.teleport(new Location(Bukkit.getWorld(home.getWorld()), home.getX(), home.getY(), home.getZ()));
                if(config.getBoolean("teleport-particle")){
                    sphereParticle(player, Particle.valueOf(config.getString("teleport-particle-effect")));
                }
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
            if(config.getBoolean("teleport-particle")){
                sphereParticle(player, Particle.valueOf(config.getString("teleport-particle-effect")));
            }
            player.sendMessage("Teleport to " + homeName);
            return true;
        }
        player.sendMessage(homeName + " Not Exist!");
        return false;
    }

    private void sphereParticle(Player player, Particle particle) {
        Location location = player.getLocation();
        new BukkitRunnable() {
            double phi = 0;
            @Override
            public void run() {
                phi += Math.PI / 10;
                for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 40) {
                    double r = 1.5;
                    double x = r * Math.cos(theta) * Math.sin(phi);
                    double y = r * Math.cos(phi) + 1.5;
                    double z = r * Math.sin(theta) * Math.sin(phi);
                    location.add(x, y, z);
                    player.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1);
                    location.subtract(x, y, z);
                }
                if (phi > Math.PI) {
                    this.cancel();
                }
            }
        }.runTaskTimer(main, 0, 1);
    }
}
