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

package org.starmod.net.command.sync;

import io.netty.buffer.ByteBuf;
import org.starmod.net.Command;
import org.starmod.net.Header;

public class Synchronize implements Command {

    private Header header;
    private ByteBuf buf;
    private int[] ids;
    private byte[] keys;

    public Synchronize(Header header, ByteBuf buf, int[] ids, byte[] keys) {
        this.header = header;
        this.buf = buf;
        this.ids = ids;
        this.keys = keys;
    }

    @Override
    public Header getHeader() {
        return header;
    }

    public ByteBuf getBuf() {
        return buf;
    }

    public int[] getIds() {
        return ids;
    }

    public byte[] getKeys() {
        return keys;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

}
