package superpowers.superpowers.knockout.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import superpowers.main.SuperPowers;
import superpowers.superpowers.knockout.KnockOutSP;

public class KnockOutTaskExecute extends BukkitRunnable {
	
	private KnockOutSP knockout;
	
	public KnockOutTaskExecute(KnockOutSP knockout) {
		
		this.knockout = knockout;
		
	}
	
	@Override
	public void run() {
		
		Player p = knockout.getPlayer();
		
		p.setVelocity(new Vector(0, -1, 0).multiply(5));
		
		new BukkitRunnable() {
			
			@SuppressWarnings("deprecation")
			public void run() {
				
				p.setNoDamageTicks(100);
				if(p.isOnGround()) {
					
					p.setNoDamageTicks(20);
					
					knockout.LandEffect();
					
					cancel();
					
				}
				
			}
			
		}.runTaskTimer(SuperPowers.getInstance(), 0, 0);
		
	}
	
}
