package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.Ping;

import java.io.IOException;

public class PingCodec implements Codec<Ping> {

	@Override
	public Ping decode(ByteBuf buf, Header header) throws IOException {
		byte pingPong = buf.readByte();
		boolean ping = true;
		if (pingPong == -2) ping = false;
		return new Ping(ping);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, Ping cmd) throws IOException {
		byte pingPong = -1;
		if (!cmd.isPing()) pingPong = -2;
		buf.writeByte(pingPong);
		return buf;
	}

}
