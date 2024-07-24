package com.github.yoruhinda.recruittest.listeners;

import com.github.yoruhinda.recruittest.Main;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WindChargeListener implements Listener {
    private Main main = Main.getInstance();
    private FileConfiguration config = main.getConfig();

    @EventHandler
    public void windCharge(ProjectileLaunchEvent event) {
        int wind_force = config.getInt("wind-force");
        int wind_velocity = config.getInt("wind-velocity");
        if (event.getEntity() instanceof WindCharge windCharge) {
            if (windCharge.getShooter() instanceof Player player) {
                windCharge.setVelocity(windCharge.getAcceleration().multiply(wind_velocity));
                windCharge.setYield(wind_force);
                if(config.getBoolean("wind-particles")){
                    particles(windCharge);
                }
            }
        }
    }

    private void particles(Entity entity) {
        Particle particle = Particle.valueOf(config.getString("wind-particles-effect"));
        new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = entity.getLocation();
                entity.getWorld().spawnParticle(particle, loc, 1);
                if(entity.isDead()) {
                    this.cancel();
                }
            }

        }.runTaskTimer(main, 0, 1);
    }
}
