package com.henez.gauntlet.world.map.tiles;

import com.henez.gauntlet.constants.Constants;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TileStack {
    private Map<TileKey, Tile> tilesMap;

    private int x;
    private int y;
    private int gx;
    private int gy;
    private boolean walkable;
    private boolean walkableWithRules;

    public TileStack(int gx, int gy, int tileBack, int tileFloor, int tileDeco, int tileObj) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Constants.tilePixelSize;
        this.y = gy * Constants.tilePixelSize;

        tilesMap = new HashMap<>();
        tilesMap.put(TileKey.back, new Tile(TileName.fromIndex(tileBack - 1)));
        tilesMap.put(TileKey.floor, new Tile(TileName.fromIndex(tileFloor - 1)));
        tilesMap.put(TileKey.deco, new Tile(TileName.fromIndex(tileDeco - 1)));
        tilesMap.put(TileKey.obj, new Tile(TileName.fromIndex(tileObj - 1)));
        //todo: obj layer should not be checked. obj layer should spawn an object then become null
        walkable = tilesMap.get(TileKey.floor).isWalkable()
                && tilesMap.get(TileKey.deco).isWalkable()
                && tilesMap.get(TileKey.obj).isWalkable()
                && !tileIsNull();

        if (walkable) {
            walkableWithRules = !tilesMap.get(TileKey.floor).getTileName().getForbiddenMovement().isEmpty()
                    || !tilesMap.get(TileKey.deco).getTileName().getForbiddenMovement().isEmpty()
                    || !tilesMap.get(TileKey.obj).getTileName().getForbiddenMovement().isEmpty();
        }
    }

    public Tile getTile(TileKey key) {
        return tilesMap.get(key);
    }

    private boolean tileIsNull() {
        return tilesMap.get(TileKey.back).getTileName() == TileName.none
                && tilesMap.get(TileKey.floor).getTileName() == TileName.none
                && tilesMap.get(TileKey.deco).getTileName() == TileName.none
                && tilesMap.get(TileKey.obj).getTileName() == TileName.none;
    }

    public boolean contains(TileName tileName) {
        return tilesMap.entrySet().stream().anyMatch(e -> e.getValue().getTileName() == tileName);
    }

    public boolean isWalkableWithoutRules() {
        return walkable && !walkableWithRules;
    }
}
