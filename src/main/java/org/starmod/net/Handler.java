package org.starmod.net;

import org.starmod.ModClient;

public interface Handler<C extends Command> {

	public void handle(ModClient modClient, C cmd);

}
