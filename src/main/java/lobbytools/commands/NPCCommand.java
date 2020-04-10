package lobbytools.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lobbytools.App;
import lobbytools.npc.NPC;
import net.md_5.bungee.api.ChatColor;


public class NPCCommand implements CommandExecutor {
	private App plugin;

	public NPCCommand(App plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender,  Command command,  String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (command.getName().equalsIgnoreCase("npc")) {

				// CREATE / DELETE [2 args]
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("preview")) {
						this.plugin.npcManager.createNPC(player, args[1], UUID.randomUUID(), plugin);
					}

					if (args[0].equalsIgnoreCase("hide")) {
						this.plugin.npcManager.deleteNPC(player, this.plugin.npcManager.getNPC(Integer.parseInt(args[1].trim())));
					}
				}

				// LIST
				if (args[0].equalsIgnoreCase("list")) {
					ArrayList<NPC> list = this.plugin.npcManager.getList();
					player.sendMessage(ChatColor.GOLD + "----{ npcs }----");

					for (int i = 0; i < list.size(); i++) {
						player.sendMessage(ChatColor.RED + "" + i + ". " + ChatColor.YELLOW + list.get(i).name);
					}

					player.sendMessage(ChatColor.GOLD + "---------------");
				}

				if (args[0].equalsIgnoreCase("reload")) {
					this.plugin.reloadConfig();
					this.plugin.npcManager.deleteAll();
					this.plugin.npcManager.createAll();
				}
			}
		}

		return true;
	}
		
}