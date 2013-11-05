package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;
import com.smartfoxserver.v2.entities.User;

import java.util.List;
import java.util.TimerTask;

/**
 * SyncGameStart: utility class used to synchronize game start command after a certain period of time
 * 
 * @author  Ing. Ignazio Locatelli
 * @version 1.0
 */
public class SyncGameStart extends TimerTask
{
	private List<User> players = null;
	private DimensionServerExtension ext = null;
	public SyncGameStart(DimensionServerExtension ext, List<User> players)
	{
		this.ext = ext;
		this.players = players;
	}

	public void run()
	{
		ext.startGame(players);
	}   
}