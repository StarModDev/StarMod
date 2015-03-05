package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.sync.Synchronize;

import java.io.IOException;

public class SynchronizeCodec implements Codec<Synchronize> {

	@Override
	public Synchronize decode(ByteBuf buf, Header header) throws IOException {
		return new Synchronize(header);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, Synchronize cmd) throws IOException {
		return null;
	}

}
