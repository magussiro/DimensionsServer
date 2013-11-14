package com.fdimensions.model;

import com.fdimensions.math.Vector2;
import com.smartfoxserver.v2.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerInfo {

    private SpaceGame spaceGame;
    private Ship ship;
    private User user;

    public PlayerInfo(SpaceGame spaceGame, User user, Ship ship) {
        this.spaceGame = spaceGame;
        this.user = user;
        this.ship = ship;
    }

    public SpaceGame getSpaceGame() {
        return spaceGame;
    }

    public void setSpaceGame(SpaceGame spaceGame) {
        this.spaceGame = spaceGame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
