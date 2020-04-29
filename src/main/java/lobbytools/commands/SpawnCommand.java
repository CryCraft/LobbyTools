package lobbytools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lobbytools.App;
import net.md_5.bungee.api.ChatColor;
import papertools.main.Papertools;

public class SpawnCommand implements CommandExecutor {
	private Papertools api;

	public SpawnCommand(Papertools api) {
		this.api = api;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("lobbytools.command.spawn")) {
			sender.sendMessage(ChatColor.RED + "You do not have the permission to use that command!");
			return true;
		}

		if (command.getName().equalsIgnoreCase("spawn")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				this.api.sendPlayerTo.spawn(player);
			}
		}
		return true;
	}
}