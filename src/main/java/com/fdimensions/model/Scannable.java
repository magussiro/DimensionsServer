package com.fdimensions.model;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 11/14/13
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Scannable {

    public void checkProximityToPlayer(PlayerInfo playerInfo);
    public void checkProximityToNPC(NPCInfo otherPlayerInfo);

}
