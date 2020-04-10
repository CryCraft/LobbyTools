package lobbytools;

import org.bukkit.plugin.java.JavaPlugin;

import lobbytools.commands.NPCCommand;
import lobbytools.commands.TestCommand;
import lobbytools.events.OnJoinEvent;
import lobbytools.npc.NPCManager;

public class App extends JavaPlugin {
	public NPCManager npcManager;

	public App() {
		this.npcManager = new NPCManager(this);
	}

	public void onEnable() {
		this.getLogger().info("LobbyTools enabled!");

		this.saveDefaultConfig();

		this.getCommand("npc").setExecutor(new NPCCommand(this));
		this.getCommand("test").setExecutor(new TestCommand(this));

		this.getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);

		this.npcManager.createAll();
	}

	public void onDisable() {
		this.npcManager.deleteAll();
	}
}

