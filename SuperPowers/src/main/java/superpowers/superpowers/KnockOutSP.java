package superpowers.superpowers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import superpowers.main.SuperPowers;
import superpowers.superpowers.tasks.KnockOutTask;

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
		
		for(int radius = 1; radius <= 3; radius++) {
			
			for(int x = -radius; x <= radius; x++) {
				
				for(int z = -radius; z <= radius; z++) {
					
					if(x == 0 && z == 0) continue;
					
					Location l = getPlayer().getLocation().add(x, -1, z);
					Block b = l.getBlock();
					
					l.getWorld().spawnFallingBlock(l, b.getType(), b.getData())
					.setVelocity(new Vector(0, 1, 0));
					
				}
				
			}
			
		}
		
	}
	
}
