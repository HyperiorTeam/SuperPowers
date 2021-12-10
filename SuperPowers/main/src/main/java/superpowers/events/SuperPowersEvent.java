package superpowers.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import superpowers.superpowers.SuperPower;

public class SuperPowersEvent implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		
		final ItemStack item = e.getItem();
		
		//ItemStack checks
		try {
			//replaceAll("§", "") used to get lore without the hidden functionality
			if(!item.getItemMeta().getLore().get(0).replaceAll("§", "").contains("superpower: ")) return;
			
		}catch(NullPointerException exc) {return;}
		
		Player p = e.getPlayer();
		
		//SuperPower check
		SuperPower superPower = getSuperPower(item, p);
		if(superPower == null) return;
		
		if(!superPower.execute(p)) return;
		
		p.getInventory().remove(item);
		
		e.setCancelled(true);
		
	}
	
	private SuperPower getSuperPower(ItemStack item, Player p) {
		
		//replaceAll("§", "") used to get lore without the hidden functionality
		String className = 
				"superpowers.superpowers." +
				item.getItemMeta().getLore().get(0).replaceAll("§", "").replace("superpower: ", "").toLowerCase() +
				"." +
				item.getItemMeta().getLore().get(0).replaceAll("§", "").replace("superpower: ", "") +
				"SP";
		
		try {
			
			return (SuperPower) Class.forName(className).getConstructor(Player.class).newInstance(p);
			
		}
		catch (ClassNotFoundException exc) {Bukkit.getConsoleSender().sendMessage("ClassNotFound");return null;}
		catch (InstantiationException exc) {Bukkit.getConsoleSender().sendMessage("InstantiationException");return null;}
		catch (IllegalAccessException exc) {Bukkit.getConsoleSender().sendMessage("IllegalAccessException");return null;}
		catch (IllegalArgumentException exc) {Bukkit.getConsoleSender().sendMessage("IllegalArgumentException");return null;}
		catch (InvocationTargetException exc) {Bukkit.getConsoleSender().sendMessage("InvocationTargetException");return null;}
		catch (NoSuchMethodException exc) {exc.printStackTrace();return null;}
		catch (SecurityException exc) {Bukkit.getConsoleSender().sendMessage("SecurityException");return null;}
		
	}
	
}
