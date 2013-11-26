package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.model.NPCInfo;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.PlayerShip;
import com.fdimensions.model.SpaceGame;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SyncNPC: utility class used to synchronize game start command after a certain period of time
 */
public class SyncNPC implements Runnable
{
	private DimensionServerExtension ext = null;
	public SyncNPC(DimensionServerExtension ext)
	{
		this.ext = ext;
	}

	public void run()
	{
        ISFSArray positionData = new SFSArray();
        ConcurrentHashMap<Integer,SpaceGame> systems = ext.getSystems();

        //BIG O(N3), ouch
        for(SpaceGame system : systems.values()) {
            ConcurrentHashMap<Integer,PlayerInfo> players = system.getPlayers();
            for(PlayerInfo pi : players.values()) {
                Map<Integer, NPCInfo> npcsForPlayer = pi.getShip().getNpcProximityMap();
                for (NPCInfo ni: npcsForPlayer.values()) {
                    if (((PlayerShip)pi.getShip()).sendNpcPositionUpdate(ni.getId())) {
                        positionData.addSFSObject(ni.getShip().getDimSFSObject());
                    }
                }
                if (positionData.size() > 0) {
                    //once we have all the position data for npcs for the player, send it off
                    ISFSObject retObj = new SFSObject();
                    retObj.putSFSArray("positionData", positionData);
                    ext.updatePlayerForNPC(retObj, pi.getUser());

                    //then clear it for the next round.
                    positionData = new SFSArray();
                }
            }
        }
	}
}