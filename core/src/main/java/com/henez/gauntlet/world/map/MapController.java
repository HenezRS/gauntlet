package com.henez.gauntlet.world.map;

import com.henez.gauntlet.atlas.img.ImgMapBack;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import lombok.Getter;

@Getter
public class MapController {
    private static MapController map = null;

    private GameMap currentMap;
    private ImgMapBack mapBack;

    private MapController() {
    }

    public void beginNew(GameMap gameMap) {
        currentMap = gameMap;
        mapBack = gameMap.getMapBack();
    }

    public static MapController getInstance() {
        if(map==null) {
            map = new MapController();
        }
        return map;
    }
}
