package com.fdimensions.model;

import com.fdimensions.math.Vector2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpaceGameMap {

    private List<StationaryBody> stationaryBodies;
    private List<AsteroidBelt> asteroidBelts;
    private int systemRadius;

    public SpaceGameMap(List<StationaryBody> stationaryBodies, List<AsteroidBelt> asteroidBelts, int systemRadius) {
        this.stationaryBodies = stationaryBodies;
        this.asteroidBelts = asteroidBelts;
        this.systemRadius = systemRadius;
    }

    public List<AsteroidBelt> getAsteroidBelts() {
        return asteroidBelts;
    }

    public void setAsteroidBelts(List<AsteroidBelt> asteroidBelts) {
        this.asteroidBelts = asteroidBelts;
    }

    public List<StationaryBody> getStationaryBodies() {
        return stationaryBodies;
    }

    public void setStationaryBodies(List<StationaryBody> stationaryBodies) {
        this.stationaryBodies = stationaryBodies;
    }

    public int getSystemRadius() {
        return systemRadius;
    }

    public void setSystemRadius(int systemRadius) {
        this.systemRadius = systemRadius;
    }

}
