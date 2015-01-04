package org.starmod.net.command.sync;

import org.starmod.net.Command;
import org.starmod.net.Header;

public class RequestServerTime implements Command {

	private final Header header;

	public RequestServerTime(Header header) {
		this.header = header;
	}

	@Override
	public Header getHeader() {
		return header;
	}

	@Override
	public boolean isAsync() {
		return false;
	}

}
