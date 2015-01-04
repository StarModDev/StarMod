package org.starmod.net.handler;

import org.starmod.net.Client;
import org.starmod.net.Handler;
import org.starmod.net.command.RequestGameMode;
import org.starmod.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RequestGameModeHandler implements Handler<RequestGameMode> {

	@Override
	public void handle(Client client, RequestGameMode cmd) {
		client.ping();
		cmd.setGameMode(client.getServer().getConfig().getString("gameMode"));
		cmd.setSector(client.getServer().getConfig().getSector("startingSystem", "startingSector"));
		try {
			cmd.setConfigChecksum(FileUtil.getSha1Checksum(new File("data/config/BlockConfig.xml")));
			cmd.setConfigPropertiesChecksum(FileUtil.getSha1Checksum(new File("data/config/BlockTypes.properties")));
			cmd.setBlockBehaviorChecksum(FileUtil.getSha1Checksum(new File("data/config/blockBehaviorConfig.xml")));
		} catch (IOException | NoSuchAlgorithmException e) {
			System.err.println("Error finding checksum on file: " + e.getMessage());
		}
		cmd.setAsteroidsDynamicPhysics(client.getServer().getConfig().getBoolean("asteroidPhysics"));
		client.send(cmd);
	}

}
