package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsteroidArea implements DimSFSObject{
    private Vector2 spawnCenter;
    private List<Vector2> bounds;
    private List<Asteroid> asteroids;
    private int asteroidNum;
    private ISFSObject sbObject;

    public AsteroidArea(Vector2 spawnCenter, List<Vector2> bounds, int asteroidNum) {
        this.spawnCenter = spawnCenter;
        this.bounds = bounds;
        this.asteroidNum = asteroidNum;
    }

    public Vector2 getSpawnCenter() {
        return spawnCenter;
    }

    public void setSpawnCenter(Vector2 spawnCenter) {
        this.spawnCenter = spawnCenter;
    }

    public List<Vector2> getBounds() {
        return bounds;
    }

    public void setBounds(List<Vector2> bounds) {
        this.bounds = bounds;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public int getAsteroidNum() {
        return asteroidNum;
    }

    public void setAsteroidNum(int asteroidNum) {
        this.asteroidNum = asteroidNum;
    }

    private void initAsteroidBeltSFSObject(){
        sbObject = new SFSObject();
        sbObject.putFloat("cpx", spawnCenter.x);
        sbObject.putFloat("cpy", spawnCenter.y);
        for (int i = 0; i < bounds.size(); i++) {
            Vector2 boundPoint = bounds.get(i);
            sbObject.putFloat("bpx"+i, boundPoint.x);
            sbObject.putFloat("bpy"+i, boundPoint.y);
        }
        sbObject.putInt("num", asteroidNum);
    }

    @Override
    public ISFSObject getDimSFSObject() {
        if (sbObject == null) {
            initAsteroidBeltSFSObject();
        }
        return sbObject;
    }
}
