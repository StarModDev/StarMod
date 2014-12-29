package org.starmod.util;

public class Vector3i {

	public int x;
	public int y;
	public int z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int[] toArray() {
		return new int[] {x, y, z};
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector3i) {
			Vector3i v = (Vector3i) obj;
			return x == v.x && y == v.y && z == v.z;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Vector3i{" +
			"x=" + x +
			", y=" + y +
			", z=" + z +
			'}';
	}

}
