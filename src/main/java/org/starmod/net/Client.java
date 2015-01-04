package org.starmod.net;

import io.netty.channel.Channel;
import org.starmod.ModServer;
import org.starmod.api.entity.OnlinePlayer;
import org.starmod.entity.ModOnlinePlayer;
import org.starmod.entity.meta.PlayerProfile;
import org.starmod.net.command.Login;
import org.starmod.net.command.Ping;
import org.starmod.net.util.IDGenerator;

/**
* Represents a client on the network server that has been registered
*/
public class Client {

	private int id;
	private String name;
	private NetworkServer networkServer;
	private ModServer server;
	private Channel channel;
	private boolean connected;

	/**
	* Constructs a registered client on the server which represents a connection
	* @param networkServer the network server which handles the clients connection
	* @param channel the channel the client communicates on
	*/
	public Client(ModServer server, NetworkServer networkServer, Channel channel) {
		this.id = IDGenerator.nextId();
		this.server = server;
		this.networkServer = networkServer;
		this.channel = channel;
		this.connected = channel.isActive();
	}

	/**
	* The unique id of this client
	* @return a unique id
	*/
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	* The network server which handles this client
	* @return the network server
	*/
	public NetworkServer getNetworkServer() {
		return networkServer;
	}

	public ModServer getServer() {
		return server;
	}

	/**
	* Gets the channel that the client communicates on
	* @return the client's channel
	*/
	public Channel getChannel() {
		return channel;
	}

	/**
	* Is the client currently connected to the {@link org.starmod.net.NetworkServer}
	* @return
	*/
	public boolean isConnected() {
		return connected;
	}

	/**
	* Method that is called when the client disconnects
	*/
	public void onDisconnect() {
		System.out.println("[StarMod][Network] Client " + id + " exited");
		// TODO - handle the disconnect
	}

	public void onInboundThrowable(Throwable t) {
		t.printStackTrace();
	}

	public void onOutboundThrowable(Throwable t) {
	}

	public void onLogin(Login cmd) {
		name = cmd.getPlayerName();
		OnlinePlayer player = new ModOnlinePlayer(this, new PlayerProfile(name, cmd.getAddress()));
		server.addPlayer(player);
		server.sendServerMessage(player.getName() + " has joined the game.");
	}

	public void ping() {
		send(new Ping(true));
	}

	public void pong() {
		send(new Ping(false));
	}

	/**
	* Sends a command from the server to the client
	* @param cmd to be sent
	*/
	public void send(Command cmd) {
		channel.writeAndFlush(cmd).addListener(future -> {
			if (future.cause() != null) {
				onOutboundThrowable(future.cause());
			}
		});
	}

	/**
	* A command that is sent to the server from the client. Called when the server
	* successfully receives and decodes the command from the {@link io.netty.buffer.ByteBuf}.
	* @param cmd
	*/
	public void incomingCommand(Command cmd) {
		Handler handler = networkServer.getCommandMap().getHandler(cmd.getHeader().getCommandId());
		if (handler != null) {
			handler.handle(this, cmd);
		}
	}

	@Override
	public String toString() {
		return "Client{" +
			"id=" + id +
			'}';
	}

}
