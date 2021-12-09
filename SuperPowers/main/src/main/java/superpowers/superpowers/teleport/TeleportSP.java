package superpowers.superpowers.teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import superpowers.superpowers.SuperPower;

public class TeleportSP extends SuperPower {

	public TeleportSP(Player p) {
		
		super(p);
		
	}
	
	public boolean execute(Player p) {
		
		Block b = p.getTargetBlock(null, 20);
		
		if(b.getType().equals(Material.AIR) ||
				b.getType().equals(Material.WATER) ||
				b.getType().equals(Material.LAVA)) return false;
		
		Location l = b.getRelative(BlockFace.UP).getLocation();
		
		l.setYaw(p.getLocation().getYaw());
		l.setPitch(p.getLocation().getPitch());
		
		p.setFallDistance(0);
		p.teleport(l);
		
		return true;
		
	}
	
}
