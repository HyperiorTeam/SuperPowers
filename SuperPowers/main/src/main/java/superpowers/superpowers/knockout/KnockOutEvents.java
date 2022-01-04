package superpowers.superpowers.knockout;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import superpowers.superpowers.knockout.utils.KnockOutUtils;

public class KnockOutEvents implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		if(!KnockOutUtils.isFreezed(p.getUniqueId())) return;
		
		p.teleport(e.getFrom());
		
	}
	
}
