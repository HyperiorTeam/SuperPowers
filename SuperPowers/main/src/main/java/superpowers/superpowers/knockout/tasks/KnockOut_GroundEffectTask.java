package superpowers.superpowers.knockout.tasks;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

import knockout.KnockoutUtils_v1_8;
import superpowers.main.SuperPowers;

public class KnockOut_GroundEffectTask extends BukkitRunnable {
	
	Block b;
	FallingBlock e;
	
	public KnockOut_GroundEffectTask(Block b, FallingBlock e) {
		
		this.b = b;
		this.e = e;
		
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		
		if((int) e.getLocation().getY() != (int) b.getLocation().getY()) return;
		
		b.getLocation().getBlock().setType(e.getMaterial());
		
		if(SuperPowers.getInstance().getServer().getVersion().contains("1.8")) {
			
			KnockoutUtils_v1_8.setBlockData(b, e);
			
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
