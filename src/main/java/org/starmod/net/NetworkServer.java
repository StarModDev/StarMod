package org.starmod.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.starmod.ModServer;
import org.starmod.net.command.MessageTo;
import org.starmod.net.pipeline.PipelineChannelInitializer;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NetworkServer implements Runnable {

	private final ConcurrentMap<NetworkClient, Boolean> clients = new ConcurrentHashMap<>();
	private final CommandMap commandMap;

	private ModServer server;
	private InetSocketAddress address;

	private final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private final EventLoopGroup workerGroup = new NioEventLoopGroup();

	public NetworkServer(ModServer server, InetSocketAddress address) {
		this.server = server;
		this.address = address;
		this.commandMap = new CommandMap();
	}

	@Override
	public void run() {

		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new PipelineChannelInitializer(this))
			.option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.SO_KEEPALIVE, true);
		b.bind(address);
		System.out.println("[StarMod][Network] Network server started on " + address.getHostString() + ":" + address.getPort());
	}

	public NetworkClient newClient(Channel channel) {
        NetworkClient modClient = new NetworkClient(server, this, channel);
		System.out.println("[StarMod][Network] Client created with ID " + modClient.getNetworkId());
		clients.put(modClient, true);
		return modClient;
	}

	public void removeClient(NetworkClient modClient) {
		clients.remove(modClient);
	}

	public void sendMessage(String message) {
		for (NetworkClient modClient : clients.keySet()) {
			modClient.send(new MessageTo("SERVER", message, 0));
		}
	}

	public CommandMap getCommandMap() {
		return commandMap;
	}

	public void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

}
