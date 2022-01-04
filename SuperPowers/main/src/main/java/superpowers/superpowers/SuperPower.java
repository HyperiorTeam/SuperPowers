package superpowers.superpowers;

import org.bukkit.entity.Player;

public abstract class SuperPower {
	
	private Player p;
	private SuperPowersEnum type;
	
	public SuperPower(Player p, SuperPowersEnum type) {
		
		this.p = p;
		this.type = type;
		
	}
	
	
	//returns true if execute was successfull
	public abstract boolean execute(Player p);
	
	public Player getPlayer() {
		
		return p;
		
	}
	
	public SuperPowersEnum getType() {
		
		return type;
		
	}
	
}
