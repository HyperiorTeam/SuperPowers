package superpowers.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import superpowers.commands.SuperPowerCommands;
import superpowers.events.SuperPowersEvent;

public class SuperPowers extends JavaPlugin {
	
	private static SuperPowers instance;
	
	public void onEnable() {
		
		instance = this;
		
		registerCommands();
		registerEvents();
		
		
		
	}
	
	private void registerCommands() {
		
		getCommand("superpowers").setExecutor(new SuperPowerCommands());
		getCommand("superpowers").setTabCompleter(new SuperPowerCommands());
		
	}
	
	private void registerEvents() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new SuperPowersEvent(), this);
		
	}
	
	public static SuperPowers getInstance() {
		
		return instance;
		
	}
	
}
