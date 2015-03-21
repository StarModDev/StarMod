package org.starmod.net;

public interface Handler<C extends Command> {

	public void handle(NetworkClient modClient, C cmd);

}
