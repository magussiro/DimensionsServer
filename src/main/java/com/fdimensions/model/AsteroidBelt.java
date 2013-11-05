package com.fdimensions.model;

import com.fdimensions.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsteroidBelt {

    private Vector2 ringRadii;
    private int asteroidNum;

    public AsteroidBelt(Vector2 ringRadii, int asteroidNum) {
        this.ringRadii = ringRadii;
        this.asteroidNum = asteroidNum;
    }

    public Vector2 getRingRadii() {
        return ringRadii;
    }

    public void setRingRadii(Vector2 ringRadii) {
        this.ringRadii = ringRadii;
    }

    public int getAsteroidNum() {
        return asteroidNum;
    }

    public void setAsteroidNum(int asteroidNum) {
        this.asteroidNum = asteroidNum;
    }

}
