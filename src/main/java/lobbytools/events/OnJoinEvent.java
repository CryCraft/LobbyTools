package lobbytools.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import lobbytools.App;
import lobbytools.npc.NPC;

public class OnJoinEvent implements Listener {

	private App plugin;

	public OnJoinEvent(App plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for (NPC npc : this.plugin.npcManager.getList()) {
			npc.show(player);
		}
	}
}