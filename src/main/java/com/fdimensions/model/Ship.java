package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/13/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Ship implements Scannable, DimSFSObject {
    protected int shipType;
    protected Vector2 pos;
    protected float angle;
    protected float thrust;
    protected Map<Integer,PlayerInfo> playerProximityMap;
    protected Map<Integer,NPCInfo> npcProximityMap;

    public Ship(int shipType, Vector2 pos, float angle, float thrust) {
        this.shipType = shipType;
        this.pos = pos;
        this.angle = angle;
        this.thrust = thrust;
        playerProximityMap = new HashMap<>();
        npcProximityMap = new HashMap<>();
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

    public Map<Integer, PlayerInfo> getPlayerProximityMap() {
        return playerProximityMap;
    }

    public void setPlayerProximityMap(Map<Integer, PlayerInfo> playerProximityMap) {
        this.playerProximityMap = playerProximityMap;
    }

    public Map<Integer, NPCInfo> getNpcProximityMap() {
        return npcProximityMap;
    }

    public void setNpcProximityMap(Map<Integer, NPCInfo> npcProximityMap) {
        this.npcProximityMap = npcProximityMap;
    }

    @Override
    public abstract void checkProximityToPlayer(PlayerInfo otherPlayerInfo);

    @Override
    public abstract void checkProximityToNPC(NPCInfo npcInfoInfo);

    @Override
    public abstract ISFSObject getDimSFSObject();
}
