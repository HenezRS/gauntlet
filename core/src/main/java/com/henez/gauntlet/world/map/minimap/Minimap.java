package com.henez.gauntlet.world.map.minimap;

import com.badlogic.gdx.graphics.Color;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.datastructures.XY;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.input.In;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.map.tiles.TileStack;
import com.henez.gauntlet.world.mapobjects.actions.ControlledPlayer;
import com.henez.gauntlet.world.teleport.BoundsTeleport;
import lombok.Getter;

@Getter
public class Minimap {
    private static Minimap minimap = null;
    private final static Color COLOR_FLOOR = Colors.text_default.color;
    private final static Color COLOR_WALL = Colors.text_system.color;
    private final static Color COLOR_NULL = Colors.black.color;
    private final static Color COLOR_SPAWN = Colors.hp.color;

    private MinimapCamera minimapCamera;
    private Color[][] currentRaw;
    private T[][] toDraw;
    private boolean[][] walkable;
    private int w;
    private int h;
    private GameList<XY> startXy;
    private BoundsTeleport bounds;
    private int bX;
    private int bY;
    private int bXX;
    private int bYY;
    private int scale = 1;

    private Minimap() {
        minimapCamera = new MinimapCamera();
    }

    public void update(ControlledPlayer player) {
        minimapCamera.update(this, player);

        if(In.map.isPressed()) {
            scale++;
            if(scale > 3) {
                scale = 0;
            }
        }
    }

    public void createMinimap(GameMap gameMap, ControlledPlayer player) {
        w = gameMap.getGw();
        h = gameMap.getGh();
        currentRaw = new Color[w][h];
        bounds = gameMap.getBoundsTeleport();
        startXy = new GameList<>();
        gameMap.getStepTiles().forEach(tp -> {
            startXy.add(new XY(tp.getGx(),tp.getGy()));
        });
        startXy.add(new XY(player.getGx(), player.getGy()));

        bX = bounds.getMinGx()==-1 ? 0 : bounds.getMinGx();
        bXX = bounds.getMaxGx()==-1 ? w : bounds.getMaxGx()+1;
        bY = bounds.getMinGy()==-1 ? 0 : bounds.getMinGy();
        bYY = bounds.getMaxGy()==-1 ? h : bounds.getMaxGy()+1;

        buildCoords(gameMap);

        currentRaw[player.getGx()][player.getGy()] = COLOR_SPAWN;

        for(int i=0; i<w; ++i) {
            for (int j = 0; j < h; ++j) {
                currentRaw[i][j] = COLOR_NULL;
            }
        }
        for(int i=bX; i<bXX; ++i) {
            for(int j=bY; j<bYY; ++j) {
                if(toDraw[i][j]==T.yes) {
                    currentRaw[i][j] = walkable[i][j] ? COLOR_FLOOR : COLOR_WALL;
                }
            }
        }

        currentRaw[player.getGx()][player.getGy()] = COLOR_SPAWN;
    }

    private void buildCoords(GameMap gameMap) {
        toDraw = new T[w][h];
        walkable = new boolean[w][h];
        for(int i=0; i<w; ++i) {
            for(int j=0; j<h; ++j) {
                toDraw[i][j] = T.no;
                walkable[i][j] = false;
            }
        }

        startXy.forEach(xy -> {
            toDraw[xy.getX()][xy.getY()] = T.check;
        });

        boolean done = false;
        while(!done) {
            boolean checked = false;
            for(int i=bX; i<bXX; ++i) {
                for(int j=bY; j<bYY; ++j) {
                    if(toDraw[i][j] == T.check) {
                        checked = true;
                        toDraw[i][j] = T.yes;

                        TileStack ts = gameMap.getTileStackAt(i,j);
                        if(ts != null && ts.isWalkableWithoutRules()) {
                            check(i, j, gameMap);
                        }
                    }
                }
            }

            done = !checked;
        }
    }

    private boolean inBounds(int i, int j) {
        return i>=bX && j>=bY && i<bXX && j<bYY;
    }

    private void check(int x, int y, GameMap gameMap) {
        boolean diag = true;
        for(int i=x-1; i<x+2; ++i) {
            for(int j=y-1; j<y+2; ++j) {
                if(!diag && !(i==x && j==y) && inBounds(i, j) && toDraw[i][j]==T.no) {
                    toDraw[i][j] = T.check;
                    walkable[i][j] = gameMap.getTileStackAt(i, j) != null && gameMap.getTileStackAt(i, j).isWalkableWithoutRules();
                }
                diag = !diag;
            }
        }
    }

    public static Minimap getInstance() {
        if(minimap==null) {
            minimap = new Minimap();
        }
        return minimap;
    }

    private enum T {
        no, yes, check, spawn
    }
}
