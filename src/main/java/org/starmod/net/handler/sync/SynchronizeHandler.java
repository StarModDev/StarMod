package org.starmod.net.handler.sync;

import io.netty.buffer.ByteBuf;
import org.starmod.net.NetworkClient;
import org.starmod.ServerRegistry;
import org.starmod.net.Handler;
import org.starmod.net.command.sync.Synchronize;
import org.starmod.net.object.NetworkObject;
import org.starmod.net.object.Sendable;
import org.starmod.net.object.SendableRegistry;

public class SynchronizeHandler implements Handler<Synchronize> {

	@Override
	public void handle(NetworkClient modClient, Synchronize cmd) {
        for (int i = 0; i < cmd.getIds().length; i++) {
            int id = cmd.getIds()[i];
            if (ServerRegistry.containsLocal(id)) {
                updateObject(cmd.getBuf(), id);
            } else {
                newObject(cmd.getBuf(), cmd.getKeys()[i]);
            }
        }
    }

    public void updateObject(ByteBuf buf, int id) {
        NetworkObject netObj = ServerRegistry.getRemote(id);
        netObj = NetworkObject.decode(buf, netObj);

        Sendable sendable = ServerRegistry.getLocal(id);
        sendable.update(netObj);
    }

    public void newObject(ByteBuf buf, byte key) {
        Sendable sendable = SendableRegistry.getSendable(key);

        NetworkObject netObj = sendable.getNetworkObject();
        netObj = NetworkObject.decode(buf, netObj);

        sendable.initialize(netObj);

        int id = netObj.id.getValue();

        ServerRegistry.addLocal(id, sendable);
        ServerRegistry.addRemote(id, netObj);
    }

}
