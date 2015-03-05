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

package org.starmod.net.object.field.type;

import io.netty.buffer.ByteBuf;
import org.starmod.net.object.field.AbstractNetworkField;

import java.nio.charset.StandardCharsets;

public class NetworkString extends AbstractNetworkField<String> {

    public NetworkString(String value) {
        super(value);
    }

    public NetworkString(String value, boolean changed) {
        super(value, changed);
    }

    @Override
    public int getSize() {
        return getValue().length() + Integer.BYTES;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(getValue().length());

        byte[] chars = getValue().getBytes(StandardCharsets.UTF_8);
        buf.writeBytes(chars);
    }

    @Override
    public void decode(ByteBuf buf) {
        byte[] chars = new byte[buf.readInt()];
        buf.readBytes(chars);

        setValue(new String(chars, StandardCharsets.UTF_8));
    }

}
