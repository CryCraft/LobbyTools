package lobbytools.main;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class utils {
	public static void giveServerSelector(Player player) {
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta meta = compass.getItemMeta();
		meta.addEnchant(Enchantment.MENDING, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&e&lGame menu"));
		compass.setItemMeta(meta);
		player.getInventory().setItem(4, compass);
		player.getInventory().setHeldItemSlot(4);
	}
}