package lobbytools.npc;

import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import lobbytools.App;
import lobbytools.inventories.ServersGUI;

import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;

import net.minecraft.server.v1_15_R1.DataWatcher;
import net.minecraft.server.v1_15_R1.DataWatcherObject;
import net.minecraft.server.v1_15_R1.DataWatcherRegistry;
import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.MinecraftServer;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_15_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import net.minecraft.server.v1_15_R1.PlayerInteractManager;
import net.minecraft.server.v1_15_R1.WorldServer;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class NPC {

	public Location loc;
	public String name;
	public UUID uuid;

	private App plugin;
	private GameProfile gameProfile;
	private EntityPlayer entityPlayer;
	private String texture;
	private String signature;
	private String interact;

	public NPC(Location loc, String name, UUID uuid, App plugin) {
		this.loc = loc;
		this.name = name;
		this.uuid = uuid;
		this.plugin = plugin;
		this.texture = "";
		this.signature = "";
		this.interact = "";
	}

	public NPC(Location loc, String name, UUID uuid, App plugin, String texture, String signature, String interact) {
		this.loc = loc;
		this.name = name;
		this.uuid = uuid;
		this.plugin = plugin;
		this.texture = texture;
		this.signature = signature;
		this.interact = interact;
	}
	
	public void create() {
		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) this.loc.getWorld()).getHandle();

		this.gameProfile = new GameProfile(this.uuid, this.name);
		this.gameProfile.getProperties().put("textures", new Property("textures", this.texture, this.signature));

		this.entityPlayer = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));
		this.entityPlayer.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
	}
	
	public void show(Player player) {

		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, this.entityPlayer));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(this.entityPlayer));
		connection.sendPacket(new PacketPlayOutEntityHeadRotation(this.entityPlayer, (byte) ((loc.getYaw() * 256.0F) / 360.0F)));
		
		DataWatcher watcher = this.entityPlayer.getDataWatcher();
		watcher.set(new DataWatcherObject<Byte>(16, DataWatcherRegistry.a), new Byte((byte) 127));
		connection.sendPacket(new PacketPlayOutEntityMetadata(this.entityPlayer.getId(), watcher, true));
	}

	public void remove(Player player) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutEntityDestroy(this.entityPlayer.getId()));
	}

	public void run(String label, Player player) {
		if (this.interact.equals("ServersGUI")) {
			NPCBehavior.openGui(player, ServersGUI.getServersGUI(player), this.plugin);
		}

		// if (label.equalsIgnoreCase("INTERACT")) {
			
		// } else if (label.equalsIgnoreCase("ATTACK")) {
		// 	player.sendMessage("ATTACK");
		// }
	}

	public void removeFromTab(Player player) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, this.entityPlayer));
	}

	public int getEntityId() {
		return this.entityPlayer.getId();
	}
}