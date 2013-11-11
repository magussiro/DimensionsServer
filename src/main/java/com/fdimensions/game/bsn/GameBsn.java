package com.fdimensions.game.bsn;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class GameBsn
{

	/**
	 * Remove the user from the game
	 */
	public static void removeUserFromGame(User user, Room room, DimensionServerExtension extension)
	{
		// Get the game related to this room
		SpaceGame currGame = (SpaceGame) extension.getSystems().get(room.getId());

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
			}
		}

	}

	/**
	 *  Quit a running game (one of the player exits or disconnects)
	 */
	public static void quitGame(SpaceGame gameBean, DimensionServerExtension extension)
	{
		// Remove game from gameList
		extension.getSystems().remove(gameBean);

		// Destroy game objects
		gameBean.setPlayers(null);
		gameBean.setBaseGameMapBean(null);

		extension.trace("Battlefarm: game " + gameBean.getId() + " destroyed");

		gameBean = null;
	}
}