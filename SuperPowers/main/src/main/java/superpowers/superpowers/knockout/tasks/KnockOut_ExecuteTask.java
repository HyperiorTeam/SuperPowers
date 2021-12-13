package superpowers.superpowers.knockout.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import superpowers.main.SuperPowers;
import superpowers.superpowers.knockout.KnockOutSP;

public class KnockOut_ExecuteTask extends BukkitRunnable {
	
	private KnockOutSP knockout;
	
	public KnockOut_ExecuteTask(KnockOutSP knockout) {
		
		this.knockout = knockout;
		
	}
	
	@Override
	public void run() {
		
		Player p = knockout.getPlayer();
		
		p.setVelocity(new Vector(0, -1, 0).multiply(5));
		
		new BukkitRunnable() {
			
			@SuppressWarnings("deprecation")
			public void run() {
				
				int maxTicks = p.getMaximumNoDamageTicks();
				
				p.setMaximumNoDamageTicks(1000);
				p.setNoDamageTicks(1000);
				if(p.isOnGround()) {
					
					p.setMaximumNoDamageTicks(maxTicks);
					p.setNoDamageTicks(20);
					
					knockout.LandEffect();
					
					cancel();
					
				}
				
			}
			
		}.runTaskTimer(SuperPowers.getInstance(), 0, 0);
		
	}
	
}
