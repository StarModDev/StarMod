package org.starmod;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.starmod.api.Server;
import org.starmod.api.entity.Player;
import org.starmod.net.NetworkServer;

public class ModServer implements Server {

	public static void main(String[] args) {
		new ModServer();
	}

	private final NetworkServer networkServer;
	private InetSocketAddress address;
    private final Config config;
	private final List<Player> players = new ArrayList<>();
	
	public ModServer() {
        this.config = ConfigFactory.load("server");
        try {
            String address = this.config.getString("address");
            int port = this.config.getInt("port");
            this.address = new InetSocketAddress(InetAddress.getByName(address), port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.networkServer = new NetworkServer(this, this.address);
		this.networkServer.run();
	}

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    public Player getPlayer(UUID uniqueId) {
        for (Player player : players) {
            if (player.getUniqueId().equals(uniqueId))
                return player;
        }
        return null;
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

	public Config getConfig() {
		return this.config;
	}

}
