package superpowers.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import superpowers.main.SuperPowers;
import superpowers.superpowers.SuperPowersEnum;

public class SPCooldown extends BukkitRunnable {
	
	private static List<SPCooldown> cooldowns = new ArrayList<SPCooldown>();
	
	private UUID uuid;
	private SuperPowersEnum type;
	private int time;
	
	public static boolean isOnCooldown(UUID uuid, SuperPowersEnum type) {
		
		for(SPCooldown cooldown : cooldowns) {
			
			if(!cooldown.getPlayer().equals(uuid)) continue;
			if(!cooldown.getSuperPower().equals(type)) continue;
			
			return true;
			
		}
		
		return false;
		
	}
	
	public SPCooldown(UUID uuid, SuperPowersEnum type) {
		
		this.uuid = uuid;
		this.type = type;
		
		cooldowns.add(this);
		
		time = SuperPowers.getInstance().getConfig().getInt("superpowers." + type.toString().toLowerCase() + ".cooldown");
		
	}
	
	public void run() {
		
		if(time == 0) {
			
			cooldowns.remove(this);
			
			cancel();
			
		}
		
		time--;
		
	}
	
	public UUID getPlayer() {
		
		return uuid;
		
	}
	
	public SuperPowersEnum getSuperPower() {
		
		return type;
		
	}
	
}
