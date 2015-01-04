package org.starmod.net.handler;

import org.starmod.net.Client;
import org.starmod.net.Handler;
import org.starmod.net.command.Ping;

public class PingHandler implements Handler<Ping> {

	@Override
	public void handle(Client client, Ping cmd) {
		System.out.println("Pong from Client " + client.getId());
	}

}
