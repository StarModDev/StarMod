package org.starmod.net;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Used to encode and decode a command to {@link io.netty.buffer.ByteBuf}
 * @param <C> Command used in the codec
 */
public interface Codec<C extends Command> {

	C decode(ByteBuf buf, Header header) throws IOException;

	ByteBuf encode(ByteBuf buf, C cmd) throws IOException;

}
