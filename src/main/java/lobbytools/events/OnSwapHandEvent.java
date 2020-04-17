package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class OnSwapHandEvent implements Listener {
	@EventHandler
	public void onSwapHandEvent(PlayerSwapHandItemsEvent event) {
		if (!event.getPlayer().hasPermission("lobbytools.overwrite")) {
			event.setCancelled(true);
		}
	}
}