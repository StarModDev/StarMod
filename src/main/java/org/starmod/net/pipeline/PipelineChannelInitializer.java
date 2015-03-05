package org.starmod.net.pipeline;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.starmod.net.NetworkServer;

public class PipelineChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final NetworkServer server;

	public PipelineChannelInitializer(NetworkServer server) {
	    this.server = server;
	}

	@Override
	protected void initChannel(SocketChannel c) throws Exception {
        FramingHandler framingHandler = new FramingHandler();
		CodecHandler codecHandler = new CodecHandler(server);
		CommandHandler commandHandler = new CommandHandler(server);
		c.pipeline().addLast("framing", framingHandler)
                    .addLast("codec", codecHandler)
			        .addLast("handler", commandHandler);
	}

}
