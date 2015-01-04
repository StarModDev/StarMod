package org.starmod;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.starmod.api.Player;
import org.starmod.api.Server;
import org.starmod.net.NetworkServer;
import org.starmod.util.ServerConfig;

public class ModServer implements Server {

	public static void main(String[] args) {
		new ModServer();
	}

	private final NetworkServer networkServer;

	private InetSocketAddress address;

	private final ServerConfig config;

	private List<Player> players = new ArrayList<>();
	
	public ModServer() {

		this.config = new ServerConfig(new File("server.cfg"));

		try {
			this.address = new InetSocketAddress(InetAddress.getByName(config.getString("address")), config.getInt("port"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		this.networkServer = new NetworkServer(this, this.address);
		this.networkServer.run();

	}

	public Player[] getPlayers() {
		return players.toArray(new Player[players.size()]);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	@Override
	public InetSocketAddress getAddress() {
		return address;
	}

	@Override
	public void sendServerMessage(String message) {
		networkServer.sendMessage(message);
	}

	@Override
	public void shutdown() {
		networkServer.shutdown();
	}

	public ServerConfig getConfig() {
		return config;
	}
}
