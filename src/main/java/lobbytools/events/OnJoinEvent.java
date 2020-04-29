package lobbytools.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import lobbytools.App;
import lobbytools.main.utils;

public class OnJoinEvent implements Listener {

	private App plugin;

	public OnJoinEvent(App plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		//server defaults
		utils.giveServerSelector(player);

		if (!player.hasPermission("lobbytools.overwrite")) {
			player.setGameMode(GameMode.ADVENTURE);
		}

		//cosmetics
	}
}