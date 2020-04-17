package lobbytools;

import org.bukkit.plugin.java.JavaPlugin;

import lobbytools.commands.LobbyToolsCommand;
import lobbytools.commands.NPCCommand;
import lobbytools.commands.ServersCommand;
import lobbytools.commands.SpawnCommand;
import lobbytools.events.OnDropEvent;
import lobbytools.events.OnInventoryClick;
import lobbytools.events.OnJoinEvent;
import lobbytools.events.OnMoveEvent;
import lobbytools.events.OnSwapHandEvent;
import lobbytools.events.OnUseEvent;
import lobbytools.inventories.ServersGUI;
import lobbytools.npc.NPCManager;
import papertools.api.portal.PortalManager;
import papertools.main.Papertools;

public class App extends JavaPlugin {
	public NPCManager npcManager;
	public PortalManager portalManager;
	public Papertools papertoolsApi;
	
	public App() {
		this.npcManager = new NPCManager(this);
		this.portalManager = new PortalManager(this);
		this.papertoolsApi = (Papertools) this.getServer().getPluginManager().getPlugin("Papertools");
	}

	public void onEnable() {
		this.getLogger().info("LobbyTools enabled!");

		this.saveDefaultConfig();

		this.getCommand("servers").setExecutor(new ServersCommand());
		this.getCommand("spawn").setExecutor(new SpawnCommand(this));
		this.getCommand("npc").setExecutor(new NPCCommand(this));
		this.getCommand("lobbytools").setExecutor(new LobbyToolsCommand(this));


		this.getServer().getPluginManager().registerEvents(new OnDropEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
		this.getServer().getPluginManager().registerEvents(new OnUseEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OnSwapHandEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OnMoveEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new ServersGUI(this), this);
		this.getServer().getPluginManager().registerEvents(this.portalManager, this);

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		this.portalManager.createAllFromConfig(this.getConfig());
		this.npcManager.createAll();
	}

	public void onDisable() {
		this.npcManager.deleteAll();
	}
}

