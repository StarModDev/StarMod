package org.starmod.net.util;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * The class used for encoding and decoding parameters used in commands.
 */
public final class Parameters {

	/**
	 * Decodes parameters from a ByteBuf
	 * @param buf the buffer to read from
	 * @return the parameter array
	 * @throws IOException
	 */
	public static Object[] decode(ByteBuf buf) throws IOException {
		int arraySize;
		Object[] params = new Object[arraySize = buf.readInt()];
		for (int i = 0; i < arraySize; i++) {
			int type;
			switch (type = buf.readByte()) {
				case 2:
					params[i] = buf.readLong(); break;
				case 4:
					params[i] = ByteBufUtils.readUTF8(buf); break;
				case 3:
					params[i] = buf.readFloat(); break;
				case 1:
					params[i] = buf.readInt(); break;
				case 5:
					params[i] = buf.readBoolean(); break;
				case 6:
					params[i] = buf.readByte(); break;
				case 7:
					params[i] = buf.readShort(); break;
				case 8:
					byte[] byteArray = new byte[buf.readInt()];
					buf.readBytes(byteArray);
					params[i] = byteArray;
					break;
				default:
					throw new IllegalArgumentException("Type " + type + " unknown when parsing parameter " + i + " of  " + arraySize);
			}
		}
		return params;
	}

	/**
	 * Encode an array of parameters into a ByteBuf
	 * @param buf the buffer to write to
	 * @param params the array of parameters
	 * @return the buffer after the parameters are written
	 * @throws IOException
	 */
	public static ByteBuf encode(ByteBuf buf, Object[] params) throws IOException {
		buf.writeInt(params.length);
		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof Long) {
			buf.writeByte(2);
			buf.writeLong((Long) params[i]);
			} else if (params[i] instanceof String) {
				buf.writeByte(4);
				ByteBufUtils.writeUTF8(buf, (String) params[i]);
			} else if (params[i] instanceof Float) {
				buf.writeByte(3);
				buf.writeFloat((Float) params[i]);
			} else if (params[i] instanceof Integer) {
				buf.writeByte(1);
				buf.writeInt((Integer) params[i]);
			} else if (params[i] instanceof Boolean) {
				buf.writeByte(5);
				buf.writeBoolean((Boolean) params[i]);
			} else if (params[i] instanceof Byte) {
				buf.writeByte(6);
				buf.writeByte((Byte) params[i]);
			} else if (params[i] instanceof Short) {
				buf.writeByte(7);
				buf.writeShort((Short) params[i]);
			} else if (params[i] instanceof byte[]) {
				buf.writeByte(8);
				byte[] byteArray = (byte[]) params[i];
				buf.writeInt(byteArray.length);
				buf.writeBytes(byteArray);
			} else {
				throw new IllegalArgumentException("Type " + params[i] +
					" is of an unknown object! When parsing parameter " + i + " of " + params.length);
			}
		}
		return buf;
	}

}