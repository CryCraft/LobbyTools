package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnInventoryClick implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!event.getWhoClicked().hasPermission("lobbytools.overwrite")) {
			event.setCancelled(true);
		}
	}
}