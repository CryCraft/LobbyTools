package lobbytools.npc;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import lobbytools.App;
import net.md_5.bungee.api.ChatColor;

public class NPCManager {
	private ArrayList<NPC> npcs;
	private App plugin;

	//INIT
	public NPCManager(App plugin) {
		this.plugin = plugin;
		this.npcs = new ArrayList<NPC>();
	}


	//NPC FUNCTIONS
	public void createNPC(Player player, String name, UUID uuid, App plugin) {
		NPC npc = new NPC(player.getLocation(), ChatColor.translateAlternateColorCodes('&', name), uuid, plugin);
		npc.create();

		for (Player p : player.getServer().getOnlinePlayers()) {
			npc.show(p);
		}

		this.addNPC(npc);
	}

	public void deleteNPC(Player player, NPC npc) {
		for (int i = 0; i < this.npcs.size(); i++) {
			if (this.npcs.get(i).equals(npc)) {
				for (Player p : player.getServer().getOnlinePlayers()) {
					npc.remove(p);
					this.removeNPC(i);
				}
			}
		}
	}

	// config operators
	public void deleteAll() {
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			for (int i = 0; i < this.npcs.size(); i++) {
				NPC npc = this.npcs.get(i);
				npc.remove(player);
				this.removeNPC(i);
			}
		}
	}
	
	public void createAll() {
		for (String configNPC : this.plugin.getConfig().getConfigurationSection("npcs").getKeys(false)) {
			ConfigurationSection config = this.plugin.getConfig().getConfigurationSection("npcs." + configNPC);

			Double x = config.getDouble("location.x");
			Double y = config.getDouble("location.y");
			Double z = config.getDouble("location.z");

			Float yaw = Float.parseFloat(config.getString("location.yaw"));
			Float pitch = Float.parseFloat(config.getString("location.pitch"));

			Location loc = new Location(this.plugin.getServer().getWorld(config.getString("location.world")), x, y, z, yaw, pitch);
			String name = ChatColor.translateAlternateColorCodes('&', config.getString("name"));
			UUID uuid = UUID.fromString(config.getString("uuid"));
			String texture = config.getString("texture");
			String signature = config.getString("signature");
			String interact = config.getString("interact");

			NPC npc = new NPC(loc, name, uuid, plugin, texture, signature, interact);
			npc.create();
			this.addNPC(npc);

			for (Player player : this.plugin.getServer().getOnlinePlayers()) {
				npc.show(player);
				new SkinUpdater(this.plugin).update(player, 10);
			}
		}
	}


	// LIST OPERATORS
	private void addNPC(NPC npc) {
		this.npcs.add(npc);
	}

	private void removeNPC(int id) {
		this.npcs.remove(id);
	}

	// STATIC LIST OPERATORS
	public NPC getNPC(int index) {
		return this.npcs.get(index);	
	}

	public ArrayList<NPC> getList() {
		return this.npcs;
	}
}