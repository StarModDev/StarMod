package org.starmod.net.handler;

import org.starmod.net.NetworkClient;
import org.starmod.net.Handler;
import org.starmod.net.command.PingPong;

public class PingPongHandler implements Handler<PingPong> {

	@Override
	public void handle(NetworkClient modClient, PingPong cmd) {
		// TODO: handle pings/pongs
        System.out.println("pong");
    }

}
