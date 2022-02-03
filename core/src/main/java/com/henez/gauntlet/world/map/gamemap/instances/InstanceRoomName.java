package com.henez.gauntlet.world.map.gamemap.instances;

import lombok.Getter;

@Getter
public enum InstanceRoomName {
    start("start"),
    empty("empty"),
    ;

    private final String path;

    InstanceRoomName(String path) {
        this.path = path;
    }
}
