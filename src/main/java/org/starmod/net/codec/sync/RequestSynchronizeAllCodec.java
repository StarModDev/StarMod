package org.starmod.net.codec.sync;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.sync.RequestSynchronizeAll;

import java.io.IOException;

public class RequestSynchronizeAllCodec implements Codec<RequestSynchronizeAll> {

	@Override
	public RequestSynchronizeAll decode(ByteBuf buf, Header header) throws IOException {
		buf.readInt();
		return new RequestSynchronizeAll(header);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, RequestSynchronizeAll cmd) throws IOException {
		return null;
	}

}
