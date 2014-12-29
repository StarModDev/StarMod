package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.Synchronize;
import org.starmod.net.util.Parameters;

import java.io.IOException;

public class SynchronizeCodec implements Codec<Synchronize> {

	@Override
	public Synchronize decode(ByteBuf buf, Header header) throws IOException {
		Object[] params = Parameters.decode(buf);
		return new Synchronize(header, params);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, Synchronize cmd) throws IOException {
		return null;
	}

}
