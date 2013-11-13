package com.fdimensions;

import com.fdimensions.game.handlers.*;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.*;
import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DimensionServerExtension extends SFSExtension {
    private Map<Integer, PlayerInfo> playerInfos;
    private ConcurrentHashMap<Integer,SpaceGame> systems;

    @Override
    public void init() {
        playerInfos = new HashMap<>();
        systems = generateSystems();

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

    private ConcurrentHashMap<Integer, SpaceGame> generateSystems() {
        ConcurrentHashMap<Integer, SpaceGame> chm = new ConcurrentHashMap<>();
        Room solSystem = getParentZone().getRoomByName("SolSystem");
        SpaceGameMap sgm = createSolSystemMap(solSystem);
        SpaceGame game = new SpaceGame(sgm, solSystem);
        chm.put(1, game);
        return chm;
    }

    private SpaceGameMap createSolSystemMap(Room system) {
        List<Vector2> bounds = new ArrayList<>();
        bounds.add(new Vector2(300, -150));
        bounds.add(new Vector2(600,150));
        List<AsteroidArea> asteroidAreas = new ArrayList<>();
        AsteroidArea ab = new AsteroidArea(new Vector2(450, 0), bounds, 10);
        asteroidAreas.add(ab);

        List<CelestialBody> cbs = new ArrayList<CelestialBody>();
        cbs.add(new CelestialBody(1, new Vector2(0,0), 1, 300));
        cbs.add(new CelestialBody(2, new Vector2(600,100), 2, 50));

        return new SpaceGameMap(system.getId(), cbs, asteroidAreas, 10000, "1,2", 1);
    }

    public PlayerInfo getPlayerInfo(int playerId) {
        return playerInfos.get(playerId);
    }

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