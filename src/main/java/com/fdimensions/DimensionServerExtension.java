package com.fdimensions;

import com.fdimensions.game.handlers.*;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.*;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DimensionServerExtension extends SFSExtension {
    private ConcurrentHashMap<Integer,SpaceGame> systems;

    @Override
    public void init() {
        systems = generateSystems();

        this.addRequestHandler("gameSetup", GameSetupHandler.class);
        this.addRequestHandler("shipPosition", ShipPositionHandler.class);
        this.addRequestHandler("collisions", CollisionHandler.class);

        // Restart game
        //addRequestHandler("restart", RestartHandler.class);

        addEventHandler(SFSEventType.USER_JOIN_ROOM, UserJoinedEventHandler.class);
        addEventHandler(SFSEventType.USER_LEAVE_ROOM, UserLeavedEventHandler.class);
        addEventHandler(SFSEventType.USER_LOGOUT, UserDisconnectedEventHandler.class);
        addEventHandler(SFSEventType.USER_DISCONNECT, UserDisconnectedEventHandler.class);

        for (SpaceGame system : systems.values()) {
            system.startGame(this);
        }
    }

    protected ConcurrentHashMap<Integer, SpaceGame> generateSystems() {
        ConcurrentHashMap<Integer, SpaceGame> chm = new ConcurrentHashMap<>();
        Room solSystem = getParentZone().getRoomByName("SolSystem");
        SpaceGameMap sgm = createSolSystemMap(solSystem);
        SpaceGame game = new SpaceGame(sgm, solSystem);

        //CREATE TEST PLAYERS
        createTestNpcs(game);
        chm.put(solSystem.getId(), game);

        Room asteroids1 = getParentZone().getRoomByName("Asteroids1");
        sgm = createAsteroids1SystemMap(asteroids1);
        game = new SpaceGame(sgm, asteroids1);
        chm.put(asteroids1.getId(), game);
        return chm;
    }

    protected SpaceGameMap createSolSystemMap(Room system) {
        List<Asteroid> asteroids = new ArrayList<>();
        asteroids.add(new Asteroid(1, 10, new Vector2(5000,5000), new Vector2(0,0), 1));
        asteroids.add(new Asteroid(2, 10, new Vector2(-5000,5000), new Vector2(0,0), 1));
        asteroids.add(new Asteroid(3, 10, new Vector2(5000,-5000), new Vector2(0,0), 1));
        asteroids.add(new Asteroid(4, 10, new Vector2(-5000,-5000), new Vector2(0,0), 1));
        asteroids.add(new Asteroid(5, 10, new Vector2(6000,4000), new Vector2(0,0), 1));
        asteroids.add(new Asteroid(6, 10, new Vector2(4000,6000), new Vector2(0,0), 1));

        List<CelestialBody> cbs = new ArrayList<>();
        cbs.add(new CelestialBody(1, new Vector2(0,0), "3,1"));
        cbs.add(new CelestialBody(2, new Vector2(1500,1500), "4,1")); //mercury
        cbs.add(new CelestialBody(3, new Vector2(-2000,2000), "4,2")); //venus
        cbs.add(new CelestialBody(4, new Vector2(3000,-3000), "4,3")); //earth
        cbs.add(new CelestialBody(5, new Vector2(-4000,-4000), "4,4")); //mars
        cbs.add(new CelestialBody(6, new Vector2(6000,6000), "4,5")); //jupiter
        cbs.add(new CelestialBody(7, new Vector2(-7000,7000), "4,6")); //saturn
        cbs.add(new CelestialBody(8, new Vector2(8000,-8000), "4,7")); //uranus
        cbs.add(new CelestialBody(9, new Vector2(-9000,-9000), "4,8")); //neptune
        cbs.add(new CelestialBody(10, new Vector2(10000,10000), "4,9")); //pluto
        cbs.add(new CelestialBody(11, new Vector2(-10000,-10000), "4,10")); //eros
        cbs.add(new CelestialBody(12, new Vector2(-52000,-5200), "4,11")); //largeAsteroid1



        return new SpaceGameMap(system.getId(), cbs, asteroids, 12000, "3,1;4,1;4,2;4,3;4,4;4,5;4,6;4,7;4,8;4,9;4,10;4,11;5,1", 1);
    }

    protected SpaceGameMap createAsteroids1SystemMap(Room system) {
        List<Asteroid> asteroids = new ArrayList<>();
        asteroids.add(new Asteroid(1, 10, new Vector2(200,0), new Vector2(1,0), 1));
        asteroids.add(new Asteroid(2, 10, new Vector2(200,200), new Vector2(1,0), 1));
//        asteroids.add(new Asteroid(3, 10, new Vector2(0,200), new Vector2(1,0), 1));
//        asteroids.add(new Asteroid(4, 10, new Vector2(-200,0), new Vector2(1,0), 1));
//        asteroids.add(new Asteroid(5, 10, new Vector2(0,-200), new Vector2(1,0), 1));
//        asteroids.add(new Asteroid(6, 10, new Vector2(-200,-200), new Vector2(1,0), 1));
        List<CelestialBody> cbs = new ArrayList<>();
        cbs.add(new CelestialBody(1, new Vector2(0,0), "4,11")); //largeAsteroid1
        return new SpaceGameMap(system.getId(), cbs, asteroids, 600, "4,11;5,1;", 1);
    }

    protected void createTestNpcs(SpaceGame game) {
        NPCInfo p = new NPCInfo(1, "The Killer King", game);
        p.setShip(new NPCShip(p, 1, new Vector2(), 0, 0));
        game.getNpcs().put(1, p);
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

    public void updatePlayersForPlanets(ISFSObject obj, List<User> players)
    {
        send("planet_data", obj, players);
    }

    public void updatePlayerForNPC(ISFSObject obj, User player)
    {
        send("npcd", obj, player);
    }

    public void updatePlayerForAsteroids(ISFSObject obj, User player)
    {
        send("astd", obj, player);
    }
}