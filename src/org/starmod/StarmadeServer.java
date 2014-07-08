package org.starmod;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.starmod.api.Server;
import org.starmod.api.world.Universe;
import org.starmod.network.NetworkManager;

public class StarmadeServer extends Server {
	
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private Universe universe;
	private InetSocketAddress address;
	private final Logger logger = Logger.getLogger("SM");
	
	public StarmadeServer(int maxPlayers, InetSocketAddress address) {
		universe = new Universe(logger, maxPlayers);
		this.address = address;
	}

	public Runnable r = () -> {
		ServerSocket listener;
		try {
			listener = new ServerSocket();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error opening a socket.", e);
			return;
		}
		try {
			listener.bind(address);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (getUniverse().getRunning().get()) {
			try {
				Socket socket = listener.accept();
				executor.execute(new NetworkManager(socket, universe));
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error accepting socket.", e);
			}
			
		}
		try {
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	@Override
	public Universe getUniverse() {
		return universe;
	}

	@Override
	public InetSocketAddress getServerAddress() {
		return address;
	}
	
}
