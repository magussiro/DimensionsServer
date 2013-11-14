package com.fdimensions.model;

import com.fdimensions.DimensionServerExtension;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;


/**
 * GameBean: class describing a game match
 * 
 * @author Ing. Ignazio Locatelli
 * @version 1.0
 */
public class SpaceGame
{
    private Room room;
	private SpaceGameMap spaceGameMap;
	private ConcurrentHashMap<Integer,PlayerInfo> players = null;

	private long gameStartTime = 0L;
	private Timer timer = null;
	private boolean started = false;
	private int matchDuration = 0;

	public SpaceGame(SpaceGameMap spaceGameMap, Room room)
	{
		this.spaceGameMap = spaceGameMap;
		this.room = room;

		// Initialize internal data structure
		players = new ConcurrentHashMap<>();

		// Reset game to its initial status
		reset(); 
	}

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public SpaceGameMap getSpaceGameMap() {
		return spaceGameMap;
	}

	public void setBaseGameMapBean(SpaceGameMap spaceGameMap) {
		this.spaceGameMap = spaceGameMap;
	}

	public ConcurrentHashMap<Integer, PlayerInfo> getPlayers() {
		return players;
	}

	public void setPlayers(ConcurrentHashMap<Integer, PlayerInfo> players) {
		this.players = players;
	}

	public long getGameStartTime() {
		return gameStartTime;
	}

	public void setGameStartTime(long gameStartTime) {
		this.gameStartTime = gameStartTime;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public int getMatchDuration() {
		return matchDuration;
	}

	public void setMatchDuration(int matchDuration) {
		this.matchDuration = matchDuration;
	}
	
	/* PUBLIC METHODS */

	/**
	 * Reset the game to its initial status
	 */
	public void reset()
	{
		started = false;
		matchDuration = 15;

		// Reset player score
//		for (Enumeration<PlayerInfo> e = players.elements(); e.hasMoreElements();)
//		{
//            PlayerInfo player = (PlayerInfo) e.nextElement();
//			player.setScore(0);
//		}
	}

	/** 
	 * Start a new game
     *             // Set the start time of the game ahead of 3 1/2 seconds to compensate the delay
	 */
	public void startGame(List<User> recipients, DimensionServerExtension ext)
	{
//		gameStartTime = System.currentTimeMillis() + 3500;
//		started = true;
//
//		// Wait a number of seconds and then notify clients that the game starts!
//		timer = new Timer();
//		timer.schedule(new SyncGameStart(ext, recipients), 3000);
	}
}