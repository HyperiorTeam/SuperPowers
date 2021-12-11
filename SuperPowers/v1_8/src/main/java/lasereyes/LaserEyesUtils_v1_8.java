package lasereyes;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class LaserEyesUtils_v1_8 {
	
	public static void spawnParticle(Location l, Player p) {
		
		PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true,
				(float) l.getX(), (float) l.getY(), (float) l.getZ(),
				0, 0, 0,
				255, 0);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(particles);
		
	}
	
}
