package superpowers.superpowers;

import org.bukkit.entity.Player;

public abstract class SuperPower {
	
	private Player p;
	
	public SuperPower(Player p) {
		
		this.p = p;
		
	}
	
	
	//returns true if execute was successfull
	public abstract boolean execute(Player p);
	
	public Player getPlayer() {
		
		return p;
		
	}
	
}
