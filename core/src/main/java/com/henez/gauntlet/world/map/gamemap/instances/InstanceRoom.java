package com.henez.gauntlet.world.map.gamemap.instances;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.world.map.MapData;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class InstanceRoom {

    private MapData mapData;
    private Map<Facing, InstanceDoor> doors;

    public InstanceRoom(MapData mapData) {
        this.mapData = mapData;
        initDoors();
    }

    private void initDoors() {
        doors = new HashMap<>();
        Arrays.stream(Facing.get4Ways()).forEach(f -> doors.put(f, new InstanceDoor()));
    }

    public void openDoor(Facing dir) {
        doors.get(dir).openDoor();
    }
}
