package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;
import papertools.main.Papertools;

public class OnUseEvent implements Listener {
	private final Papertools api;

	public OnUseEvent(Papertools api) {
		this.api = api;
	}

	@EventHandler
	public void onUseEvent(PlayerInteractEvent event) {
		try {
			if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "" + ChatColor.BOLD + "Server Selector")) {
				event.setCancelled(true);
				event.getPlayer().openInventory(api.guiManager.getGui("Servers").getInventory());
			}
		} catch (Exception ignored) {}
	}
}