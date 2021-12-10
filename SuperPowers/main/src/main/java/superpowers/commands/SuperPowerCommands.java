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
		if(args.length != 0) {
			
			switch(args[0].toLowerCase()) {
			
			case "giveitem":
				
				//args length check
				if(args.length != 3) {
					
					sender.sendMessage(ChatColor.RED + "Invalid args length!");
					sender.sendMessage(ChatColor.GREEN + "Usage: " +
							ChatColor.YELLOW + "/sp giveitem [player] [superpower]");
					
					return false;
					
				}
				
				//Player check
				Player p = Bukkit.getPlayerExact(args[1]);
				if(p == null) {
					
					sender.sendMessage(ChatColor.RED + "Uknown Player!");
					
					return false;
					
				}
				
				String spName = args[2];
				try {
					
					spName = //Gets the name with the right format
							SuperPowersEnum.valueOf(spName.toUpperCase()).getName();
					
				}catch(IllegalArgumentException exc) {
					
					sender.sendMessage(ChatColor.RED + "Uknown Superpower!");
					
					return false;
					
				}
				
				giveItem(p, spName);
				
				break;
				
			case "help":
				
				printHelp(sender);
				
				break;
				
			default:
				
				sender.sendMessage(ChatColor.RED + "Uknown command - please use /sp help!");
				
				break;
				
			}
			
		}else {
			
			printHelp(sender);
			
		}
		
		return false;
		
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		List<String> tabComplete = new ArrayList<String>();
		
		if(args.length == 1) {
			
			tabComplete.add("giveitem");
			tabComplete.add("help");
			
		}else if(args.length == 2) {
			
			for(Player p : Bukkit.getOnlinePlayers()) tabComplete.add(p.getName());
			
		}else if(args.length == 3) {
			
			for(SuperPowersEnum superpower : SuperPowersEnum.values()) tabComplete.add(superpower.getName());
			
		}
		
		return tabComplete;
		
	}
	
	private void printHelp(CommandSender sender) {
		
		sender.sendMessage(ChatColor.GREEN + "--- SuperPowers by Stexjy ---" + 
				"\n");
		
		sender.sendMessage(ChatColor.YELLOW + "/sp giveitem [player] [superpower]" + 
				ChatColor.GREEN + " - Gives the superpower item to the player");
		
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
