package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import lobbytools.App;

public class OnMoveEvent implements Listener {
	private App plugin;

	public OnMoveEvent(App plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onMoveEvent(PlayerMoveEvent event) {
		if (event.getTo().getBlockY() < 0) {
			this.plugin.papertoolsApi.sendPlayerTo.spawn(event.getPlayer());
		}
	}
}