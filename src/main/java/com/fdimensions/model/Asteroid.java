package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/11/13
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Asteroid implements DimSFSObject{
    private int id;
    private int hp;
    private Vector2 startPos;
    private Vector2 curPos;
    private Vector2 velocity;
    private int type;
    SFSObject sbObject;

    private Vector2 gravCenter;
    private double distanceFromCenter;
    private double velMag;
    private double startAngle;

    public Asteroid(int id, int hp, Vector2 startPos, Vector2 velocity, int type) {
        this.id = id;
        this.hp = hp;
        this.startPos = startPos;
        this.curPos = startPos;
        this.velocity = velocity;
        velMag = Math.sqrt(Math.pow(velocity.x,2)+Math.pow(velocity.y,2));
        this.type = type;
        this.gravCenter = new Vector2(0,0);
        startAngle = Math.atan2((startPos.y-gravCenter.y), (startPos.x-gravCenter.x));
        distanceFromCenter = Math.sqrt(Math.pow(startPos.x, 2) + Math.pow(startPos.y, 2));
    }

    public Asteroid(int id, int hp, Vector2 startPos, Vector2 velocity, int type, Vector2 gravCenter) {
        this.id = id;
        this.hp = hp;
        this.startPos = startPos;
        this.velocity = velocity;
        velMag = Math.sqrt(Math.pow(velocity.x,2)+Math.pow(velocity.y,2));
        this.type = type;
        this.gravCenter = gravCenter;
        startAngle = Math.atan2((startPos.x-gravCenter.x), (startPos.y-gravCenter.y));
        distanceFromCenter = Math.sqrt(Math.pow(startPos.x-gravCenter.x, 2) + Math.pow(startPos.y-gravCenter.y, 2));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Vector2 getGravCenter() {
        return gravCenter;
    }

    public void setGravCenter(Vector2 gravCenter) {
        this.gravCenter = gravCenter;
    }

    public double getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(double distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

    public double getVelMag() {
        return velMag;
    }

    public void setVelMag(double velMag) {
        this.velMag = velMag;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public Vector2 getCurPos() {
        return curPos;
    }

    public void setCurPos(Vector2 curPos) {
        this.curPos = curPos;
    }

    @Override
    public ISFSObject getDimSFSObject() {
        if (null == sbObject) {
            initStationaryBodySFSObject();
        }
        return sbObject;
    }

    private void initStationaryBodySFSObject(){
        sbObject = new SFSObject();
        sbObject.putInt("id", id);
        sbObject.putFloat("x", curPos.x);
        sbObject.putFloat("y", curPos.y);
        sbObject.putFloat("vx", velocity.x);
        sbObject.putFloat("vy", velocity.y);
        sbObject.putFloat("gx", gravCenter.x);
        sbObject.putFloat("gy", gravCenter.y);
        sbObject.putInt("t", type);
        sbObject.putInt("hp", hp);
    }
}
