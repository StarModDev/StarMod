package org.starmod.entity.meta;

import com.gravypod.nbt.NBTFile;

import java.io.File;

public abstract class EntityProfile extends NBTFile {

	public EntityProfile(File file) {
		super(file);
		if (isNewFile()) loadDefaults();
	}

	public abstract void fromNBT();

	public abstract void toNBT();

	public abstract void loadDefaults();

}
