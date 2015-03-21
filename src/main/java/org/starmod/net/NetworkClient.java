package org.starmod.net;

import io.netty.channel.Channel;
import org.starmod.ModServer;
import org.starmod.net.command.Login;
import org.starmod.net.command.PingPong;
import org.starmod.net.util.IDGenerator;

/**
* Represents a client on the network server that has been registered
*/
public class NetworkClient {

	private int networkId;
	private NetworkServer networkServer;
	private ModServer server;
	private Channel channel;
	private boolean connected;
    private int ping;

	/**
	* Constructs a registered client on the server which represents a connection
	* @param networkServer the network server which handles the clients connection
	* @param channel the channel the client communicates on
	*/
	public NetworkClient(ModServer server, NetworkServer networkServer, Channel channel) {
		this.networkId = IDGenerator.nextId();
		this.server = server;
		this.networkServer = networkServer;
		this.channel = channel;
		this.connected = channel.isActive();
	}

	/**
	* The unique networkId of this client
	* @return a unique networkId
	*/
	public int getNetworkId() {
		return networkId;
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

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    /**
	* Method that is called when the client disconnects
	*/
	public void onDisconnect() {
		System.out.println("[StarMod][Network] Client " + networkId + " exited");
		// TODO - handle the disconnect
	}

	public void onInboundThrowable(Throwable t) {
		t.printStackTrace();
	}

	public void onOutboundThrowable(Throwable t) {
	}

	public void onLogin(Login cmd) {
		// TODO: Handler onLogin
	}

	public void ping() {
		send(new PingPong(PingPong.PING));
	}

	public void pong() {
		send(new PingPong(PingPong.PONG));
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
			"networkId=" + networkId +
			'}';
	}

}
