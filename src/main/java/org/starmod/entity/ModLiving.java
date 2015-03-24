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

package org.starmod.entity;

import org.starmod.api.entity.Living;

public class ModLiving extends ModEntity implements Living {

    private float health;
    private float maxHealth;
    private float lastDamage;
    private Living lastAttacker;
    private int killCount;
    private int deathCount;

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void damage(float amount) {
        if (amount > health)
            health = 0;
        else
            health -= amount;
    }

    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public float getLastDamage() {
        return lastDamage;
    }

    @Override
    public void setLastDamage(float lastDamage) {
        this.lastDamage = lastDamage;
    }

    @Override
    public Living getLastAttacker() {
        return lastAttacker;
    }

    @Override
    public void setLastAttacker(Living lastAttacker) {
        this.lastAttacker = lastAttacker;
    }

    @Override
    public int getKillCount() {
        return killCount;
    }

    @Override
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    @Override
    public int getDeathCount() {
        return deathCount;
    }

    @Override
    public void setDeathCount(int deaths) {
        this.deathCount = deaths;
    }

}
