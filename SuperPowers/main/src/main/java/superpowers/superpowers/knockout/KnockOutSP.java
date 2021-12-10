package superpowers.superpowers.knockout;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import superpowers.main.SuperPowers;
import superpowers.superpowers.SuperPower;
import superpowers.superpowers.knockout.tasks.KnockOut_ExecuteTask;
import superpowers.superpowers.knockout.tasks.KnockOut_GroundEffectTask;

public class KnockOutSP extends SuperPower {
	
	public KnockOutSP(Player p) {
		
		super(p);
		
	}
	
	public boolean execute(Player p) {
		
		PlayerAnim();
		
		//LandEffect called in the task (KnockOutTaskExecute.java)
		
		return true;
		
	}
	
	public void PlayerAnim() {
		
		getPlayer().setVelocity(new Vector(0, 1, 0).multiply(1.5));
		
		new KnockOut_ExecuteTask(this).runTaskLater(SuperPowers.getInstance(), 20);
		
	}
	
	public void LandEffect() {
		
		int radius = 3;
		
		for(int x = -radius; x <= radius; x++) {
			
			for(int z = -radius; z <= radius; z++) {
				
				if(x == 0 && z == 0) continue;
				
				for(int y = 5; y >= 0; y--) {
					
					Location l = getPlayer().getLocation().add(x, -1 + y, z);
					
					GroundEffect(l);
					EntityEffect(l);
					
				}
				
			}
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void GroundEffect(Location l) {
		
		Block b = l.getBlock();
		
		if(b.getType().equals(Material.AIR) ||
				b.getType().equals(Material.WATER) ||
				b.getType().equals(Material.LAVA)) return;
		
		FallingBlock e = l.getWorld().spawnFallingBlock(l, b.getType(), b.getData());
		e.setVelocity(new Vector(0, 1, 0).multiply(0.75));
		
		b.setType(Material.AIR);
		
		new KnockOut_GroundEffectTask(b, e).runTaskTimer(SuperPowers.getInstance(), 20, 0);
		
	}
	
	private void EntityEffect(Location l) {
		
		
		l.add(0, 1, 0);
		
		for(Entity e : l.getWorld().getNearbyEntities(l, 1, 1, 1)) {
			
			if(e.equals(getPlayer())) continue;
			if(e instanceof FallingBlock) continue;
			
			e.setVelocity(new Vector(0, 1, 0).multiply(1.5));
			
			new BukkitRunnable() {
				
				public void run() {
					
					e.setFallDistance(10);
					
					if(e.isOnGround()) cancel();
					
				}
				
			}.runTaskTimerAsynchronously(SuperPowers.getInstance(), 0, 0);
			
		}
		
		l.add(0, -1, 0);
		
	}
	
}
