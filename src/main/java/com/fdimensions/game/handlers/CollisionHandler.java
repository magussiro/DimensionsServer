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
public class CollisionHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User player, ISFSObject positionParams) {
        DimensionServerExtension sphe =(DimensionServerExtension)getParentExtension();
        int actionId = positionParams.getInt("actionId");
        if (actionId == 1)  { //update position
            PlayerInfo pi = sphe.getPlayerInfo(player.getId());
            float posX = positionParams.getFloat("posX");
            float posY = positionParams.getFloat("posY");
            long quadrantX = positionParams.getInt("quadrantX");
            long quadrantY = positionParams.getInt("quadrantY");
            pi.setPos(new Vector2(posX, posY));
            pi.setQuadrant(new Vector2(quadrantX, quadrantY));
        }
        else if (actionId == 2) { //get server position
            PlayerInfo pi = sphe.getPlayerInfo(player.getId());
            ISFSObject rtn = new SFSObject();
            rtn.putUtfString("pos", "X:"+pi.getPos().X+" Y:"+pi.getPos().X);
            rtn.putUtfString("quad", "X:"+pi.getQuadrant().X+" Y:"+pi.getPos().Y);
            sphe.send("position", rtn, player);
        }
        else if (actionId == 3) { //get asteroids number
            ISFSObject rtn = new SFSObject();
            rtn.putInt("asteroids", 3);
            sphe.send("asteroids", rtn, player);

        }
    }
}
