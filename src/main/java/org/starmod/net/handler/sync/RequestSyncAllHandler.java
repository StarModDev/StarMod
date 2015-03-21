package org.starmod.net.handler.sync;

import org.starmod.net.NetworkClient;
import org.starmod.net.Handler;
import org.starmod.net.command.sync.RequestSyncAll;

public class RequestSyncAllHandler implements Handler<RequestSyncAll> {

	@Override
	public void handle(NetworkClient modClient, RequestSyncAll cmd) {
        System.out.println("[StarMod][Sync] Client " + modClient.getNetworkId() + " is requesting sync all");
	}

}
