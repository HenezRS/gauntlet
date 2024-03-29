package com.henez.gauntlet.world.map.gamemap.impl;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.map.gamemap.MapName;
import com.henez.gauntlet.world.teleport.TeleportDestination;

public class TestMap extends GameMap {

    public TestMap() {
        super(MapName.test_grass);
        destinations.add(new TeleportDestination(40, 34, MapName.world, Facing.down));
        boundsTeleport.setBoundsY(0, 45);
        boundsTeleport.addDestFacingAll(new TeleportDestination(40, 34, MapName.world, Facing.down));
    }
}
