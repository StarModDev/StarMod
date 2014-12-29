package org.starmod.net.codec;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Codec;
import org.starmod.net.Header;
import org.starmod.net.command.Login;
import org.starmod.net.util.Parameters;

import java.io.IOException;

public class LoginCodec implements Codec<Login> {

	@Override
	public Login decode(ByteBuf buf, Header header) throws IOException {
		Object[] params = Parameters.decode(buf);
		String playerName = (String) params[0];
		float version = params.length > 1 ? ((float) params[1]) : 0.0F;
		String uid = params.length > 2 ? ((String) params[2]) : "";
		String loginCode = params.length > 3 ? ((String) params[3]) : "";
		return new Login(header, playerName, version, uid, loginCode);
	}

	@Override
	public ByteBuf encode(ByteBuf buf, Login cmd) throws IOException {
        	Object[] params = new Object[4];
        	params[0] = cmd.getReturnCode();
        	params[1] = cmd.getId();
        	params[2] = System.currentTimeMillis();
        	params[3] = cmd.getVersion();
        	buf = Parameters.encode(buf, params);
        	return buf;
	}

}
