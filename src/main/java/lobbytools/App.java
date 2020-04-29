package lobbytools;

import lobbytools.inventories.ServersGUI;
import org.bukkit.plugin.java.JavaPlugin;

import lobbytools.commands.LobbyToolsCommand;
import lobbytools.commands.ServersCommand;
import lobbytools.commands.SpawnCommand;
import lobbytools.events.OnDropEvent;
import lobbytools.events.OnInventoryClick;
import lobbytools.events.OnJoinEvent;
import lobbytools.events.OnMoveEvent;
import lobbytools.events.OnSwapHandEvent;
import lobbytools.events.OnUseEvent;
import papertools.main.Papertools;

public class App extends JavaPlugin {
	public Papertools api;
	
	public App() {
		this.api = (Papertools) this.getServer().getPluginManager().getPlugin("Papertools");
	}

	public void onEnable() {
		this.getLogger().info("LobbyTools enabled!");

		this.saveDefaultConfig();

		this.getCommand("servers").setExecutor(new ServersCommand(this.api));
		this.getCommand("spawn").setExecutor(new SpawnCommand(this.api));
		this.getCommand("lobbytools").setExecutor(new LobbyToolsCommand(this));


		this.getServer().getPluginManager().registerEvents(new OnDropEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
		this.getServer().getPluginManager().registerEvents(new OnSwapHandEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OnUseEvent(this.api), this);
		this.getServer().getPluginManager().registerEvents(new OnMoveEvent(this.api), this);
		this.getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		this.api.guiManager.addGui("Servers", new ServersGUI(this.api));
	}

	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage("LobbyTools disabled");
	}
}

