package lobbytools.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import papertools.main.Papertools;


public class ServersCommand implements CommandExecutor {
	private Papertools api;

	public ServersCommand(Papertools api) {
		this.api = api;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("lobbytools.command.servers")) {
			sender.sendMessage(ChatColor.RED + "You do not have the permission to use that command!");
			return true;
		}

		if (command.getName().equalsIgnoreCase("servers")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.openInventory(this.api.guiManager.getGui("Servers").getInventory());
			}
		}
		return true;
	}
}