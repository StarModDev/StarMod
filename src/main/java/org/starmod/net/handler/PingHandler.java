package org.starmod.net.handler;

import org.starmod.ModClient;
import org.starmod.net.Handler;
import org.starmod.net.command.Ping;

public class PingHandler implements Handler<Ping> {

	@Override
	public void handle(ModClient modClient, Ping cmd) {
		System.out.println("Pong from Client " + modClient.getNetworkId());
	}

}
