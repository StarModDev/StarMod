package org.starmod.net.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

	private static final AtomicInteger pool = new AtomicInteger();
	private static final AtomicInteger packetPool = new AtomicInteger(-32767);
    private static final AtomicInteger sendablePool = new AtomicInteger(0);

	public static int nextId() {
		return pool.getAndIncrement();
	}

	public static int nextPacketId() {
		return packetPool.getAndIncrement();
	}

    public static int nextSendableKey() {
        return sendablePool.getAndIncrement();
    }

}
