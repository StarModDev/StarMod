package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.MessageTo;
import org.starmod.net.util.Parameters;

import java.io.IOException;

public class MessageToCodec implements Codec<MessageTo> {

	@Override
	public MessageTo decode(ByteBuf buf, Header header) throws IOException {
		Object[] params = Parameters.decode(buf);
		String prefix = (String) params[0];
		String message = (String) params[1];
		int type = (int) params[2];
		return new MessageTo(header, prefix, message, type);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, MessageTo cmd) throws IOException {
		Object[] params = new Object[3];
		params[0] = cmd.getPrefix();
		params[1] = cmd.getMessage();
		params[2] = cmd.getType();
		buf = Parameters.encode(buf, params);
		return buf;
	}

}
