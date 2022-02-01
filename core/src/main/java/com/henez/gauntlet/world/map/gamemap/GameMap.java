package com.henez.gauntlet.world.map.gamemap;

import com.badlogic.gdx.utils.TimeUtils;
import com.henez.gauntlet.atlas.img.ImgMapBack;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.world.World;
import com.henez.gauntlet.world.map.MapData;
import com.henez.gauntlet.world.map.MapDataReader;
import com.henez.gauntlet.world.map.tiles.*;
import com.henez.gauntlet.world.teleport.BoundsTeleport;
import com.henez.gauntlet.world.teleport.StepTeleport;
import com.henez.gauntlet.world.teleport.TeleportDestination;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public abstract class GameMap {
    protected MapName mapName;
    protected MapData mapData;
    protected GameList<TileStack> tiles;
    protected TextureRegion mapTex;

    protected ImgMapBack mapBack;
    protected TextureRegion mapBackTex;
    protected GameList<TeleportDestination> destinations;
    protected BoundsTeleport boundsTeleport;

    public GameMap(MapName mapName) {
        this.mapName = mapName;
        this.mapData = new MapDataReader().read(mapName.getPath());
        loadTilesFromMapData();
        //mapTex = new MapTexBuilder().draw(mapData.getWidth(), mapData.getHeight(), tiles);

        mapBack = mapName.getBack();
        mapBackTex = mapName.getBack().asTex();
        destinations = new GameList<>();
        boundsTeleport = new BoundsTeleport();
    }

    private void loadTilesFromMapData() {
        World.MAP_GW = mapData.getWidth();
        World.MAP_GH = mapData.getHeight();
        tiles = new GameList<>();

        for (int j = 0; j < mapData.getHeight(); ++j) {
            for (int i = 0; i < mapData.getWidth(); ++i) {
                tiles.add(new TileStack(i,j,mapData.getTileIndex(i, j, Constants.LAYER_BACK),
                                        mapData.getTileIndex(i, j, Constants.LAYER_FLOOR),
                                        mapData.getTileIndex(i, j, Constants.LAYER_DECO),
                                        mapData.getTileIndex(i, j, Constants.LAYER_OBJ)));
            }
        }

        long totalMillis = TimeUtils.millis();
        long transformMillis = TimeUtils.millis();
        TileTextureService.transformTiles(tiles);
        System.out.println(" - TRANSFORM: "+(TimeUtils.timeSinceMillis(transformMillis))+" ("+(TimeUtils.timeSinceMillis(transformMillis)/1000)+"s)");

        long correctMillis = TimeUtils.millis();
        TileTextureService.correctTextures(tiles);
        System.out.println(" - CORRECT TEXTURES: "+(TimeUtils.timeSinceMillis(correctMillis))+" ("+(TimeUtils.timeSinceMillis(correctMillis)/1000)+"s)");

        long swapMillis = TimeUtils.millis();
        TileThemeSwapService.swapTextures(tiles, mapName.getTileTheme());
        System.out.println(" - THEME SWAP TEXTURES: "+(TimeUtils.timeSinceMillis(swapMillis))+" ("+(TimeUtils.timeSinceMillis(swapMillis)/1000)+"s)");

        System.out.println(" - TOTAL: "+(TimeUtils.timeSinceMillis(totalMillis))+" ("+(TimeUtils.timeSinceMillis(totalMillis)/1000)+"s)");
    }

    public GameList<StepTeleport> getStepTiles() {
        GameList<StepTeleport> steps = new GameList<>();
        tiles.forEach(tileStack -> {
            if(tileStack.contains(TileName.world_cave_door) || tileStack.contains(TileName.door) || tileStack.contains(TileName.world_town)) {
                steps.add(new StepTeleport(tileStack.getGx(), tileStack.getGy()).withDestination(destinations.first()));

                if(destinations.size()>1) {
                    destinations.remove(0);
                }
            }
        });
        return steps;
    }

    public int getGw() {
        return mapData.getWidth();
    }

    public int getGh() {
        return mapData.getHeight();
    }

    public int getPixelW() {
        return mapData.getWidth()*Constants.tilePixelSize;
    }

    public int getPixelH() {
        return mapData.getHeight()*Constants.tilePixelSize;
    }

    public TileStack getTileStackAt(int gx, int gy) {
        return TileUtils.getTileStackAt(tiles, gx, gy);
    }
}
