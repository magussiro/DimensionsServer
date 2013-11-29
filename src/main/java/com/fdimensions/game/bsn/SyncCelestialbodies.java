package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;

import java.util.TimerTask;

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