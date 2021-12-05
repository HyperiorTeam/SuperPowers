package superpowers.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import superpowers.superpowers.SuperPower;

public class SuperPowersEvent implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		final ItemStack item = e.getItem();
		
		//ItemStack checks
		try {
			//replaceAll("§", "") used to get lore without the hidden functionality
			if(!item.getItemMeta().getLore().get(0).replaceAll("§", "").contains("superpower: ")) return;
			
		}catch(NullPointerException exc) {return;}
		
		//SuperPower check
		SuperPower superPower = getSuperPower(item);
		if(superPower == null) return;
		
		//SuperPower execution
		Player p = e.getPlayer();
		superPower.execute(p);
		
		p.getInventory().remove(item);
		
		e.setCancelled(true);
		
	}
	
	private SuperPower getSuperPower(ItemStack item) {
		
		//replaceAll("§", "") used to get lore without the hidden functionality
		String className = 
				"superpowers.superpowers." +
				item.getItemMeta().getLore().get(0).replaceAll("§", "").replace("superpower: ", "") +
				"SP";
		
		try {
			
			return (SuperPower) Class.forName(className).getDeclaredConstructor().newInstance();
			
		}
		catch (ClassNotFoundException exc) {return null;}
		catch (InstantiationException exc) {return null;}
		catch (IllegalAccessException exc) {return null;}
		catch (IllegalArgumentException exc) {return null;}
		catch (InvocationTargetException exc) {return null;}
		catch (NoSuchMethodException exc) {return null;}
		catch (SecurityException exc) {return null;}
		
	}
	
}
