package com.henez.gauntlet.world.teleport;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.world.map.gamemap.MapName;
import lombok.Getter;

@Getter
public class TeleportDestination {
    private int gx;
    private int gy;
    private MapName mapName;
    private Facing facing;

    public TeleportDestination(int gx, int gy, MapName mapName, Facing facing) {
        this.gx = gx;
        this.gy = gy;
        this.mapName = mapName;
        this.facing = facing;
    }
}
