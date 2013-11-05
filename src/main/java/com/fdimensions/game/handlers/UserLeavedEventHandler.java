package com.fdimensions.game.handlers;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.game.bsn.GameBsn;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

/**
 * UserLeavedEventHandler: manager user leaved events (user exiting a game room)
 * 
 * @author Ing. Ignazio Locatelli
 * @version 1.0
 */
public class UserLeavedEventHandler extends BaseServerEventHandler
{
	/**
	 * Handle user leaving a room
	 * 
	 * @param event The received event
	 */
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException
	{
		// Get room
		Room room = (Room) event.getParameter(SFSEventParam.ROOM);

		// Get user that leaved
		User user = (User) event.getParameter(SFSEventParam.USER);
		trace("BattleFarm: user " + user.getName() + " left the game");

		// Process remove user from game
		if (room != null)
			GameBsn.removeUserFromGame(user, room, (DimensionServerExtension) getParentExtension());
	}
}