package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnDropEvent implements Listener{

	@EventHandler
	public void onDropEvent(PlayerDropItemEvent event) {
		if (!event.getPlayer().hasPermission("lobbytools.overwrite")) {
			event.setCancelled(true);
		}
	}
}