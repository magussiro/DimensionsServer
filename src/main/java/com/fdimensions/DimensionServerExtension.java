package com.fdimensions;

import com.fdimensions.game.bsn.StaticSpaceLevelGenerator;
import com.fdimensions.game.handlers.*;
import com.fdimensions.model.PlayerInfo;
import com.fdimensions.model.SpaceGame;
import com.fdimensions.model.SpaceGameMap;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DimensionServerExtension extends SFSExtension {
    private Map<Integer, PlayerInfo> playerInfos;
    private ConcurrentHashMap<Integer,SpaceGame> games = null;

    @Override
    public void init() {

        SpaceGameMap spm = StaticSpaceLevelGenerator.generateRandomLevel();

        this.addRequestHandler("gameSetup", GameSetupHandler.class);
        this.addRequestHandler("ships", ShipHandler.class);
        this.addRequestHandler("collisions", CollisionHandler.class);

        // Restart game
        //addRequestHandler("restart", RestartHandler.class);


        // REGISTER EVENT HANDLERS

        // Event handler: join room
        addEventHandler(SFSEventType.USER_JOIN_ROOM, UserJoinedEventHandler.class);

        // Event handler: user leave game room
        addEventHandler(SFSEventType.USER_LEAVE_ROOM, UserLeavedEventHandler.class);

        // Event handler: user log out
        addEventHandler(SFSEventType.USER_LOGOUT, UserDisconnectedEventHandler.class);

        // Event handler: user disconnect
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

        trace("BattleFarm extension destroyed");
    }

    public ConcurrentHashMap<Integer, SpaceGame> getGames() {
        return games;
    }

    public void startGame(List<User> players)
    {
        ISFSObject resObj = new SFSObject();
        send("GO", resObj, players);
    }
}
