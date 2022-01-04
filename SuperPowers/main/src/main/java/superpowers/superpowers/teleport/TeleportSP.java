package superpowers.superpowers.teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import superpowers.superpowers.SuperPower;
import superpowers.superpowers.SuperPowersEnum;

public class TeleportSP extends SuperPower {

	public TeleportSP(Player p, SuperPowersEnum type) {
		
		super(p, type);
		
	}
	
	public boolean execute(Player p) {
		
		Block target = p.getTargetBlock(null, 20);
		Block land = target.getRelative(BlockFace.UP);
		
		if(target.getType().equals(Material.AIR) ||
				target.getType().equals(Material.WATER) ||
				target.getType().equals(Material.LAVA)) return false;
		
		if(!land.getType().equals(Material.AIR) ||
				!land.getRelative(BlockFace.UP).getType().equals(Material.AIR)) return false;
		
		Location l = land.getLocation();
		
		l.setYaw(p.getLocation().getYaw());
		l.setPitch(p.getLocation().getPitch());
		
		p.setFallDistance(0);
		p.teleport(l);
		
		return true;
		
	}
	
}
