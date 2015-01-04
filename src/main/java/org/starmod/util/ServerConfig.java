package org.starmod.util;

import org.starmod.api.world.Sector;
import org.starmod.api.world.StarSystem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerConfig extends Properties {

	private File file;

	public ServerConfig(File file) {
		this.file = file;
		load();
	}

	public void load() {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			super.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			super.store(out, "StarMod Server Configuration File");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reload() {
		save();
		load();
	}

	public String getString(String key) {
		return getProperty(key);
	}

	public boolean getBoolean(String key) {
		return Boolean.valueOf(getProperty(key));
	}

	public int getInt(String key) {
		return Integer.valueOf(getProperty(key));
	}

	public double getDouble(String key) {
		return Double.valueOf(getProperty(key));
	}

	public float getFloat(String key) {
		return Float.valueOf(getProperty(key));
	}

	public Sector getSector(String systemKey, String sectorKey) {
		String[] system = getString(systemKey).split(",");
		String[] sector = getString(sectorKey).split(",");
		return new Sector(new StarSystem(Integer.valueOf(system[0]), Integer.valueOf(system[1]), Integer.valueOf(system[2])),
			Integer.valueOf(sector[0]), Integer.valueOf(sector[1]), Integer.valueOf(sector[2]));
	}

}
