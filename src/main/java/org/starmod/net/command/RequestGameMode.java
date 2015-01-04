package org.starmod.net.command;

import org.starmod.api.world.Sector;
import org.starmod.net.Command;
import org.starmod.net.Header;

public class RequestGameMode implements Command {

	private final Header header;

	private String gameMode;
	private Sector sector;
	private String configChecksum;
	private String configPropertiesChecksum;
	private boolean asteroidsDynamicPhysics;
	private String blockBehaviorChecksum;

	public RequestGameMode(Header header) {
		this.header = header;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getConfigChecksum() {
		return configChecksum;
	}

	public void setConfigChecksum(String configChecksum) {
		this.configChecksum = configChecksum;
	}

	public String getConfigPropertiesChecksum() {
		return configPropertiesChecksum;
	}

	public void setConfigPropertiesChecksum(String configPropertiesChecksum) {
		this.configPropertiesChecksum = configPropertiesChecksum;
	}

	public boolean isAsteroidsDynamicPhysics() {
		return asteroidsDynamicPhysics;
	}

	public void setAsteroidsDynamicPhysics(boolean asteroidsDynamicPhysics) {
		this.asteroidsDynamicPhysics = asteroidsDynamicPhysics;
	}

	public String getBlockBehaviorChecksum() {
		return blockBehaviorChecksum;
	}

	public void setBlockBehaviorChecksum(String blockBehaviorChecksum) {
		this.blockBehaviorChecksum = blockBehaviorChecksum;
	}

	@Override
	public Header getHeader() {
		return header;
	}

	@Override
	public boolean isAsync() {
		return false;
	}

	@Override
	public String toString() {
		return "RequestGameMode{" +
			"header=" + header +
			", gameMode='" + gameMode + '\'' +
			", sector=" + sector +
			", configChecksum='" + configChecksum + '\'' +
			", configPropertiesChecksum='" + configPropertiesChecksum + '\'' +
			", asteroidsDynamicPhysics=" + asteroidsDynamicPhysics +
			", blockBehaviorChecksum='" + blockBehaviorChecksum + '\'' +
			'}';
	}
}
