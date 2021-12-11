package superpowers.superpowers.lasereyes.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import lasereyes.LaserEyesUtils_v1_8;
import superpowers.main.SuperPowers;

public class LaserEyes_ExecuteTask extends BukkitRunnable {
	
	private Player p;
	public int id;
	
	public LaserEyes_ExecuteTask(Player p) {
		
		this.p = p;
		
		new BukkitRunnable() {
			
			public void run() {
				
				Bukkit.getScheduler().cancelTask(id);
				
			}
			
		}.runTaskLater(SuperPowers.getInstance(), 10 * 20);
		
	}

	public void run() {
		
		id = getTaskId();
		
		for(float i = 1; i <= 20; i+=0.1f) {
			
			Vector direction = p.getLocation().getDirection();
			Location l = p.getEyeLocation().add(direction.multiply(i));
			
			if(SuperPowers.getInstance().getServer().getVersion().contains("1.8")) {
				
				LaserEyesUtils_v1_8.spawnParticle(l, p);
				
			}else {
				
				l.getWorld().spawnParticle(Particle.REDSTONE, l, 1);
				
			}
			
			for(Entity e : l.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5)) {
				
				if(e instanceof LivingEntity) {
					
					LivingEntity le = (LivingEntity) e;
					
					le.damage(1);
					
				}
				
			}
			
		}
		
	}
	
}
