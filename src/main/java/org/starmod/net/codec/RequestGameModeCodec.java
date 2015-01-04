package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.RequestGameMode;
import org.starmod.net.util.Parameters;

import java.io.IOException;

public class RequestGameModeCodec implements Codec<RequestGameMode> {

	@Override
	public RequestGameMode decode(ByteBuf buf, Header header) throws IOException {
		buf.readInt();
		return new RequestGameMode(header);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, RequestGameMode cmd) throws IOException {
		Object[] params = new Object[13];
		params[0] = cmd.getGameMode();
		params[1] = 2;
		params[2] = cmd.getSector().getX();
		params[3] = cmd.getSector().getY();
		params[4] = cmd.getSector().getZ();
		params[5] = cmd.getConfigChecksum();
		params[6] = cmd.getConfigPropertiesChecksum();
		params[7] = cmd.isAsteroidsDynamicPhysics();
		params[8] = cmd.getBlockBehaviorChecksum();
		// Hardcoded for now some sort of checksum but unknown value
		params[9] = "cf29931069e83777d193da437dbd6a03c209bdf34cbb914a55e1651786e73d9102b5cbb73a5322be8cffba5b9f86445f49a8e69e7ffb2c0aa0687b8a4c81d62ecc62a2d1276970907b9ae1f3431b014f6d746c8dab54177d1a44430b65fd1c0494282791b587431a4e4a290110b4f69ed92c0d3dda74a0d1";
		params[10] = true;
		params[11] = "cdda1e8603294f21b78e21b6239c0509b8655c80";
		params[12] = 127;
		buf = Parameters.encode(buf, params);
		return buf;
	}

}
