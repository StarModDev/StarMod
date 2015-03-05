package org.starmod.net.command.sync;

import org.starmod.net.Command;
import org.starmod.net.Header;

public class RequestSyncAll implements Command {

	private Header header;

	public RequestSyncAll(Header header) {
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
