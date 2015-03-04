package org.starmod;

import io.netty.channel.Channel;
import org.starmod.api.Client;
import org.starmod.api.entity.Player;
import org.starmod.net.Command;
import org.starmod.net.Handler;
import org.starmod.net.NetworkServer;
import org.starmod.net.command.Login;
import org.starmod.net.command.Ping;
import org.starmod.net.util.IDGenerator;

import java.util.UUID;

/**
* Represents a client on the network server that has been registered
*/
public class ModClient implements Client {

	private int networkId;
	private String name;
	private NetworkServer networkServer;
	private ModServer server;
	private Channel channel;
	private boolean connected;
    private int credits;
    private final long firstPlayed;
    private long lastOnline;
    private int ping;
    private Player player;

	/**
	* Constructs a registered client on the server which represents a connection
	* @param networkServer the network server which handles the clients connection
	* @param channel the channel the client communicates on
	*/
	public ModClient(ModServer server, NetworkServer networkServer, Channel channel) {
		this.networkId = IDGenerator.nextId();
		this.server = server;
		this.networkServer = networkServer;
		this.channel = channel;
		this.connected = channel.isActive();
        this.firstPlayed = System.currentTimeMillis();
	}

	/**
	* The unique networkId of this client
	* @return a unique networkId
	*/
	public int getNetworkId() {
		return networkId;
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

    @Override
    public UUID getUniqueId() {
        return null;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public long getFirstPlayed() {
        return firstPlayed;
    }

    @Override
    public long getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(long lastOnline) {
        this.lastOnline = lastOnline;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
			"networkId=" + networkId +
			'}';
	}

}
