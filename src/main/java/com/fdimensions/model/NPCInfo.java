package com.fdimensions.model;

import com.smartfoxserver.v2.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NPCInfo {

    private SpaceGame spaceGame;
    private Ship ship;
    private Integer id;
    private String name;

    public NPCInfo(Integer id, String name, SpaceGame spaceGame) {
        this.spaceGame = spaceGame;
        this.id = id;
        this.name = name;
    }

    public SpaceGame getSpaceGame() {
        return spaceGame;
    }

    public void setSpaceGame(SpaceGame spaceGame) {
        this.spaceGame = spaceGame;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
