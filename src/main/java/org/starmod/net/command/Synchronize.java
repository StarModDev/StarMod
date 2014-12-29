package org.starmod.net.command;

import org.starmod.net.Command;
import org.starmod.net.Header;

import java.util.Arrays;

public class Synchronize implements Command {

	private final Header header;
	private final Object[] params;

	public Synchronize(Header header, Object[] params) {
		this.header = header;
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}

	@Override
	public Header getHeader() {
		return header;
	}

	@Override
	public boolean isAsync() {
		return false;
	}

	@Override
	public String toString() {
		return "Synchronize{" +
			"params=" + Arrays.toString(params) +
			'}';
	}

}
