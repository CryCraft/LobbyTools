package lobbytools.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import lobbytools.App;
import net.md_5.bungee.api.ChatColor;
import papertools.api.gui.GUI;
import papertools.api.utils.SendPlayerTo;

public class ServersGUI implements Listener {
	
	private App plugin;

	public ServersGUI(App plugin) {
		this.plugin = plugin;
	}

	public static Inventory getServersGUI(Player player) {
		GUI gui = new GUI("&e&lServers", 27, player);
		gui.addItem("&e&lCreative", Material.GRASS_BLOCK, 1, 11, "&7creative worlds");
		gui.addItem("&e&lLobby", Material.COMPASS, 1, 13, "&7spawn");
		gui.addItem("&e&lSurvival", Material.GOLDEN_APPLE, 1, 15, "&7survival");
		return gui.getInventory();
	}

	@EventHandler
	public void onItemClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = ServersGUI.getServersGUI(player);
		if(event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&e&lServers"))) {
			event.setCancelled(true);
			if (GUI.compareItem(event.getCurrentItem(), inventory.getItem(11))) {
				SendPlayerTo.server(player, "creative", this.plugin);
			} else if (GUI.compareItem(event.getCurrentItem(), inventory.getItem(13))) {
				player.sendMessage(ChatColor.DARK_RED + "Already connected to this server!");
			} else if (GUI.compareItem(event.getCurrentItem(), inventory.getItem(15))) {
				SendPlayerTo.server(player, "survival", this.plugin);
			}
		}
	}
}