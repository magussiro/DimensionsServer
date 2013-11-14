package com.fdimensions.model;

import com.fdimensions.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/13/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ship {

    private int shipType;
    private Vector2 pos;
    private float angle;
    private float thrust;

    public Ship(int shipType, Vector2 pos, float angle, float thrust) {
        this.shipType = shipType;
        this.pos = pos;
        this.angle = angle;
        this.thrust = thrust;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getThrust() {
        return thrust;
    }

    public void setThrust(float thrust) {
        this.thrust = thrust;
    }
}
