package org.starmod.net.command;

import org.starmod.net.Command;
import org.starmod.net.Header;
import org.starmod.net.util.IDGenerator;

public class MessageTo implements Command {

	private final Header header;
	private final String prefix;
	private final String message;
	private final int type;

	public MessageTo(String prefix, String message, int type) {
		this.header = new Header(Header.BYTE_PACKET, (short) IDGenerator.nextPacketId(), (byte) 22, (byte) 111);
		this.prefix = prefix;
		this.message = message;
		this.type = type;
	}

	public MessageTo(Header header, String prefix, String message, int type) {
		this.header = header;
		this.prefix = prefix;
		this.message = message;
		this.type = type;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getMessage() {
		return message;
	}

	public int getType() {
		return type;
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
