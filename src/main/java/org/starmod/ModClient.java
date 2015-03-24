package org.starmod;

import org.starmod.api.Client;
import org.starmod.api.entity.Player;

public class ModClient implements Client {

    private final ModServer server;
    private final String name;

    private int credits;
    private long firstPlayed;
    private long lastOnline;

    public ModClient(ModServer server, String name) {
        this.server = server;
        this.name = name;
    }

    @Override
    public int getId() {
        return 0;
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
        return server.getPlayer(name);
    }

}
