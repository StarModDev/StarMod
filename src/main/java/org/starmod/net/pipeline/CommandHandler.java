package org.starmod.net.pipeline;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.starmod.net.Client;
import org.starmod.net.Command;
import org.starmod.net.NetworkServer;

import java.util.concurrent.atomic.AtomicReference;

public class CommandHandler extends SimpleChannelInboundHandler<Command> {

	private final AtomicReference<Client> client = new AtomicReference<>(null);
	private final NetworkServer server;

	public CommandHandler(NetworkServer server) {
		this.server = server;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		final Channel c = ctx.channel();
		System.out.println("[StarMod][Network] Incoming connection: " + c.remoteAddress());
		Client client = server.newClient(c);
		if (!this.client.compareAndSet(null, client)) {
		    throw new IllegalStateException("There can only be one registered client per connection!");
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Client client = this.client.get();
		client.onDisconnect();
		server.removeClient(client);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command cmd) throws Exception {
		client.get().incomingCommand(cmd);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		client.get().onInboundThrowable(cause);
	}

	public Client getClient() {
		return client.get();
	}

}
