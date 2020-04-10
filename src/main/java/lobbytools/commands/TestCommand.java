package lobbytools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import lobbytools.App;

public class TestCommand implements CommandExecutor {
	private App plugin;

	public TestCommand(App plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("test")) {
			Player player = (Player) sender;
			Vector vector = player.getLocation().toVector();
			plugin.getConfig().set("test.vector", vector);
			plugin.saveConfig();
		}
		return true;
	}
}