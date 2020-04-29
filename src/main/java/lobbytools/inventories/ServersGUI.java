package lobbytools.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import papertools.api.gui.GuiInventory;
import papertools.api.gui.GuiItem;
import papertools.main.Papertools;

import java.util.ArrayList;

public class ServersGUI extends GuiInventory {

	public ServersGUI(Papertools api) {
		super("&e&lGame menu", 27, api);

		String[] creativeLore = new String[] {"&7Build on a flat or","&7medieval world.", "", "&7Plotworld comming soon!","","&7► Click to join"};
		this.addItem(11, new GuiItem("&e&lCreative", Material.GRASS_BLOCK, 1, false, creativeLore, (player) -> {
			api.sendPlayerTo.server(player, "creative", api);
		}));

		String[] survivalLore = new String[] {"&7Play survival, alone or with friends.","&7Whitelisted!","","&7► Click to join"};
		this.addItem(13, new GuiItem("&e&lSurvival", Material.GOLDEN_APPLE, 1, false, survivalLore, (player) -> {
			api.sendPlayerTo.server(player, "survival", api);
		}));

		String[] dungeonLore = new String[] {"&4Coming soon!"};
		GuiItem dungeonsItem = new GuiItem("&e&lDungeons", Material.IRON_BARS, 1, true, dungeonLore, (player) -> {
			if (player.hasPermission("lobbytools.overwrite")) {
				api.sendPlayerTo.server(player, "dungeons", api);
			} else {
				player.sendMessage(ChatColor.RED + "Dungeons is coming soon!");
			}
		});
		this.addItem(15, dungeonsItem);

	}
}