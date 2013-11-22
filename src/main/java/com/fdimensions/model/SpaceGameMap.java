package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 8:53 PM
 */
public class SpaceGameMap implements DimSFSObject{

    private int id;
    private ConcurrentHashMap<Integer, PlayerInfo> playerInfos;
    private List<CelestialBody> celestialBodies;
    private List<Asteroid> asteroids;
    private int systemRadius;
    private Vector2 shipPos;
    private int shipType;
    private String systemResources;
    private int backgroundImage;
    /** SFSObject representation ready to be used in responses to clients */
    private ISFSObject mapObject = null;

    public SpaceGameMap(int id,
                        List<CelestialBody> celestialBodies,
                        List<Asteroid> asteroids,
                        int systemRadius,
                        String systemResources,
                        int backgroundImage) {
        this.id = id;
        this.celestialBodies = celestialBodies;
        this.asteroids = asteroids;
        this.systemRadius = systemRadius;
        this.systemResources = systemResources;
        this.backgroundImage = backgroundImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConcurrentHashMap<Integer, PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    public void setPlayerInfos(ConcurrentHashMap<Integer, PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }

    public List<Asteroid> getAsteroidAreas() {
        return asteroids;
    }

    public void setAsteroidAreas(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public List<CelestialBody> getCelestialBodies() {
        return celestialBodies;
    }

    public void setCelestialBodies(List<CelestialBody> celestialBodies) {
        this.celestialBodies = celestialBodies;
    }

    public int getSystemRadius() {
        return systemRadius;
    }

    public void setSystemRadius(int systemRadius) {
        this.systemRadius = systemRadius;
    }

    public Vector2 getShipPos() {
        return shipPos;
    }

    public void setShipPos(Vector2 shipPos) {
        this.shipPos = shipPos;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public String getSystemResources() {
        return systemResources;
    }

    public void setSystemResources(String systemResources) {
        this.systemResources = systemResources;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public ISFSObject getDimSFSObject() {
        if (null == mapObject) {
            initMapObject();
        }
        return mapObject;
    }

    private void initMapObject() {
        mapObject = new SFSObject();
        mapObject.putInt("id", id);

        SFSArray sbds = new SFSArray();
        for(DimSFSObject bd: celestialBodies) {
            sbds.addSFSObject(bd.getDimSFSObject());
        }
        mapObject.putSFSArray("sbds", sbds);

        SFSArray abs = new SFSArray();
        for(DimSFSObject ab : asteroids) {
            abs.addSFSObject(ab.getDimSFSObject());
        }
        mapObject.putSFSArray("ast", abs);

        mapObject.putInt("rad", systemRadius);
        mapObject.putFloat("spx", shipPos.x);
        mapObject.putFloat("spy", shipPos.y);
        mapObject.putInt("st", shipType);
        mapObject.putUtfString("sysrec", systemResources);
        mapObject.putInt("bgi", backgroundImage);
    }

}
