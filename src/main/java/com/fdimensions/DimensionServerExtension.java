package com.fdimensions;

import com.fdimensions.game.handlers.*;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DimensionServerExtension extends SFSExtension {
    private Map<Integer, PlayerInfo> playerInfos;
    private ConcurrentHashMap<Integer,SpaceGame> systems = null;

    @Override
    public void init() {
        playerInfos = new HashMap<Integer, PlayerInfo>();
        systems = new ConcurrentHashMap<Integer, SpaceGame>();

        this.addRequestHandler("gameSetup", GameSetupHandler.class);
        this.addRequestHandler("ships", ShipHandler.class);
        this.addRequestHandler("collisions", CollisionHandler.class);

        // Restart game
        //addRequestHandler("restart", RestartHandler.class);

        addEventHandler(SFSEventType.USER_JOIN_ROOM, UserJoinedEventHandler.class);
        addEventHandler(SFSEventType.USER_LEAVE_ROOM, UserLeavedEventHandler.class);
        addEventHandler(SFSEventType.USER_LOGOUT, UserDisconnectedEventHandler.class);
        addEventHandler(SFSEventType.USER_DISCONNECT, UserDisconnectedEventHandler.class);
    }

    public PlayerInfo getPlayerInfo(int playerId) {
        return playerInfos.get(playerId);
    }

    /**
     * Destroy
     */
    public void destroy()
    {
//        gameController.setTimeEventsRunning(false);
//        gameController = null;

        removeEventHandler(SFSEventType.USER_JOIN_ROOM);
        removeEventHandler(SFSEventType.USER_LEAVE_ROOM);
        removeEventHandler(SFSEventType.USER_LOGOUT);
        removeEventHandler(SFSEventType.USER_DISCONNECT);
//        removeRequestHandler(Commands.CMD_MAP_LIST);
//        removeRequestHandler(Commands.CMD_READY);
//        removeRequestHandler(Commands.CMD_RESTART);
//        removeRequestHandler(Commands.MV);
//        removeRequestHandler(Commands.BOMB);
    }

    public ConcurrentHashMap<Integer, SpaceGame> getSystems() {
        return systems;
    }

    public void startGame(List<User> players)
    {
        ISFSObject resObj = new SFSObject();
        send("GO", resObj, players);
    }
}