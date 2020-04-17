package lobbytools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import lobbytools.App;
import net.md_5.bungee.api.ChatColor;

public class LobbyToolsCommand implements CommandExecutor {
	private App plugin;

	public LobbyToolsCommand(App plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("lobbytools.command.lobbytools")) {
			sender.sendMessage(ChatColor.RED + "You do not have the permission to use that command!");
			return true;
		}
		
		if (command.getName().equalsIgnoreCase("lobbytools")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					this.plugin.npcManager.deleteAll();
					this.plugin.reloadConfig();
					this.plugin.npcManager.createAll();
				}
			}
		}
		return true;
	}
}