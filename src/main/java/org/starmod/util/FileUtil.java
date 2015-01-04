package org.starmod.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {

	public static byte[] createChecksum(File file) throws IOException, NoSuchAlgorithmException {
		byte[] bytes = new byte[1024];
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		int i;
		do {
			if ((i = in.read(bytes)) > 0) {
				sha1.update(bytes, 0, i);
			}
		} while (i != -1);
		in.close();
		return sha1.digest();
	}

	public static String getSha1Checksum(File file) throws IOException, NoSuchAlgorithmException {
		byte[] bytes = createChecksum(file);

		String result = "";
		for (byte b : bytes) {
			result = result + Integer.toString((b & 0xFF) + 256, 16).substring(1);
		}

		return result;
	}

}
