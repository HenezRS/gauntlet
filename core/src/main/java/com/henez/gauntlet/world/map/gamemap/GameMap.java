package com.henez.gauntlet.world.map.gamemap;

import com.badlogic.gdx.utils.TimeUtils;
import com.henez.gauntlet.atlas.img.ImgMapBack;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.world.map.MapController;
import com.henez.gauntlet.world.map.MapData;
import com.henez.gauntlet.world.map.MapDataReader;
import com.henez.gauntlet.world.map.gamemap.instances.GameInstance;
import com.henez.gauntlet.world.map.gamemap.instances.InstanceGenerator;
import com.henez.gauntlet.world.map.tiles.*;
import com.henez.gauntlet.world.teleport.BoundsTeleport;
import com.henez.gauntlet.world.teleport.StepTeleport;
import com.henez.gauntlet.world.teleport.TeleportDestination;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public abstract class GameMap {
    protected boolean isInstance = false;

    protected GameInstance gameInstance;
    protected MapName mapName;
    protected MapData mapData;
    protected GameList<TileStack> tiles;
    protected TextureRegion mapTex;

    protected ImgMapBack mapBack;
    protected TextureRegion mapBackTex;
    protected GameList<TeleportDestination> destinations;
    protected BoundsTeleport boundsTeleport;

    public GameMap(MapName mapName) {
        init(mapName, false);
    }

    public GameMap(MapName mapName, boolean isInstance) {
        init(mapName, isInstance);
    }

    private void init(MapName mapName, boolean isInstance) {
        this.isInstance = isInstance;
        this.mapName = mapName;
        tiles = new GameList<>();
        mapBack = mapName.getBack();
        mapBackTex = mapName.getBack().asTex();
        destinations = new GameList<>();
        boundsTeleport = new BoundsTeleport();

        loadMapData();
        //mapTex = new MapTexBuilder().draw(mapData.getWidth(), mapData.getHeight(), tiles);
    }

    private void loadMapData() {
        if (isInstance) {
            gameInstance = new InstanceGenerator().generate();
            loadTilesFromGameInstance(gameInstance);
        } else {
            mapData = new MapDataReader().read(mapName.getPath());
            loadTilesFromMapData(mapData);
        }
        applyTileTransformations();
    }

    private void loadTilesFromMapData(MapData mapData) {
        MapController.MAP_GW = mapData.getWidth();
        MapController.MAP_GH = mapData.getHeight();

        for (int j = 0; j < mapData.getHeight(); ++j) {
            for (int i = 0; i < mapData.getWidth(); ++i) {
                tiles.add(new TileStack(i, j, mapData.getTileIndex(i, j, Constants.LAYER_BACK),
                        mapData.getTileIndex(i, j, Constants.LAYER_FLOOR),
                        mapData.getTileIndex(i, j, Constants.LAYER_DECO),
                        mapData.getTileIndex(i, j, Constants.LAYER_OBJ)));
            }
        }
    }

    private void loadTilesFromGameInstance(GameInstance gameInstance) {
        MapController.MAP_GW = (gameInstance.getRoomW() + 1) * Constants.instanceRoomTileW;
        MapController.MAP_GH = (gameInstance.getRoomH() + 1) * Constants.instanceRoomTileH;
        gameInstance.getInstanceRoomsMap().forEach((xy, room) -> {
            MapData md = room.getMapData();
            int x = xy.getX() * Constants.instanceRoomTileW;
            int y = xy.getY() * Constants.instanceRoomTileH;

            for (int j = 0; j < md.getHeight(); ++j) {
                for (int i = 0; i < md.getWidth(); ++i) {
                    tiles.add(new TileStack(i + x, j + y, md.getTileIndex(i, j, Constants.LAYER_BACK),
                            md.getTileIndex(i, j, Constants.LAYER_FLOOR),
                            md.getTileIndex(i, j, Constants.LAYER_DECO),
                            md.getTileIndex(i, j, Constants.LAYER_OBJ)));
                }
            }
        });
    }

    private void applyTileTransformations() {
        long totalMillis = TimeUtils.millis();
        long transformMillis = TimeUtils.millis();
        TileTextureService.transformTiles(tiles);
        System.out.println(" - TRANSFORM: " + (TimeUtils.timeSinceMillis(transformMillis)) + " (" + (TimeUtils.timeSinceMillis(transformMillis) / 1000) + "s)");

        long correctMillis = TimeUtils.millis();
        TileTextureService.correctTextures(tiles);
        System.out.println(" - CORRECT TEXTURES: " + (TimeUtils.timeSinceMillis(correctMillis)) + " (" + (TimeUtils.timeSinceMillis(correctMillis) / 1000) + "s)");

        long swapMillis = TimeUtils.millis();
        TileThemeSwapService.swapTextures(tiles, mapName.getTileTheme());
        System.out.println(" - THEME SWAP TEXTURES: " + (TimeUtils.timeSinceMillis(swapMillis)) + " (" + (TimeUtils.timeSinceMillis(swapMillis) / 1000) + "s)");

        System.out.println(" - TOTAL: " + (TimeUtils.timeSinceMillis(totalMillis)) + " (" + (TimeUtils.timeSinceMillis(totalMillis) / 1000) + "s)");
    }

    public GameList<StepTeleport> getStepTiles() {
        GameList<StepTeleport> steps = new GameList<>();
        tiles.forEach(tileStack -> {
            if (tileStack.contains(TileName.world_cave_door) || tileStack.contains(TileName.door) || tileStack.contains(TileName.world_town)) {
                steps.add(new StepTeleport(tileStack.getGx(), tileStack.getGy()).withDestination(destinations.first()));

                if (destinations.size() > 1) {
                    destinations.remove(0);
                }
            }
        });
        return steps;
    }

    public int getGw() {
        return MapController.MAP_GW;
    }

    public int getGh() {
        return MapController.MAP_GH;
    }

    public int getPixelW() {
        return MapController.MAP_GW * Constants.tilePixelSize;
    }

    public int getPixelH() {
        return MapController.MAP_GH * Constants.tilePixelSize;
    }

    public TileStack getTileStackAt(int gx, int gy) {
        return TileUtils.getTileStackAt(tiles, gx, gy);
    }
}
