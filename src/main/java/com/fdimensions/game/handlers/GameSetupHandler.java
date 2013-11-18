package com.fdimensions.game.handlers;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.*;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
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

        int systemId = 2;   //(roomid) will eventually load from the db
        int shipType = 1;   //will eventually load from the db
        Vector2 startPosition = new Vector2(600, 100); //will eventually load from db
        SpaceGame currSystem = sphe.getSystems().get(systemId);
        if (currSystem == null) {
            return;
        }
        // Leave any room the player is currently in
        List<Room> rooms = player.getJoinedRooms();
        for(Room r: rooms) {
            getApi().leaveRoom(player, r);
        }

        //join room for current system
        if (!joinRoom(player, currSystem.getRoom())) return;

        //set player info to send
        PlayerInfo p = new PlayerInfo(currSystem, player);
        p.setShip(new PlayerShip(p, shipType, startPosition, 0, 0));
        currSystem.getPlayers().put(player.getId(), p);

        if (currSystem != null && player != null)
        {
            SpaceGameMap spm = currSystem.getSpaceGameMap();
            spm.setId(currSystem.getRoom().getId());
            spm.setShipPos(startPosition);
            spm.setShipType(shipType);

            ISFSObject resObj = spm.getDimSFSObject();
            send("setup_game_for_client", resObj, player);
        }
    }

    private boolean joinRoom(User player, Room room) {
        try {
            getApi().joinRoom(player, room);
        }
        catch (SFSJoinRoomException e) {
            e.printStackTrace();
            ISFSObject retObj = new SFSObject();
            retObj.putUtfString("msg", "Failed to Join Room: " + room.getName());
            send("error_msg", retObj, player);
            return false;
        }
        return true;
    }
}
