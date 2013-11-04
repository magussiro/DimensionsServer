package com.fdimensions.game;

import com.fdimensions.model.PlayerInfo;
import com.smartfoxserver.v2.extensions.SFSExtension;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpacePositionHandlerExtension extends SFSExtension {
    private Map<Integer, PlayerInfo> playerInfos;
    private PlayerInfo pi;


    @Override
    public void init() {
        this.addRequestHandler("position", SpacePositionHandler.class);
    }

    public PlayerInfo getPlayerInfo(int playerId) {
        if (null == pi) {
            pi = new PlayerInfo();
        }
        return pi;
    }
}
