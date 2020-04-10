package lobbytools.npc;

import org.bukkit.entity.Player;

import lobbytools.App;

public class SkinUpdater {
	private App plugin;

	public SkinUpdater(App plugin) {
		this.plugin = plugin;
	}

	public void update(Player player, int duration) {
		this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (NPC npc : plugin.npcManager.getList()) {
					npc.removeFromTab(player);
				}
			}
		}, duration);
	}

}