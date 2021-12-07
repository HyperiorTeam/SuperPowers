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
			//replaceAll("�", "") used to get lore without the hidden functionality
			if(!item.getItemMeta().getLore().get(0).replaceAll("�", "").contains("superpower: ")) return;
			
		}catch(NullPointerException exc) {return;}
		
		Player p = e.getPlayer();
		
		//SuperPower check
		SuperPower superPower = getSuperPower(item, p);
		if(superPower == null) return;
		
		superPower.execute(p);
		
		p.getInventory().remove(item);
		
		e.setCancelled(true);
		
	}
	
	private SuperPower getSuperPower(ItemStack item, Player p) {
		
		//replaceAll("�", "") used to get lore without the hidden functionality
		String className = 
				"superpowers.superpowers." +
				item.getItemMeta().getLore().get(0).replaceAll("�", "").replace("superpower: ", "") +
				"SP";
		
		try {
			
			return (SuperPower) Class.forName(className).getConstructor(Player.class).newInstance(p);
			
		}
		catch (ClassNotFoundException exc) {Bukkit.getConsoleSender().sendMessage("ciao1r");return null;}
		catch (InstantiationException exc) {Bukkit.getConsoleSender().sendMessage("ciao2r");return null;}
		catch (IllegalAccessException exc) {Bukkit.getConsoleSender().sendMessage("ciao4r");return null;}
		catch (IllegalArgumentException exc) {Bukkit.getConsoleSender().sendMessage("ciaor");return null;}
		catch (InvocationTargetException exc) {Bukkit.getConsoleSender().sendMessage("ciao45r");return null;}
		catch (NoSuchMethodException exc) {exc.printStackTrace();return null;}
		catch (SecurityException exc) {Bukkit.getConsoleSender().sendMessage("ci5ao4r");return null;}
		
	}
	
}