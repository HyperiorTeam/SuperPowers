package superpowers.superpowers.flight.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import superpowers.main.SuperPowers;

public class Flight_ExecuteTask extends BukkitRunnable {
	
	private Player p;
	
	public Flight_ExecuteTask(Player p) {
		
		this.p = p;
		
	}
	
	public void run() {
		
		p.setAllowFlight(false);
		
		new BukkitRunnable() {
			
			@SuppressWarnings("deprecation")
			public void run() {
				
				p.setNoDamageTicks(1000);
				if(p.isOnGround()) {
					
					p.setNoDamageTicks(20);
					
					cancel();
					
				}
				
			}
			
		}.runTaskTimer(SuperPowers.getInstance(), 0, 0);
		
	}
	
}
