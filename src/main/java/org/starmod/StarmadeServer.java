package org.starmod;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.starmod.api.Server;
import org.starmod.api.world.Universe;
import org.starmod.net.NetworkServer;

public class StarmadeServer extends Server {
	
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private Universe universe;
	private InetSocketAddress address;
	private final Logger logger = Logger.getLogger("SM");
	private NetworkServer networkServer;

	private List<Player> players = new ArrayList<>();
	
	public StarmadeServer(int maxPlayers, InetSocketAddress address) {
		universe = new Universe(logger, maxPlayers);
		this.address = address;
		this.networkServer = new NetworkServer(address.getPort());
		this.networkServer.run();
	}

	public Player[] getPlayers() {
		return (Player[]) players.toArray();
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	@Override
	public Universe getUniverse() {
		return universe;
	}

	@Override
	public InetSocketAddress getServerAddress() {
		return address;
	}
	
}
