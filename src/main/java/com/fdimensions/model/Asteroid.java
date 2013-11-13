package com.fdimensions.model;

import com.fdimensions.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/11/13
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Asteroid {
    private int size;
    private int hp;
    private Vector2 startPos;
    private Vector2 velocity;

    public Asteroid(int size, int hp, Vector2 startPos, Vector2 velocity) {
        this.size = size;
        this.hp = hp;
        this.startPos = startPos;
        this.velocity = velocity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Vector2 getStartPos() {
        return startPos;
    }

    public void setStartPos(Vector2 startPos) {
        this.startPos = startPos;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

}
