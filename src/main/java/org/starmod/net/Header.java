package org.starmod.net;

import io.netty.buffer.ByteBuf;

public class Header {

	public static final byte BYTE_PACKET = 42;
	public static final byte BYTE_PING = 23;
	public static final byte BYTE_TEST = 100;
	public static final byte BYTE_LOGOUT = 65;

	public static final int SIZE = 5; // The size in bytes of a header

	private final byte packetType;
	private final short packetId;
	private final byte commandId;
	private final byte commandType;

	/**
	 * Constructs a header with a specified size.
	 * @param packetType the packet identification byte
	 * @param packetId the unique packet id
	 * @param commandId the command id associated with the header
	 * @param commandType the type of command, stream or parametrized
	 */
	public Header(byte packetType, short packetId, byte commandId, byte commandType) {
		this.packetType = packetType;
		this.packetId = packetId;
		this.commandId = commandId;
		this.commandType = commandType;
	}

	/**
	 * The type of packet, currently there are 4 bytes used
	 * @return the packet type byte
	 */
	public byte getPacketType() {
		return packetType;
	}

	/**
	 * The unique ID of the packet created by the ID generator, if the packet is -4242 it denotes
	 * first time connection with the server.
	 * @return the ID of the packet as a short
	 */
	public short getPacketId() {
		return packetId;
	}

	/**
	 * The command ID used to select the appropriate codec to use.
	 * @return
	 */
	public byte getCommandId() {
		return commandId;
	}

	/**
	 * The type of command, currently there are two options: parametrized or stream.
	 * @return one of two bytes, parametrized or stream
	 */
	public byte getCommandType() {
		return commandType;
	}

	/**
	 * Encode the packet to send over the network.
	 * @param buf the buffer before the header is written
	 * @return the buffer after the header is written
	 */
	public ByteBuf encode(ByteBuf buf) {
		buf.writeLong(System.currentTimeMillis());
		buf.writeByte(packetType);
		if (packetType != Header.BYTE_PING) {
			buf.writeShort(packetId);
			buf.writeByte(commandId);
			buf.writeByte(commandType);
		}
		return buf;
	}

	/**
	 * Used to decode a ByteBuf to a header object.
	 * @param buf the buffer to read the header off of.
	 * @return the newly create Header object
	 */
	public static Header decode(ByteBuf buf) {
		byte packetType = buf.readByte();
		short packetId;
		byte commandId;
		byte commandType;
		if (packetType != Header.BYTE_PING) {
			packetId = buf.readShort();
			commandId = buf.readByte();
			commandType = buf.readByte();
		} else {
			packetId = 0;
			commandId = Header.BYTE_PING;
			commandType = 0;
		}
		return new Header(packetType, packetId, commandId, commandType);
	}

	@Override
	public String toString() {
		return "Header{" +
			", packetType=" + packetType +
			", packetId=" + packetId +
			", commandId=" + commandId +
			", commandType=" + commandType +
			'}';
	}

}
