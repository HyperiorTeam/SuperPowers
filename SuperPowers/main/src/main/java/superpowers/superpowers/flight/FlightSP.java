package superpowers.superpowers.flight;

import org.bukkit.entity.Player;

import superpowers.main.SuperPowers;
import superpowers.superpowers.SuperPower;
import superpowers.superpowers.SuperPowersEnum;
import superpowers.superpowers.flight.tasks.Flight_ExecuteTask;

public class FlightSP extends SuperPower {

	public FlightSP(Player p, SuperPowersEnum type) {
		
		super(p, type);
		
	}

	@Override
	public boolean execute(Player p) {
		
		if(p.getAllowFlight() == true) return false;
		
		p.setAllowFlight(true);
		
		new Flight_ExecuteTask(p).runTaskLater(SuperPowers.getInstance(), SuperPowers.getInstance().getConfig().getInt("superpowers.flight.duration") * 20);
		
		return true;
		
	}
	
	
	
}
