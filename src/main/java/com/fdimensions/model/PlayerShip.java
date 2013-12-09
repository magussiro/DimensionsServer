package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/13/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerShip extends Ship implements Scannable, DimSFSObject {

    private PlayerInfo playerInfo;

    public PlayerShip(PlayerInfo playerInfo, int shipType, Vector2 pos, float angle, float thrust) {
        super(shipType, pos, angle, thrust);
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public boolean sendPositionUpdate(Integer playerId) {
        if (playerProximityMap.get(playerId)!=null) return true;
        return false;
    }

    public boolean sendNpcPositionUpdate(Integer npcId) {
        if (npcProximityMap.get(npcId)!=null) return true;
        return false;
    }

    @Override
    public void checkProximityToPlayer(PlayerInfo otherPlayerInfo) {
        if (otherPlayerInfo.getUser()!=null && playerProximityMap.get(otherPlayerInfo.getUser().getId()) == null) {
            Ship ship = otherPlayerInfo.getShip();
            float difx = Math.abs(ship.getPos().x-pos.x);
            float dify = Math.abs(ship.getPos().y-pos.y);
            if (difx < 900 || dify < 900) {
                playerProximityMap.put(otherPlayerInfo.getUser().getId(), otherPlayerInfo);
                otherPlayerInfo.getShip().playerProximityMap.put(playerInfo.getUser().getId(), playerInfo);
            }
        }
    }

    @Override
    public void checkProximityToNPC(NPCInfo npcInfo) {
        if (playerProximityMap.get(npcInfo.getId()) == null) {
            Ship ship = npcInfo.getShip();
            float difx = Math.abs(ship.getPos().x-pos.x);
            float dify = Math.abs(ship.getPos().y-pos.y);
            if (difx < 900 || dify < 900) {
                npcProximityMap.put(npcInfo.getId(), npcInfo);
                npcInfo.getShip().playerProximityMap.put(playerInfo.getUser().getId(), playerInfo);
            }
        }
    }

    @Override
    public ISFSObject getDimSFSObject() {
        ISFSObject sfsObject =  new SFSObject();
        sfsObject.putInt("id", playerInfo.getUser().getId());
        sfsObject.putUtfString("name", playerInfo.getUser().getName());
        sfsObject.putInt("st", shipType);
        sfsObject.putFloat("x", pos.x);
        sfsObject.putFloat("y", pos.y);
        sfsObject.putFloat("a", angle);
        sfsObject.putFloat("t", thrust);
        return sfsObject;
    }

    @Override
    public ISFSObject getUpdateDimSFSObject() {
        ISFSObject sfsObject =  new SFSObject();
        sfsObject.putInt("id", playerInfo.getUser().getId());
        sfsObject.putFloat("x", pos.x);
        sfsObject.putFloat("y", pos.y);
        sfsObject.putFloat("a", angle);
        sfsObject.putFloat("t", thrust);
        return sfsObject;
    }
}
