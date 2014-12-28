package org.starmod.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import org.starmod.api.entity.Entity;
import org.starmod.api.entity.Player;
import org.starmod.api.network.StarmadePacket;
import org.starmod.api.world.Universe;

public class NetworkManager extends org.starmod.api.network.NetworkManager {
	private DataInputStream input;
	private DataOutputStream output;

	public NetworkManager(Socket socket, Universe universe) {
		super(socket, universe);
	}

	@Override
	public StarmadePacket readPacket() throws IOException {
		return new StarmadePacket(input.readInt(), input);
	}

	@Override
	public void sendPacket(StarmadePacket packet) {
		for (int i = 0; i < 3; i++) {
			try {
				packet.send(output);
			} catch (IOException e) {
				getUniverse().getLogger().log(Level.SEVERE, "Error sending packet to socket at " + getSocket().getRemoteSocketAddress() + ". Retrying", e);
				continue;
			}
			return;
		}
	}

	@Override
	public Player onConnect(Socket connection) {

		try {
			input = new DataInputStream(connection.getInputStream());
			output = new DataOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			getUniverse().getLogger().log(Level.SEVERE, "Error opening streams to a socket. " + connection.getRemoteSocketAddress(), e);
			return null;
		}

		try {
			StarmadePacket packet = readPacket();
			System.out.println(packet.toString());
			byte commandID = packet.getCommandID();
			if (commandID == 0) { // Login Packet
				
			}
			
			if (commandID == 1) { // Ping packet
				
			}
			
		} catch (IOException e) {
			getUniverse().getLogger().log(Level.SEVERE, "Error reading packet from " + connection.getRemoteSocketAddress(), e);
			return null;
		}

		return null;

	}

	@Override
	public void track(Entity t) { // TODO: Track and send entity updates

	}

	@Override
	public void sendMessage(Player sender, String message) { // TODO: Send message to clients

	}

	@Override
	public void sendMessage(String message) { // TODO: Send broadcast to clients

	}

}
