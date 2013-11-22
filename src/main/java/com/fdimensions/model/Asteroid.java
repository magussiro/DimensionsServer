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
    private Vector2 pos;
    private Vector2 velocity;
    private int type;
    SFSObject sbObject;

    private Vector2 gravCenter;

    public Asteroid(int id, int hp, Vector2 pos, Vector2 velocity, int type) {
        this.id = id;
        this.hp = hp;
        this.pos = pos;
        this.velocity = velocity;
        this.type = type;
        this.gravCenter = new Vector2(0,0);
    }

    public Asteroid(int id, int hp, Vector2 pos, Vector2 velocity, int type, Vector2 gravCenter) {
        this.id = id;
        this.hp = hp;
        this.pos = pos;
        this.velocity = velocity;
        this.type = type;
        this.gravCenter = gravCenter;
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

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
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
        sbObject.putFloat("x", pos.x);
        sbObject.putFloat("y", pos.y);
        sbObject.putFloat("vx", velocity.x);
        sbObject.putFloat("vy", velocity.y);
        sbObject.putInt("t", type);
        sbObject.putInt("hp", hp);
    }
}
