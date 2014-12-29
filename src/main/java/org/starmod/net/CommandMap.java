package org.starmod.net;

import org.starmod.net.codec.LoginCodec;
import org.starmod.net.codec.PingCodec;
import org.starmod.net.codec.SynchronizeCodec;
import org.starmod.net.command.Login;
import org.starmod.net.command.Ping;
import org.starmod.net.command.Synchronize;
import org.starmod.net.handler.LoginHandler;
import org.starmod.net.handler.PingHandler;
import org.starmod.net.handler.SynchronizeHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CommandMap {

	private HashMap<Integer, Class<? extends Command>> commands = new HashMap<>();
	private ConcurrentMap<Class<? extends Command>, Codec> codecs = new ConcurrentHashMap<>();
	private ConcurrentMap<Class<? extends Command>, Handler> handlers = new ConcurrentHashMap<>();

	public CommandMap() {
		loadDefaultCommands();
	}

	private <M extends Command, C extends Codec, H extends Handler> void bind(int id, Class<M> cmdClass, Class<C> codecClass, Class<H> handlerClass) {
		Codec codec = null;
		Handler handler = null;
		try {
			Constructor<C> codecCon = codecClass.getConstructor();
			Constructor<H> handlerCon = handlerClass.getConstructor();
			codecCon.setAccessible(true);
			handlerCon.setAccessible(true);
			codec = codecCon.newInstance();
			handler = handlerCon.newInstance();
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		commands.put(id, cmdClass);
		codecs.put(cmdClass, codec);
		handlers.put(cmdClass, handler);
	}

	private void loadDefaultCommands() {
		bind(0, Login.class, LoginCodec.class, LoginHandler.class);
		bind(13, Synchronize.class, SynchronizeCodec.class, SynchronizeHandler.class);
		bind(23, Ping.class, PingCodec.class, PingHandler.class);
	}

	public Codec getCodec(int id) {
		return codecs.get(commands.get(id));
	}

	public Handler getHandler(int id) {
		return handlers.get(commands.get(id));
	}

}
