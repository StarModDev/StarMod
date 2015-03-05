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

import io.netty.buffer.ByteBuf;
import net.openhft.koloboke.collect.map.ByteObjMap;
import net.openhft.koloboke.collect.map.ObjByteMap;
import net.openhft.koloboke.collect.map.hash.HashByteObjMaps;
import net.openhft.koloboke.collect.map.hash.HashObjByteMaps;
import org.starmod.net.object.field.NetworkField;
import org.starmod.net.object.field.type.NetworkBoolean;
import org.starmod.net.object.field.type.NetworkByte;
import org.starmod.net.object.field.type.NetworkInteger;

import java.lang.reflect.Field;
import java.util.Arrays;

public class NetworkObject {

    // == Start Network Fields (These must be same name as StarMade's) ===
    public NetworkInteger id = new NetworkInteger(-123456);
    public NetworkInteger clientId = new NetworkInteger(-777777);
    public NetworkBoolean markedDeleted = new NetworkBoolean(false);
    public NetworkBoolean upgradedAccount = new NetworkBoolean(false);
    public NetworkByte debugMode = new NetworkByte((byte) 0);
    // == End ====================

    private boolean changed = true;

    private ByteObjMap<NetworkField> fields = HashByteObjMaps.newMutableMap();
    private ObjByteMap<NetworkField> fieldKeys = HashObjByteMaps.newMutableMap();

    public NetworkObject() {
        initialize();
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    private void initialize() {

        Field[] objectFields = getClass().getFields();
        String[] objectFieldNames = new String[objectFields.length];

        for (byte i = 0; i < objectFields.length; i++) {
            objectFieldNames[i] = objectFields[i].getName();
        }

        Arrays.sort(objectFieldNames); // Sort all the fields in alphabetical order

        byte key = 0;
        for (String name : objectFieldNames) {
            try {
                Field field = getClass().getField(name);
                if (NetworkField.class.isAssignableFrom(field.getType())) {
                    NetworkField netField = (NetworkField) field.get(this);
                    fields.put(key, netField);
                    fieldKeys.put(netField, key);
                    key++;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public static NetworkObject decode(ByteBuf buf, NetworkObject netObj) {

        byte size = buf.readByte();

        for (byte i = 0; i < size; i++) {

            byte fieldId = buf.readByte();

            NetworkField field = netObj.fields.get(fieldId);

            assert field != null: "Field in NetworkObject not found";
            field.decode(buf);

        }
        return netObj;
    }

    public static void encode(ByteBuf buf, NetworkObject remote, boolean force) {

        int size = 0;
        for (NetworkField field : remote.fields.values()) {
            if (field.isChanged() || force) {
                size++;
            }
        }

        buf.writeInt(0); // TODO: Write id generator for network objects

        buf.writeByte(0); // TODO: Write key generator

        buf.writeByte(size);

        remote.fields.values().stream().filter(field -> field.isChanged() || force).forEach(field -> {
            byte fieldId = remote.fieldKeys.getByte(field);
            buf.writeByte(fieldId);

            field.encode(buf);

            if (!field.keepChanged())
                field.setChanged(false);

        });

    }

}
