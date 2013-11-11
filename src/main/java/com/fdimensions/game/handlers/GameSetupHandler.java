package com.fdimensions.game.handlers;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;
import com.fdimensions.model.SpaceGameMap;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameSetupHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject positionParams) {
        DimensionServerExtension sphe =(DimensionServerExtension)getParentExtension();
        // Retrieve user's room
        Room room = null;
        List<Room> rooms = player.getJoinedRooms();

        if (rooms.size() > 0) room = rooms.get(0);  // User can be only in 1 room at a time

        // Get game bean
        SpaceGame gameBean = sphe.getSystems().get(room.getId());

        if (gameBean != null && player != null)
        {
            PlayerInfo p = gameBean.getPlayers().get(player.getId());
            if (null == p) {
                //Player Doesn't Exist.
                return;
            }
            p.setReadyToPlay(true);

            // Retrieve map data and send to players
            SpaceGameMap spm = gameBean.getSpaceGameMap();

            // Retrieve pre-generated SFSObject
            ISFSObject resObj = spm.getDimSFSObject();

            // Add information relative to master and slave
            //resObj.putSFSObject("map",resObj);

            // Send map data to player
            send("map_data", resObj, player);

            // Set the start time of the game ahead of 3 1/2 seconds to compensate the delay
            gameBean.startGame(room.getUserList(),(DimensionServerExtension)getParentExtension());
        }
    }
}
