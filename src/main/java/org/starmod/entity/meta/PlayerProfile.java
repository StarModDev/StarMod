package org.starmod.entity.meta;

import org.starmod.api.world.Location;
import org.starmod.api.world.Sector;
import org.starmod.api.world.StarSystem;

import java.io.File;
import java.io.IOException;

public class PlayerProfile extends EntityProfile {

	private String name;
	private int credits;
	private Location spawnPoint;
	private long lastLogin;
	private long firstLogin;
	private String address;

	public PlayerProfile(String name, String address) {
		super(new File("players/" + name + ".dat"));
		fromNBT();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Location getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Location spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public long getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(long firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public void fromNBT() {
		try {
			Object[] values = read();
			credits = (int) values[0];
			spawnPoint = (Location) values[1];
			lastLogin = (long) values[2];
			firstLogin = (long) values[3];
			address = (String) values[4];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toNBT() {
		Object[] values = new Object[] {
			credits,
			spawnPoint,
			lastLogin,
			firstLogin,
			address,
		};
		try {
			write(values);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadDefaults() {
		setCredits(25000);
		setFirstLogin(System.currentTimeMillis());
		setLastLogin(System.currentTimeMillis());
		setSpawnPoint(new Location(new Sector(new StarSystem(0, 0, 0), 2, 2, 2), 0, 0, 0));
		toNBT();
	}

}
