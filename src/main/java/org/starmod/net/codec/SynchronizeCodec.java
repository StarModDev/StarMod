package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.sync.Synchronize;

import java.io.IOException;

public class SynchronizeCodec implements Codec<Synchronize> {

	@Override
	public Synchronize decode(ByteBuf buf, Header header) throws IOException {

        int size = buf.readInt();

        int[] ids = new int[size];
        byte[] keys = new byte[size];

        for (int i = 0; i < size; i++) {

            int id = buf.readInt();
            ids[i] = id;

            byte key = buf.readByte();
            keys[i] = key;

        }

        return new Synchronize(header, buf, ids, keys);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, Synchronize cmd) throws IOException {
		return null;
	}

}
