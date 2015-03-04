package org.starmod.net.handler;

import org.starmod.ModClient;
import org.starmod.net.Handler;
import org.starmod.net.command.Synchronize;

public class SynchronizeHandler implements Handler<Synchronize> {

	@Override
	public void handle(ModClient modClient, Synchronize cmd) {
		if (cmd.getParams().length == 0) {
			System.out.println("[StarMod][Network] Client " + modClient.getNetworkId() + " requesting sync");
			modClient.ping();
		}
	}

}
