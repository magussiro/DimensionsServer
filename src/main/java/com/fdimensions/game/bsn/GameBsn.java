package com.fdimensions.game.bsn;

import java.util.LinkedList;
import java.util.Random;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

/**
 * GameBsn: class containing utility business classes for game processing
 * 
 * @author Ing. Ignazio Locatelli
 * @version 1.0
 */
public class GameBsn
{

	/**
	 * Remove the user from the game
	 * 
	 * @param user			User to be removed
	 * @param room			Room from which the user will be remove (room.id = game.id)
	 * @param extension		Reference to main battlefarm extension
	 */
	public static void removeUserFromGame(User user, Room room, DimensionServerExtension extension)
	{
		// Get the game related to this room
		SpaceGame currGame = (SpaceGame) extension.getGames().get(room.getId());

		if (currGame != null)
		{
			// Get the player that was gone and then delete it
			PlayerInfo pi = currGame.getPlayers().get(user.getId());
			if (pi != null)
			{
				currGame.getPlayers().remove(user.getId());

				// If one player is still alive, notify him and reset the game status
				if (currGame.getPlayers().size() > 0)
                {
					// Reset remaining player
                    //TODO manage scores
					//remaining.setScore(0);

					currGame.reset();

					// Notify the remaining user
					ISFSObject resObj = new SFSObject();
					resObj.putUtfString("_cmd","stop");

					// Send to remaining user
					extension.send("stop", resObj, pi.getUser());

				}
				else
				{
					GameBsn.quitGame(currGame,extension);
				}
			}
		}

	}

	/**
	 *  Quit a running game (one of the player exits or disconnects)
	 *  
	 * @param gameBean		Game Object describing the game from which the user has exited
	 * @param extension		Reference to the main Battlefarm extension
	 */
	public static void quitGame(SpaceGame gameBean, DimensionServerExtension extension)
	{
		// Remove game from gameList
		extension.getGames().remove(gameBean);

		// Destroy game objects
		gameBean.setPlayers(null);
		gameBean.setBaseGameMapBean(null);

		extension.trace("Battlefarm: game " + gameBean.getId() + " destroyed");

		gameBean = null;
	}
}