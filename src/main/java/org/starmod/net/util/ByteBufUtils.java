package org.starmod.net.util;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteBufUtils {

	/**
	 * Reads an UTF8 string from a byte buffer.
	 *
	 * @param buf The byte buffer to read from
	 * @return The read string
	 * @throws java.io.IOException If the reading fails
	 */
	public static String readUTF8(ByteBuf buf) throws IOException {
		final int length = buf.readUnsignedShort();
		final byte[] bytes = new byte[length];
		buf.readBytes(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	/**
	 * Writes an UTF8 string to a byte buffer.
	 *
	 * @param buf The byte buffer to write too
	 * @param value The string to write
	 * @throws java.io.IOException If the writing fails
	 */
	public static void writeUTF8(ByteBuf buf, String value) throws IOException {
		final byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
		if (bytes.length >= Short.MAX_VALUE) {
			throw new IOException("Attempt to write a string with a length greater than Short.MAX_VALUE to ByteBuf!");
		}
		buf.writeShort(value.length());
		buf.writeBytes(bytes);
	}

}
