package lobbytools.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import org.bukkit.entity.Player;

import lobbytools.App;

public class SendPlayerTo {
	public static void server(Player player, String server, App plugin) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}	
}