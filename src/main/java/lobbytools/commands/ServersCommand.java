package lobbytools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lobbytools.App;
import lobbytools.inventories.ServersGUI;

public class ServersCommand implements CommandExecutor {
	// private App plugin;

	public ServersCommand(App plugin) {
		// this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("servers")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.openInventory(ServersGUI.getServersGUI(player));
			}
		}
		return true;
	}
}