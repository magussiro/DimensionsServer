package com.fdimensions.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.game.bsn.SyncGameStart;
import com.smartfoxserver.v2.entities.User;


/**
 * GameBean: class describing a game match
 * 
 * @author Ing. Ignazio Locatelli
 * @version 1.0
 */
public class SpaceGame
{
	/** Game id, corresponding to system id */
	private int id = 0;
	private SpaceGameMap spaceGameMap;
	Integer master = null;
	Integer slave = null;
	private ConcurrentHashMap<Integer,PlayerInfo> players = null;
	private long gameStartTime = 0L;
	private Timer timer = null;
	private boolean started = false;
	private int matchDuration = 0;

	public SpaceGame(SpaceGameMap spaceGameMap, int id)
	{
		this.spaceGameMap = spaceGameMap;
		this.id = id;

		// Initialize internal data structure
		players = new ConcurrentHashMap<Integer,PlayerInfo>();

		// Reset game to its initial status
		reset(); 
	}
	
	/* GETTERS & SETTERS */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SpaceGameMap getSpaceGameMap() {
		return spaceGameMap;
	}

	public void setBaseGameMapBean(SpaceGameMap spaceGameMap) {
		this.spaceGameMap = spaceGameMap;
	}

	public Integer getMaster() {
		return master;
	}

	public void setMaster(Integer master) {
		this.master = master;
	}

	public Integer getSlave() {
		return slave;
	}

	public void setSlave(Integer slave) {
		this.slave = slave;
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
	 */
	public void startGame(List<User> recipients, DimensionServerExtension ext)
	{
		gameStartTime = System.currentTimeMillis() + 3500;
		started = true;

		// Wait a number of seconds and then notify clients that the game starts!
		timer = new Timer();
		timer.schedule(new SyncGameStart(ext, recipients), 3000);
	}
}