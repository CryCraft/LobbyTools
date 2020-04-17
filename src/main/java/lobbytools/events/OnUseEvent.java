package lobbytools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import lobbytools.inventories.ServersGUI;
import net.md_5.bungee.api.ChatColor;

public class OnUseEvent implements Listener {
	@EventHandler
	public void onUseEvent(PlayerInteractEvent event) {
		try {
			if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "" + ChatColor.BOLD + "Server Selector")) {
				event.setCancelled(true);
				event.getPlayer().openInventory(ServersGUI.getServersGUI(event.getPlayer()));
			}
		} catch (Exception e) {}
	}
}