package org.starmod;

import com.gravypod.nbt.NBT;
import org.starmod.net.Client;
import org.starmod.util.Vector3i;

import javax.vecmath.Vector3f;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Player {

	private int id;
	private int credits;
	private String name;
	private Client client;
	private Vector3f spawnPoint;
	private Vector3i currentSector;

	public Player(int id, int credits, String name, Client client, Vector3f spawnPoint, Vector3i currentSector) {
		this.id = id;
		this.credits = credits;
		this.name = name;
		this.client = client;
		this.spawnPoint = spawnPoint;
		this.currentSector = currentSector;
	}

	public int getId() {
		return id;
	}

	public int getCredits() {
		return credits;
	}

	public String getName() {
		return name;
	}

	public Client getClient() {
		return client;
	}

	public Vector3f getSpawnPoint() {
		return spawnPoint;
	}

	public Vector3i getCurrentSector() {
		return currentSector;
	}

	public static Player load(Client client) {
		Player player = null;
		try {
			RandomAccessFile playerFile = new RandomAccessFile(new File("./world/players/" + client.getName() + ".ent"), "rw");
			int credits = (int) NBT.readNBTChannel(playerFile).getValue();
			Vector3f spawn = (Vector3f) NBT.readNBTChannel(playerFile).getValue();
			Vector3i sector = (Vector3i) NBT.readNBTChannel(playerFile).getValue();
			player = new Player(client.getId(), credits, "", client, spawn, sector);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return player;
	}

}
