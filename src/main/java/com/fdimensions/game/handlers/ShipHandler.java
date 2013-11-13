package com.fdimensions.game.handlers;

import com.fdimensions.DimensionServerExtension;
import com.fdimensions.math.Vector2;
import com.fdimensions.model.PlayerInfo;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 * Created with IntelliJ IDEA.
 * User: rkevan
 * Date: 10/30/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShipHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject positionParams) {
        DimensionServerExtension sphe =(DimensionServerExtension)getParentExtension();
        int actionId = positionParams.getInt("actionId");
        if (actionId == 1)  { //update position

        }
        else if (actionId == 2) { //get server position

        }
        else if (actionId == 3) { //get asteroids number

        }
    }
}
