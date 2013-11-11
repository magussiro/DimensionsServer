package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsteroidArea implements DimSFSObject{

    private ISFSObject sbObject;
    private Vector2 xy1;
    private Vector2 xy2;
    private int asteroidNum;

    public AsteroidArea(Vector2 xy1, Vector2 xy2, int asteroidNum) {
        this.xy1 = xy1;
        this.xy2 = xy2;
        this.asteroidNum = asteroidNum;
    }

    public Vector2 getXy1() {
        return xy1;
    }

    public void setXy1(Vector2 xy1) {
        this.xy1 = xy1;
    }

    public Vector2 getXy2() {
        return xy2;
    }

    public void setXy2(Vector2 xy2) {
        this.xy2 = xy2;
    }

    public int getAsteroidNum() {
        return asteroidNum;
    }

    public void setAsteroidNum(int asteroidNum) {
        this.asteroidNum = asteroidNum;
    }

    public ISFSObject getDimSFSObject() {
        if (null == sbObject) {
            initAsteroidBeltSFSObject();
        }
        return sbObject;
    }

    private void initAsteroidBeltSFSObject(){
        sbObject = new SFSObject();
        sbObject.putFloat("x1", xy1.x);
        sbObject.putFloat("y1", xy1.y);
        sbObject.putFloat("x2", xy2.x);
        sbObject.putFloat("y2", xy2.y);
        sbObject.putInt("num", asteroidNum);
    }

}
