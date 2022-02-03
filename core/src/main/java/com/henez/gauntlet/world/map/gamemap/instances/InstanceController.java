package com.henez.gauntlet.world.map.gamemap.instances;

import com.henez.gauntlet.world.map.MapData;
import com.henez.gauntlet.world.map.MapDataReader;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class InstanceController {
    private static InstanceController instance = null;
    private Map<InstanceRoomName, MapData> roomDataMap;
    private GameInstance currentInstance;

    private InstanceController() {
        loadRooms();
    }

    private void loadRooms() {
        MapDataReader mapDataReader = new MapDataReader();
        roomDataMap = new HashMap<>();
        Arrays.stream(InstanceRoomName.values()).forEach(room -> {
            roomDataMap.put(room, mapDataReader.read("instance/"+room.getPath()));
        });
    }

    public static InstanceController getInstance() {
        if(instance==null) {
            instance = new InstanceController();
        }
        return instance;
    }

    private MapData getRoomData(InstanceRoomName instanceRoomName) {
        return roomDataMap.get(instanceRoomName);
    }

    public InstanceRoom createRoom(InstanceRoomName instanceRoomName) {
        return new InstanceRoom(getRoomData(instanceRoomName));
    }
}
