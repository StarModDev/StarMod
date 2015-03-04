package org.starmod.entity;

import org.starmod.ModClient;
import org.starmod.api.entity.Player;
import org.starmod.api.world.Location;

public class ModPlayer extends ModHuman implements Player {

    private ModClient client;
    private Location spawnPoint;
    private boolean god;

    @Override
    public Location getSpawnPoint() {
        return spawnPoint;
    }

    @Override
    public void setSpawnPoint(Location spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    @Override
    public int getPing() {
        return client.getPing();
    }

    @Override
    public boolean isGod() {
        return god;
    }

    @Override
    public void setGod(boolean god) {
        this.god = god;
    }

    @Override
    public int getCredits() {
        return client.getCredits();
    }

    @Override
    public long getFirstPlayed() {
        return client.getFirstPlayed();
    }

    @Override
    public long getLastOnline() {
        return client.getLastOnline();
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public Player getPlayer() {
        return this;
    }

}
