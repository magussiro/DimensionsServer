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

    private int currentSystemId;
    private Vector2 pos;
    private Vector2 quadrant;
    private User user;

    public PlayerInfo(int currentSystemId, Vector2 pos, User user) {
        this.currentSystemId = currentSystemId;
        this.pos = pos;
        this.user = user;
    }

    public int getCurrentSystemId(int currentSystemId) {
        return currentSystemId;
    }

    public void setCurrentSystemId(int currentSystemId) {
        this.currentSystemId = currentSystemId;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(Vector2 quadrant) {
        this.quadrant = quadrant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
