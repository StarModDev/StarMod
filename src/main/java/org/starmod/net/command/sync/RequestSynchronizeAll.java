package org.starmod.net.command.sync;

import org.starmod.net.Command;
import org.starmod.net.Header;

public class RequestSynchronizeAll implements Command {

	private Header header;

	public RequestSynchronizeAll(Header header) {
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
