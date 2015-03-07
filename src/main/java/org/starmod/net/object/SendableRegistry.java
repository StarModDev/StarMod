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

package org.starmod.net.object;

import net.openhft.koloboke.collect.map.ByteObjMap;
import net.openhft.koloboke.collect.map.ObjByteMap;
import net.openhft.koloboke.collect.map.hash.HashByteObjMaps;
import net.openhft.koloboke.collect.map.hash.HashObjByteMaps;
import org.starmod.net.util.IDGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SendableRegistry {

    private static final ByteObjMap<Class<? extends Sendable>> sendableClassMap = HashByteObjMaps.newMutableMap();
    private static final ObjByteMap<Class<? extends Sendable>> sendableClassKeyMap = HashObjByteMaps.newMutableMap();

    public static void add(Class<? extends Sendable> sendableClass) {
        if (!sendableClassKeyMap.containsKey(sendableClass)) {
            byte key = (byte) IDGenerator.nextSendableKey();

            sendableClassMap.put(key, sendableClass);
            sendableClassKeyMap.put(sendableClass, key);
        }
    }

    public static Class<? extends Sendable> getClass(byte key) {
        assert sendableClassMap.containsKey(key);
        return sendableClassMap.get(key);
    }

    public static byte getKey(Class<? extends Sendable> sendableClass) {
        assert sendableClassKeyMap.containsKey(sendableClass);
        return sendableClassKeyMap.getByte(sendableClass);
    }

    public static Sendable getSendable(byte key) {
        Class<? extends Sendable> sendableClass = getClass(key);
        try {
            Constructor ctr = sendableClass.getConstructor();
            return (Sendable) ctr.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
