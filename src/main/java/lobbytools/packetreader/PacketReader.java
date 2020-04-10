package lobbytools.packetreader;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lobbytools.App;
import lobbytools.npc.NPC;
import net.minecraft.server.v1_15_R1.Packet;

public class PacketReader {

	private Player player;
	private Channel channel;
	private App plugin;

	public PacketReader(Player player, App plugin) {
		this.player = player;
		this.plugin = plugin;
	}

	public void inject() {
		CraftPlayer player = (CraftPlayer) this.player;

		MessageToMessageDecoder<Packet<?>> messageDecoder = new MessageToMessageDecoder<Packet<?>>() {
			@Override
			protected void decode(ChannelHandlerContext arg0, Packet<?> packet, List<Object> arg2) throws Exception {
				arg2.add(packet);
				readPacket(packet);
			}
		};

		this.channel = player.getHandle().playerConnection.networkManager.channel;
		this.channel.pipeline().addAfter("decoder", "PacketInjector", messageDecoder);
	}
	
	public void uninject() {
		if (this.channel.pipeline().get("PacketInjector") != null) {
			this.channel.pipeline().remove("PacketInjector");
		}
	}

	public void readPacket(Packet<?> packet) {
		if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
			int id = (Integer) getValue(packet, "a");
			for (NPC npc : this.plugin.npcManager.getList()) {
				if (npc.getEntityId() == id) {
					if (getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) {
						npc.run("ATTACK", player);
					} else if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
						if (getValue(packet, "d").toString().equalsIgnoreCase("MAIN_HAND")) {
							npc.run("INTERACT", player);
						}
					}
				}
			}
		}
	}

	public void setValue(Object obj, String name, Object value) {
		try {
			Field field = obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {}
	}

	public Object getValue(Object obj, String name) {
		try {
			Field field = obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {}
		return null;
	}
}