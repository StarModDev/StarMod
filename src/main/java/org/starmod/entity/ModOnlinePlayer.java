package org.starmod.entity;

import org.starmod.api.entity.OnlinePlayer;
import org.starmod.api.world.Location;
import org.starmod.entity.meta.PlayerProfile;
import org.starmod.net.Client;

public class ModOnlinePlayer implements OnlinePlayer {

	private final Client client;
	private String name;
	private int credits;
	private final long firstPlayed;
	private long lastOnline;
	private Location location;

	public ModOnlinePlayer(Client client, PlayerProfile profile) {
		this.client = client;
		this.name = profile.getName();
		this.credits = profile.getCredits();
		this.firstPlayed = profile.getFirstLogin();
		this.lastOnline = profile.getLastLogin();
		this.location = profile.getSpawnPoint();
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
	public OnlinePlayer getOnlinePlayer() {
		return this;
	}

	@Override
	public boolean isOnline() {
		return true;
	}

	@Override
	public Location getLocation() {
		return location;
	}

}
