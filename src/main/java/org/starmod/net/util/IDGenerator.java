package org.starmod.net.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

	private static final AtomicInteger pool = new AtomicInteger();

	public static int nextId() {
		return pool.getAndIncrement();
	}

}
