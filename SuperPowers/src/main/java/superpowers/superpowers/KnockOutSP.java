package superpowers.superpowers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import superpowers.main.SuperPowers;

public class KnockOutSP implements SuperPower {
	
	public void execute(Player p) {
		
		p.setVelocity(new Vector(0, 1, 0).multiply(1.5));
		
		new BukkitRunnable() {
			
			public void run() {
				
				p.setVelocity(new Vector(0, -1, 0).multiply(5));
				
				new BukkitRunnable() {
					
					@SuppressWarnings("deprecation")
					public void run() {
						
						p.setNoDamageTicks(100);
						if(p.isOnGround()) {
							
							p.setNoDamageTicks(20);
							
							cancel();
							
						}
						
					}
					
				}.runTaskTimerAsynchronously(SuperPowers.getInstance(), 1, 1);
				
			}
			
		}.runTaskLater(SuperPowers.getInstance(), 20);
		
	}
	
}
