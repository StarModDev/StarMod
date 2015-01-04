package org.starmod.net.codec.sync;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.sync.RequestServerTime;

import java.io.IOException;

public class RequestServerTimeCodec implements Codec<RequestServerTime> {

	@Override
	public RequestServerTime decode(ByteBuf buf, Header header) throws IOException {
		return null;
	}

	@Override
	public ByteBuf encode(ByteBuf buf, RequestServerTime cmd) throws IOException {
		buf.writeLong(System.currentTimeMillis());
		return buf;
	}

}
