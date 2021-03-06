package superpowers.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import superpowers.main.SuperPowers;
import superpowers.superpowers.SuperPower;
import superpowers.superpowers.SuperPowersEnum;
import superpowers.tasks.SPCooldown;
import superpowers.utils.HiddenStringUtils;

public class SuperPowersEvent implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		
		Player p = e.getPlayer();
		final ItemStack item = e.getItem();
		
		//ItemStack checks
		try {
			
			if(HiddenStringUtils.extractHiddenString(item.getItemMeta().getLore().get(0)).contains("superpower: ")) return;
			
		}catch(NullPointerException exc) {exc.printStackTrace(); return;}
		
		
		
		//SuperPower check
		SuperPower superPower = getSuperPower(item, p);
		if(superPower == null) return;
		
		//Cooldown check
		if(SPCooldown.isOnCooldown(p.getUniqueId(), superPower.getType())) {
			
			p.sendMessage(ChatColor.DARK_RED + "This superpower is on cooldown!");
			
			return;
			
		}
		
		if(!superPower.execute(p)) return;
		
		new SPCooldown(p.getUniqueId(), superPower.getType()).runTaskTimer(SuperPowers.getInstance(), 0, 20);
		
		e.setCancelled(true);
		
	}
	
	private SuperPower getSuperPower(ItemStack item, Player p) {
		
		SuperPowersEnum type = SuperPowersEnum.valueOf(
				HiddenStringUtils.extractHiddenString(item.getItemMeta().getLore().get(0)).replace("superpower: ", "").toUpperCase());
		
		//replaceAll("?", "") used to get lore without the hidden functionality
		String className = 
				"superpowers.superpowers." +
				type.toString().toLowerCase() +
				"." +
				type.getName() +
				"SP";
		
		try {
			
			return (SuperPower) Class.forName(className).getConstructor(Player.class, SuperPowersEnum.class).newInstance(p, type);
			
		}
		catch (ClassNotFoundException exc) {Bukkit.getConsoleSender().sendMessage("ClassNotFound");return null;}
		catch (InstantiationException exc) {Bukkit.getConsoleSender().sendMessage("InstantiationException");return null;}
		catch (IllegalAccessException exc) {Bukkit.getConsoleSender().sendMessage("IllegalAccessException");return null;}
		catch (IllegalArgumentException exc) {Bukkit.getConsoleSender().sendMessage("IllegalArgumentException");return null;}
		catch (InvocationTargetException exc) {Bukkit.getConsoleSender().sendMessage("InvocationTargetException");return null;}
		catch (NoSuchMethodException exc) {Bukkit.getConsoleSender().sendMessage("NoSuchMethoException");return null;}
		catch (SecurityException exc) {Bukkit.getConsoleSender().sendMessage("SecurityException");return null;}
		
	}
	
	public static String unTranslateAlternateColorCodes(String text) {
	    char[] array = text.toCharArray();
	    for (int i = 0; i < array.length - 1; i++) {
	    	SuperPowers.getInstance().getLogger().info(array[i] + "");
	        if (array[i] == ChatColor.COLOR_CHAR && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(array[i + 1]) != -1) {
	            array[i] = '&';
	            array[i + 1] = Character.toLowerCase(array[i + 1]);
	        }
	    }
	    return new String(array);
	}
	
}
