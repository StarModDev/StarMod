package org.starmod.net.command;

import org.starmod.net.Command;
import org.starmod.net.Header;

public class Ping implements Command {

	private boolean ping;

	public Ping(boolean ping) {
		this.ping = ping;
	}

	public boolean isPing() {
		return ping;
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
		return "Ping{" +
			"ping=" + ping +
			'}';
	}

}
