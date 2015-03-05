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

package org.starmod.net.object.field;

import io.netty.buffer.ByteBuf;

public interface NetworkField<T> {

    /**
     * Gets the byte size of the field.
     *
     * @return size in bytes of the field
     */
    int getSize();

    /**
     * Gets the value of the field.
     *
     * @return object value
     */
    T getValue();

    /**
     * Sets the value of the field.
     *
     * @param value value of the field
     */
    void setValue(T value);

    /**
     * Whether the field has changed or not.
     *
     * @return true if changed
     */
    boolean isChanged();

    /**
     * Sets whether the field has changed or not.
     *
     * @param changed changed status
     */
    void setChanged(boolean changed);

    /**
     * Return if the field shall remain in the changed state.
     *
     * @return true if it is always changed
     */
    boolean keepChanged();

    /**
     * Sets the force changed status.
     *
     * @param keepChanged keepChanged
     */
    void setKeepChanged(boolean keepChanged);

    void encode(ByteBuf buf);

    void decode(ByteBuf buf);

}
