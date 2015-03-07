/* This file is part of StarMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) StarMod <http://starmod.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.starmod;

import net.openhft.koloboke.collect.map.IntObjMap;
import net.openhft.koloboke.collect.map.hash.HashIntObjMaps;
import org.starmod.net.object.NetworkObject;
import org.starmod.net.object.Sendable;
import org.starmod.util.MapUtils;

public class ServerRegistry {

    private static final IntObjMap<NetworkObject> remote = HashIntObjMaps.newUpdatableMap();
    private static final IntObjMap<Sendable> local = HashIntObjMaps.newUpdatableMap();

    public static NetworkObject getRemote(int key) {
        return remote.get(key);
    }

    public static int getRemote(NetworkObject value) {
        return MapUtils.getKeyByValue(remote, value);
    }

    public static void addRemote(int key, NetworkObject value) {
        remote.put(key, value);
    }

    public static boolean containsRemote(int key) {
        return remote.containsKey(key);
    }

    public static boolean containsRemote(NetworkObject value) {
        return remote.containsValue(value);
    }

    public static Sendable getLocal(int key) {
        return local.get(key);
    }

    public static int getLocal(Sendable value) {
        return MapUtils.getKeyByValue(local, value);
    }

    public static void addLocal(int key, Sendable value) {
        local.put(key, value);
    }

    public static boolean containsLocal(int key) {
        return local.containsKey(key);
    }

    public static boolean containsLocal(Sendable value) {
        return local.containsValue(value);
    }

    public static boolean same() {
        return remote.keySet().equals(local.keySet());
    }

}
