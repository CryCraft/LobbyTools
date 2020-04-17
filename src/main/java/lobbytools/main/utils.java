package lobbytools.main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class utils {
	public static void giveServerSelector(Player player) {
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta compassMeta = compass.getItemMeta();
		compassMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Server Selector");
		compass.setItemMeta(compassMeta);
		player.getInventory().setItem(4, compass);
		player.getInventory().setHeldItemSlot(4);
	}
}