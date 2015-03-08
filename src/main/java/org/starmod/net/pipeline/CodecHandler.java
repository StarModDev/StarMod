package org.starmod.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.starmod.net.*;

import java.util.List;

public class CodecHandler extends MessageToMessageCodec<ByteBuf, Command> {

	private final NetworkServer server;

	public CodecHandler(NetworkServer server) {
		this.server = server;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Command cmd, List<Object> out) throws Exception {
		final Codec codec = server.getCommandMap().getCodec(cmd.getHeader().getCommandId());

		ByteBuf cmdBuf = ctx.alloc().buffer();
		cmdBuf = codec.encode(cmdBuf, cmd);

		ByteBuf headerBuf = ctx.alloc().buffer(Header.SIZE);
		headerBuf = cmd.getHeader().encode(headerBuf);
		out.add(Unpooled.wrappedBuffer(headerBuf, cmdBuf));
	}

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List<Object> out) throws Exception {
		Header header = Header.decode(buf);
		System.out.println("New Incoming Packet " + header.getPacketId() + " Command ID: " + header.getCommandId() + " Type: " + header.getCommandType()); // Debugging
		final Codec codec = server.getCommandMap().getCodec(header.getCommandId());
		Command cmd = codec.decode(buf, header);
        out.add(cmd);
	}

}
