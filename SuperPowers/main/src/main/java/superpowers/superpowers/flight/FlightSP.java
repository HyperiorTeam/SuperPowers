package superpowers.superpowers.flight;

import org.bukkit.entity.Player;

import superpowers.main.SuperPowers;
import superpowers.superpowers.SuperPower;
import superpowers.superpowers.flight.tasks.Flight_ExecuteTask;

public class FlightSP extends SuperPower {

	public FlightSP(Player p) {
		
		super(p);
		
	}

	@Override
	public boolean execute(Player p) {
		
		p.setAllowFlight(true);
		
		new Flight_ExecuteTask(p).runTaskLater(SuperPowers.getInstance(), 10 * 20);
		
		return true;
		
	}
	
	
	
}
