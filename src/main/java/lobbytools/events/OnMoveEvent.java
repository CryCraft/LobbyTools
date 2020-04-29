package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import papertools.main.Papertools;

public class OnMoveEvent implements Listener {
	private Papertools api;

	public OnMoveEvent(Papertools api) {
		this.api = api;
	}

	@EventHandler
	public void onMoveEvent(PlayerMoveEvent event) {
		if (event.getTo().getBlockY() < 0) {
			this.api.sendPlayerTo.spawn(event.getPlayer());
		}
	}
}