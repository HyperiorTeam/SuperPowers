package superpowers.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import superpowers.enums.SuperPowersEnum;

public class SuperPowerCommands implements TabExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		//args length check
		if(args.length == 0) return false;
		
		switch(args[0].toLowerCase()) {
		
		case "giveitem":
			
			//args length check
			if(args.length != 3) return false;
			
			//Player check
			Player p = Bukkit.getPlayerExact(args[1]);
			if(p == null) return false;
			
			String spName = args[2];
			try {
				
				spName = //Gets the name with the right format
						SuperPowersEnum.valueOf(spName.toUpperCase()).getName();
				
			}catch(NullPointerException exc) {return false;}
			
			giveItem(p, spName);
			
			break;
		
		}
		
		return false;
		
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		
		
		return null;
		
	}
	
	private void giveItem(Player p, String spName) {
		
		ItemStack item = new ItemStack(Material.GHAST_TEAR);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
		meta.setDisplayName(ChatColor.GREEN + spName);
		
		String hiddenlore = "";
		for (char c : ("superpower: " + spName).toCharArray()) hiddenlore += ChatColor.COLOR_CHAR+""+c;
		
		lore.add(hiddenlore);
		meta.setLore(lore);
		
		item.setItemMeta(meta);
		
		p.getInventory().addItem(item);
		
	}
	
}
