package superpowers.superpowers.knockout.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import superpowers.main.SuperPowers;

public class KnockOutUtils {
	
	private static List<UUID> freezed = new ArrayList<UUID>();
	
	public static void setFreezed(UUID uuid) {
		
		freezed.add(uuid);
		
		new BukkitRunnable() {
			
			public void run() {
				
				freezed.remove(uuid);
				
			}
			
		}.runTaskLater(SuperPowers.getInstance(), 2 * 20);
		
	}
	
	public static boolean isFreezed(UUID uuid) {
		
		return freezed.contains(uuid);
		
	}
	
}
