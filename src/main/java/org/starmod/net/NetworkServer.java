package org.starmod.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.starmod.net.pipeline.PipelineChannelInitializer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NetworkServer implements Runnable {

	private final ConcurrentMap<Client, Boolean> clients = new ConcurrentHashMap<>();
	private final CommandMap commandMap;

	private int port;

	public NetworkServer(int port) {
		this.port = port;
		this.commandMap = new CommandMap();
	}

	@Override
	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new PipelineChannelInitializer(this))
			.option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.SO_KEEPALIVE, true);
		b.bind(port);
		System.out.println("[StarMod][Network] Network server is listening on port " + port);
	}

	public Client newClient(Channel channel) {
		Client client = new Client(this, channel);
		System.out.println("[StarMod][Network] Client created with ID " + client.getId());
		clients.put(client, true);
		return client;
	}

	public void removeClient(Client client) {
		clients.remove(client);
	}

	public CommandMap getCommandMap() {
		return commandMap;
	}

}
