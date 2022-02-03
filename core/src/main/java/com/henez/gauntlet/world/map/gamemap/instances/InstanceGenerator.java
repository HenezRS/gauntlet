package com.henez.gauntlet.world.map.gamemap.instances;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.datastructures.XY;

import java.util.HashMap;
import java.util.Map;

public class InstanceGenerator {
    private InstanceController instanceController = InstanceController.getInstance();

    private Map<XY,InstanceRoom> map;
    private Facing lastDir;

    private int bX = 0;
    private int bY = 0;
    private int bXX = 20;
    private int bYY = 20;

    public InstanceGenerator() {

    }

    public GameInstance generate() {
        GameInstance gameInstance = new GameInstance();
        int startX = 10;
        int startY = 10;
        int stepsLeft = 15;

        map = new HashMap<>();
        XY xy = new XY(startX,startY);
        XY xyLast;
        xy.setEnableWrap(false);

        gameInstance.setStartRoomXY(new XY(startX,startY));
        map.put(new XY(xy), instanceController.createRoom(InstanceRoomName.start));
        while(stepsLeft>0) {
            xyLast = new XY(xy);
            takeStep(xy);
            if(roomSpaceFree(xy)) {
                map.put(new XY(xy), instanceController.createRoom(InstanceRoomName.empty));
            }

            map.get(xyLast).openDoor(lastDir);

            stepsLeft--;
        }

        gameInstance.setInstanceRoomsMap(map);
        return gameInstance;
    }

    private void takeStep(XY xy) {
        boolean valid = false;

        while(!valid) {
            lastDir = Facing.getRandom4();
            xy.transform(lastDir.tx, lastDir.ty);

            if(withinBounds(xy)) {
                valid = true;
            } else {
                xy.transformRevert();
            }
        }
    }

    private boolean withinBounds(XY xy) {
        return xy.getX()>=bX && xy.getX()<bXX && xy.getY()>=bY && xy.getY()<bYY;
    }

    private boolean roomSpaceFree(XY xy) {
        return map.containsKey(xy);
    }
}
