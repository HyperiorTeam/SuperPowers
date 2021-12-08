package superpowers.superpowers.knockout;

import org.bukkit.Bukkit;
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
import superpowers.superpowers.knockout.tasks.KnockOutTask;

public class KnockOutSP extends SuperPower {
	
	public KnockOutSP(Player p) {
		
		super(p);
		
	}
	
	public void execute(Player p) {
		
		PlayerAnim();
		
		//GroundEffect called in the task (KnockOutTask.java)
		
	}
	
	public void PlayerAnim() {
		
		getPlayer().setVelocity(new Vector(0, 1, 0).multiply(1.5));
		
		new KnockOutTask(this).runTaskLater(SuperPowers.getInstance(), 20);
		
	}
	
	@SuppressWarnings("deprecation")
	public void GroundEffect() {
		
		int radius = 3;
		
		for(int x = -radius; x <= radius; x++) {
			
			for(int z = -radius; z <= radius; z++) {
				
				if(x == 0 && z == 0) continue;
				
				boolean cancelBlocks = false;
				
				for(int y = 5; y >= 0; y--) {
					
					Location l = getPlayer().getLocation().add(x, -1 + y, z);
					Block b = l.getBlock();
					
					EntityEffect(l);
					
					if(cancelBlocks) continue;
					
					if(b.getType().equals(Material.AIR) ||
							b.getType().equals(Material.WATER) ||
							b.getType().equals(Material.LAVA)) continue;
					
					FallingBlock e = l.getWorld().spawnFallingBlock(l, b.getType(), b.getData());
					e.setVelocity(new Vector(0, 1, 0).multiply(0.75));
					
					new BukkitRunnable() {
						
						public void run() {
							
							if((int) e.getLocation().getY() == (int) b.getLocation().getY()) {
								
								b.getLocation().getBlock().setType(e.getMaterial());
								
								if(SuperPowers.getInstance().getServer().getVersion().contains("1.8")) {
									
									
									
								}else{
									
									b.getLocation().getBlock().setBlockData(e.getBlockData());
								
								}
								
								e.teleport(new Location(e.getLocation().getWorld(),
										e.getLocation().getX(),
										0,
										e.getLocation().getZ()));
								
								cancel();
								
							}
							
						}
						
					}.runTaskTimer(SuperPowers.getInstance(), 1, 1);
					
					b.setType(Material.AIR);
					
					KSPFallingBlocksManager.add(e, l);
					
					cancelBlocks = true;
					
				}
				
			}
			
		}
		
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
