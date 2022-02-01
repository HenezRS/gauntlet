package com.henez.gauntlet.world.map.gamemap.impl;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.map.gamemap.MapName;
import com.henez.gauntlet.world.teleport.TeleportDestination;

public class CaveMap extends GameMap {

    public CaveMap() {
        super(MapName.cave);
        boundsTeleport.setBoundsY(3,35);
        boundsTeleport.addDestFacingAll(new TeleportDestination(53, 21, MapName.world, Facing.down));
    }
}
