package org.starmod.net.command;

import org.starmod.net.Command;
import org.starmod.net.Header;

public class PingPong implements Command {

    public static final int PING = -1, PONG = -2;

	private int type;

	public PingPong(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	@Override
	public Header getHeader() {
		return new Header(Header.BYTE_PING, (short) 0, Header.BYTE_PING, (byte) 0);
	}

	@Override
	public boolean isAsync() {
		return false;
	}

    @Override
    public String toString() {
        String type;
        switch (this.type) {
            case PING:
                type = "PING"; break;
            case PONG:
                type = "PONG"; break;
            default:
                type = "UNKNOWN"; break;
        }
        return "PingPong{" +
                "type=" + type +
                '}';
    }

}
