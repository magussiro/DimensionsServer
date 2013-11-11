package com.fdimensions.model;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpaceGameMap implements DimSFSObject{

    private List<StationaryBody> stationaryBodies;
    private List<AsteroidArea> asteroidAreas;
    private int systemRadius;
    /** SFSObject representation ready to be used in responses to clients */
    private ISFSObject mapObject = null;

    public SpaceGameMap(List<StationaryBody> stationaryBodies, List<AsteroidArea> asteroidAreas, int systemRadius) {
        this.stationaryBodies = stationaryBodies;
        this.asteroidAreas = asteroidAreas;
        this.systemRadius = systemRadius;
    }

    public List<AsteroidArea> getAsteroidAreas() {
        return asteroidAreas;
    }

    public void setAsteroidAreas(List<AsteroidArea> asteroidAreas) {
        this.asteroidAreas = asteroidAreas;
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

    public ISFSObject getDimSFSObject() {
        if (null == mapObject) {
            initMapObject();
        }
        return mapObject;
    }

    private void initMapObject() {
        mapObject = new SFSObject();
        SFSArray sbds = new SFSArray();
        for(DimSFSObject bd: stationaryBodies) {
            sbds.addSFSObject(bd.getDimSFSObject());
        }
        mapObject.putSFSArray("sbds", sbds);

        SFSArray abs = new SFSArray();
        for(DimSFSObject ab : asteroidAreas) {
            abs.addSFSObject(ab.getDimSFSObject());
        }
        mapObject.putSFSArray("abs", abs);

        mapObject.putInt("rad", systemRadius);
    }

}
