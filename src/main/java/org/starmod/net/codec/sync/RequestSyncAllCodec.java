package org.starmod.net.codec.sync;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.sync.RequestSyncAll;

import java.io.IOException;

public class RequestSyncAllCodec implements Codec<RequestSyncAll> {

	@Override
	public RequestSyncAll decode(ByteBuf buf, Header header) throws IOException {
		return new RequestSyncAll(header);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, RequestSyncAll cmd) throws IOException {
		return buf;
	}

}
