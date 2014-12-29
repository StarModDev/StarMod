package org.starmod.net.handler;

import org.starmod.net.Client;
import org.starmod.net.Handler;
import org.starmod.net.command.Synchronize;

public class SynchronizeHandler implements Handler<Synchronize> {

	@Override
	public void handle(Client client, Synchronize cmd) {
		if (cmd.getParams().length == 0) {
			System.out.println("[StarMod][Network] Client " + client.getId() + " requesting sync");
			client.ping();
		}
	}

}
