package com.fdimensiontests.sync;

import com.fdimensions.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/26/13
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class SyncAsteroidTestInfo {

    private Vector2 startPos;
    private Double testTimePassed;
    private Vector2 expectedEndPos;
    private float velMag;

    public SyncAsteroidTestInfo(Vector2 startPos, Double testTimePassed, Vector2 expectedEndPos, float velMag) {
        this.startPos = startPos;
        this.testTimePassed = testTimePassed;
        this.expectedEndPos = expectedEndPos;
        this.velMag = velMag;
    }

    public Vector2 getStartPos() {
        return startPos;
    }

    public void setStartPos(Vector2 startPos) {
        this.startPos = startPos;
    }

    public Double getTestTimePassed() {
        return testTimePassed;
    }

    public void setTestTimePassed(Double testTimePassed) {
        this.testTimePassed = testTimePassed;
    }

    public Vector2 getExpectedEndPos() {
        return expectedEndPos;
    }

    public void setExpectedEndPos(Vector2 expectedEndPos) {
        this.expectedEndPos = expectedEndPos;
    }

    public float getVelMag() {
        return velMag;
    }

    public void setVelMag(float velMag) {
        this.velMag = velMag;
    }
}
