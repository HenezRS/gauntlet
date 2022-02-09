package com.henez.gauntlet.world.map.gamemap.instances;

import com.henez.gauntlet.datastructures.XY;
import com.henez.gauntlet.world.map.MapData;
import lombok.Getter;

import java.util.Map;

@Getter
public class GameInstance {
    private Map<XY, InstanceRoom> instanceRoomsMap;
    private XY startRoomXY;
    private int roomW = 0;
    private int roomH = 0;

    public GameInstance() {

    }

    public void setInstanceRoomsMap(Map<XY, InstanceRoom> instanceRoomsMap) {
        this.instanceRoomsMap = instanceRoomsMap;
        determineRoomSizes();
    }

    private void determineRoomSizes() {
        roomW = 0;
        roomH = 0;
        instanceRoomsMap.forEach((xy, room) -> {
            if(xy.getX()>roomW) {
                roomW = xy.getX();
            }

            if(xy.getY()>roomH) {
                roomH = xy.getY();
            }
        });
    }

    public void setStartRoomXY(XY xy) {
        startRoomXY = xy;
    }
}
