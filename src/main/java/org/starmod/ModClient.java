package org.starmod;

import org.starmod.api.Client;
import org.starmod.api.entity.Player;

import java.util.UUID;

public class ModClient implements Client {

    private final ModServer server;
    private final String name;
    private final UUID uniqueId;

    private int credits;
    private long firstPlayed;
    private long lastOnline;

    public ModClient(ModServer server, String name) {
        this.server = server;
        this.uniqueId = null; // TODO: Get UUID from player file
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public long getFirstPlayed() {
        return firstPlayed;
    }

    @Override
    public long getLastOnline() {
        return lastOnline;
    }

    @Override
    public boolean isOnline() {
        return getPlayer() != null;
    }

    @Override
    public Player getPlayer() {
        if (uniqueId != null) {
            return server.getPlayer(uniqueId);
        } else {
            return server.getPlayer(name);
        }
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

}
