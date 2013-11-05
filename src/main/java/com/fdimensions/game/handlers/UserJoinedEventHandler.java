package com.fdimensions.game.handlers;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.game.bsn.StaticSpaceLevelGenerator;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;
import com.fdimensions.model.SpaceGameMap;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import java.util.ArrayList;

public class UserJoinedEventHandler extends BaseServerEventHandler {
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException
	{
		Room room = (Room) event.getParameter(SFSEventParam.ROOM);	

		if (room.isGame()) {

			// Get user that joined
			User user = (User) event.getParameter(SFSEventParam.USER);

			trace("BattleFarm: user " + user.getName() + " entered the game room '" + room.getName() + "' - game room id: " + room.getId());

			// Check if game already exist. If not, you have just entered a new room: create new game
			SpaceGame currGame = ((DimensionServerExtension) getParentExtension()).getGames().get(room.getId());

			if (currGame == null)
			{
				/*
				 * Note: global room variables cannot be set by the client; so we receive an idMap room variable that has been created
				 * by the client and remap it on a global room variable created server-side in order to be sure that all clients will receive this information
				 */
				int mapId = ((Integer) room.getVariable("map").getValue()).intValue();
				ArrayList<RoomVariable> roomVariables = new ArrayList<RoomVariable>();
				RoomVariable map = new SFSRoomVariable("idMap",mapId,true,false,true);
				roomVariables.add(map);
				this.getApi().setRoomVariables(user, room, roomVariables);

				SpaceGameMap gameMapBean = StaticSpaceLevelGenerator.generateRandomLevel();

				currGame = new SpaceGame(gameMapBean,room.getId());
				((DimensionServerExtension) (getParentExtension())).getGames().put(room.getId(),currGame);
				trace("BattleFarm: a new match was generated for room name '" + room.getName() + "' room id: " + room.getId() + "; selected map is RANDOM");

			} 

			if (currGame != null)
			{
				// Retrieve game map data
				SpaceGameMap gameMapBean = currGame.getSpaceGameMap();

				// Create player with starting coordinates
                PlayerInfo pi = new PlayerInfo(user.getId(), new Vector2(100,100),user);
                pi.setCurrentSystemId(currGame.getId());

                currGame.getPlayers().put(user.getId(), pi);
                currGame.setMaster(user.getId());
			}
		}
	}
}