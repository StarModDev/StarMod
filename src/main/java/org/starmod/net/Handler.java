package org.starmod.net;

public interface Handler<C extends Command> {

	public void handle(Client client, C cmd);

}
