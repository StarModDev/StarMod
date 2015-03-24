package org.starmod.entity;

import com.flowpowered.nbt.CompoundTag;
import org.starmod.ModClient;
import org.starmod.api.item.inventory.Inventory;
import org.starmod.net.NetworkClient;
import org.starmod.api.entity.Player;
import org.starmod.api.world.Location;

public class ModPlayer extends ModLiving implements Player {

    private NetworkClient networkClient;
    private ModClient client;
    private Location spawnPoint;
    private Inventory inventory;
    private boolean god;

    @Override
    public int getId() {
        return networkClient.getNetworkId();
    }

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
        return networkClient.getPing();
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
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Player getPlayer() {
        return this;
    }

    @Override
    public void save(CompoundTag tag) {

    }

    @Override
    public void load(CompoundTag tag) {

    }

}
