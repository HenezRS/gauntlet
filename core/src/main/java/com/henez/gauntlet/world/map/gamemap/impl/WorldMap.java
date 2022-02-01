package com.henez.gauntlet.world.map.gamemap.impl;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.map.gamemap.MapName;
import com.henez.gauntlet.world.teleport.TeleportDestination;

public class WorldMap extends GameMap {

    public WorldMap() {
        super(MapName.world);
        destinations.add(new TeleportDestination(18, 34, MapName.cave, Facing.up));
        destinations.add(new TeleportDestination(44, 23, MapName.test_grass, Facing.down));
    }
}
