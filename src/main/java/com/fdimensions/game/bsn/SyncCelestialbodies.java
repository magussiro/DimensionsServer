package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.model.NPCInfo;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.PlayerShip;
import com.fdimensions.model.SpaceGame;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SyncNPC: utility class used to synchronize game start command after a certain period of time
 */
public class SyncCelestialBodies extends TimerTask
{
	private DimensionServerExtension ext = null;
	public SyncCelestialBodies(DimensionServerExtension ext)
	{
		this.ext = ext;
	}

	public void run()
	{

	}
}