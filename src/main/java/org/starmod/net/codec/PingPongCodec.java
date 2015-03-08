package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.PingPong;

import java.io.IOException;

public class PingPongCodec implements Codec<PingPong> {

	@Override
	public PingPong decode(ByteBuf buf, Header header) throws IOException {
		byte pingType = buf.readByte();
		return new PingPong(pingType);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, PingPong cmd) throws IOException {
		buf.writeByte(cmd.getType());
		return buf;
	}

}
