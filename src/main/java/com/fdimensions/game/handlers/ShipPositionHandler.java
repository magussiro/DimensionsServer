package com.fdimensions.game.handlers;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.*;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShipPositionHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject positionParams) {
        DimensionServerExtension sphe =(DimensionServerExtension)getParentExtension();
        List<Room> rooms = player.getJoinedRooms();
        if (rooms.size() > 1) return;  //shouldn't be in more than one room
        Room room = rooms.get(0);
        SpaceGame game = sphe.getSystems().get(room.getId());
        PlayerInfo pi = game.getPlayers().get(player.getId());
        Vector2 newPos = new Vector2(positionParams.getFloat("x"), positionParams.getFloat("y"));
        pi.getShip().setPos(newPos);
        pi.getShip().setAngle(positionParams.getFloat("a"));
        pi.getShip().setThrust(positionParams.getFloat("t"));

        //catch any new ships that come into range (once scanned the player will recieve position updates from them until they leave the system or cloak or something.)
        scan(pi, game.getPlayers(), game.getNpcs());

        //send position update to all players who have scanned this ship
        sendUpdates(pi.getUser(), game.getPlayers(), game.getNpcs());
    }

    private void scan(PlayerInfo playerInfo, ConcurrentHashMap<Integer, PlayerInfo> playerInfos, ConcurrentHashMap<Integer, NPCInfo> npcInfos) {
        Ship ship = playerInfo.getShip();
        Integer userId = playerInfo.getUser().getId();
        for(PlayerInfo pi : playerInfos.values()) {
            if (pi.getUser().getId() != userId) //ignore the check for yourself.
                ship.checkProximityToPlayer(pi);
        }
        for(NPCInfo ni : npcInfos.values()) {
            ship.checkProximityToNPC(ni);
        }
    }

    private void sendUpdates(User user, ConcurrentHashMap<Integer, PlayerInfo> playerInfos, ConcurrentHashMap<Integer, NPCInfo> npcInfos) {
        Integer userId = user.getId();
        ISFSArray positionData = new SFSArray();
        for(PlayerInfo pi : playerInfos.values()) {
            if (((PlayerShip)pi.getShip()).sendPositionUpdate(userId)) {
                positionData.addSFSObject(pi.getShip().getDimSFSObject());
            }
        }
        ISFSObject retObj = new SFSObject();
        retObj.putSFSArray("positionData", positionData);
        send("ship_position_data", retObj, user);



        //for(NPCInfo ni : npcInfos.values()) {
           //TODO update npcInfos in memory
        //}

    }
}
