package com.henez.gauntlet.world.map.gamemap.instances;

import com.henez.gauntlet.datastructures.XY;
import lombok.Getter;

import java.util.Map;

@Getter
public class GameInstance {
    private Map<XY, InstanceRoom> instanceRoomsMap;
    private XY startRoomXY;

    public GameInstance() {

    }

    public void setInstanceRoomsMap(Map<XY, InstanceRoom> instanceRoomsMap) {
        this.instanceRoomsMap = instanceRoomsMap;
    }

    public void setStartRoomXY(XY xy) {
        startRoomXY = xy;
    }
}
