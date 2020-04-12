package lobbytools.npc;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import lobbytools.App;

public class NPCBehavior {
	public static void openGui(Player player, Inventory inv, App plugin){
		plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
			@Override
			public void run() {
				player.openInventory(inv);
			}
		});
	}
}