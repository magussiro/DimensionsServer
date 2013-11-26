package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.*;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SyncNPC: utility class used to synchronize game start command after a certain period of time
 */
public class SyncAsteroid implements Runnable
{
    Double time = 0.0;
	private DimensionServerExtension ext = null;
	public SyncAsteroid(DimensionServerExtension ext)
	{
		this.ext = ext;
	}

    public void run(Double d) {
        time = d;
        run();
    }

	public void run()
    {
        ConcurrentHashMap<Integer,SpaceGame> systems = ext.getSystems();
        //BIG O(N3), ouch
        for(SpaceGame system : systems.values()) {
            ConcurrentHashMap<Integer,PlayerInfo> players = system.getPlayers();
            List<Asteroid> asteroids= system.getSpaceGameMap().getAsteroids();
            for(PlayerInfo pi : players.values()) {
                ISFSArray positionData = new SFSArray();
                for (Asteroid asteroid: asteroids) {
                    //straight line for now
                    Vector2 startPos = asteroid.getStartPos();
                    double velMag = asteroid.getVelMag();
                    double disFromCenter = asteroid.getDistanceFromCenter();
                    double startAngle = asteroid.getStartAngle();

                    //(velMag*time*0.0174532925)

                    //position of asteroid as a function of time
                    double x = startPos.x - (disFromCenter*Math.sin(startAngle+(Math.PI/2))) + (disFromCenter*Math.sin(time+startAngle+(Math.PI/2)));
                    double y = startPos.y + (disFromCenter*Math.cos(startAngle+(Math.PI/2))) - (disFromCenter*Math.cos(time+startAngle+(Math.PI/2)));
                    Vector2 gravCenter = asteroid.getGravCenter();
                    asteroid.setCurPos(new Vector2((float)(x-gravCenter.x),(float)(y-gravCenter.y)));
                    positionData.addSFSObject(asteroid.getDimSFSObject());
                }
                if (positionData.size() > 0) {
                    //once we have all the position data for asteroids, send it off
                    ISFSObject retObj = new SFSObject();
                    retObj.putSFSArray("apd", positionData);
                    ext.updatePlayerForAsteroids(retObj, pi.getUser());
                }
            }
        }
        time += 3;
	}
}