package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/13/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class NPCShip extends Ship {

    private NPCInfo npcInfo;

    public NPCShip(NPCInfo npcInfo, int shipType, Vector2 pos, float angle, float thrust) {
        super(shipType, pos, angle, thrust);
        this.npcInfo = npcInfo;
    }

    public NPCInfo getNpcInfo() {
        return npcInfo;
    }

    public void setNpcInfo(NPCInfo npcInfo) {
        this.npcInfo = npcInfo;
    }

    @Override
    public void checkProximityToPlayer(PlayerInfo playerInfo) {
        if (playerInfo.getUser()!=null && playerProximityMap.get(playerInfo.getUser().getId()) == null) {
            Ship ship = playerInfo.getShip();
            float difx = Math.abs(ship.getPos().x-pos.x);
            float dify = Math.abs(ship.getPos().y-pos.y);
            if (difx < 900 || dify < 900) {
                playerProximityMap.put(playerInfo.getUser().getId(), playerInfo);
                playerInfo.getShip().npcProximityMap.put(npcInfo.getId(), npcInfo);
            }
        }
    }

    @Override
    public void checkProximityToNPC(NPCInfo otherNpcInfo) {
        if (playerProximityMap.get(otherNpcInfo.getId()) == null) {
            Ship ship = otherNpcInfo.getShip();
            float difx = Math.abs(ship.getPos().x-pos.x);
            float dify = Math.abs(ship.getPos().y-pos.y);
            if (difx < 900 || dify < 900) {
                npcProximityMap.put(otherNpcInfo.getId(), otherNpcInfo);
                otherNpcInfo.getShip().npcProximityMap.put(npcInfo.getId(), npcInfo);
            }
        }
    }

    @Override
    public ISFSObject getDimSFSObject() {
        if (null == sfsObject) {
            sfsObject = new SFSObject();
            sfsObject.putInt("nid", npcInfo.getId());
            sfsObject.putUtfString("name", npcInfo.getName());
            sfsObject.putInt("st", shipType);
        }
        sfsObject.putFloat("x", pos.x);
        sfsObject.putFloat("y", pos.y);
        Random r = new Random();
        float randomAngle = r.nextFloat()*((float)Math.PI);
        sfsObject.putFloat("a", randomAngle);
        //sfsObject.putFloat("a", angle);
        sfsObject.putFloat("t", thrust);
        return sfsObject;
    }
}
