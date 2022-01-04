package superpowers.superpowers.lasereyes;

import org.bukkit.entity.Player;

import superpowers.main.SuperPowers;
import superpowers.superpowers.SuperPower;
import superpowers.superpowers.SuperPowersEnum;
import superpowers.superpowers.lasereyes.tasks.LaserEyes_ExecuteTask;

public class LaserEyesSP extends SuperPower {

	public LaserEyesSP(Player p, SuperPowersEnum type) {
		
		super(p, type);
		
	}

	public boolean execute(Player p) {
		
		new LaserEyes_ExecuteTask(p).runTaskTimer(SuperPowers.getInstance(), 1, 1);
		
		return false;
		
	}
	
}
