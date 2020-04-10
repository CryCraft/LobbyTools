package lobbytools.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import lobbytools.App;
import lobbytools.npc.NPC;
import lobbytools.npc.SkinUpdater;
import lobbytools.packetreader.PacketReader;

public class OnJoinEvent implements Listener {

	private App plugin;

	public OnJoinEvent(App plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PacketReader pr = new PacketReader(player, this.plugin);
		pr.inject();

		for (NPC npc : plugin.npcManager.getList()) {
			npc.show(player);
		}
		
		new SkinUpdater(this.plugin).update(player, 200);
	}
}