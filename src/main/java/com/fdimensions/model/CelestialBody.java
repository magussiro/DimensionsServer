package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/4/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CelestialBody implements DimSFSObject {
    private int id;
    private Vector2 location;
    private String categoryType;
    private ISFSObject sbObject;

    public CelestialBody(int id, Vector2 location, String categoryType) {
        this.id = id;
        this.location = location;
        this.categoryType = categoryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public ISFSObject getDimSFSObject() {
        if (null == sbObject) {
            initStationaryBodySFSObject();
        }
        return sbObject;
    }

    private void initStationaryBodySFSObject(){
        sbObject = new SFSObject();
        sbObject.putInt("id", id);
        sbObject.putFloat("x", location.x);
        sbObject.putFloat("y", location.y);
        sbObject.putUtfString("ct", categoryType);
    }

}
