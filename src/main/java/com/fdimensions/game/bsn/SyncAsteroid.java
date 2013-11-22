package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.*;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SyncNPC: utility class used to synchronize game start command after a certain period of time
 */
public class SyncAsteroid implements Runnable
{
	private DimensionServerExtension ext = null;
	public SyncAsteroid(DimensionServerExtension ext)
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
            ConcurrentHashMap<Integer,Asteroid> asteroids= system.getAsteroids();
            for(PlayerInfo pi : players.values()) {
                for (Asteroid asteroid: asteroids.values()) {
                    //straight line for now

                    //float angleToCenter = 3.13f;
                    //float distanceFromCenter = 100;
                    Vector2 pos = asteroid.getPos();
                    Vector2 vel = asteroid.getVelocity();
                    pos.x = pos.x + vel.x*3;
                    pos.y = vel.y*3;
                    //figure out what to do here.
                    //asteroid.setPos(new Vector2(asteroid.getPos().x, asteroid.getPos().y));
                    //asteroid.setVelocity(new Vector2(asteroid.getVelocity().x, asteroid.getVelocity().y));
                    positionData.addSFSObject(asteroid.getDimSFSObject());
                }
                if (positionData.size() > 0) {
                    //once we have all the position data for asteroids, send it off
                    ISFSObject retObj = new SFSObject();
                    retObj.putSFSArray("apd", positionData);
                    ext.updatePlayerForAsteroids(retObj, pi.getUser());

                    //then clear it for the next round.
                    positionData = new SFSArray();
                }
            }
        }
	}
}