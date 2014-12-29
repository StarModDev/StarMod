package org.starmod;

import java.net.InetSocketAddress;

public class Starmade {
	
	public static void main(String[] args) {
		new StarmadeServer(8, new InetSocketAddress(4242));
	}
}
