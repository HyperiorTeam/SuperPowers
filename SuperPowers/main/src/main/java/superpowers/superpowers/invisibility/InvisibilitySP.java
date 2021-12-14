package superpowers.superpowers.invisibility;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import superpowers.superpowers.SuperPower;

public class InvisibilitySP extends SuperPower {

	public InvisibilitySP(Player p) {
		
		super(p);
		
	}
	
	public boolean execute(Player p) {
		
		/*/ Will be added in the future
		for(Player hide : p.getWorld().getPlayers()) {
			
			hide.hidePlayer(SuperPowers.getInstance(), p);
			
		}/*/
		
		for(PotionEffect effect : p.getActivePotionEffects()) {
			
			if(effect.getType().equals(PotionEffectType.INVISIBILITY)) return false;
			
		}
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10 * 20, 1));
		
		return true;
		
	}
	
}
