package com.henez.gauntlet.world.map.gamemap;

import com.henez.gauntlet.atlas.img.ImgBb;
import com.henez.gauntlet.atlas.img.ImgMapBack;
import com.henez.gauntlet.world.map.gamemap.impl.CaveMap;
import com.henez.gauntlet.world.map.gamemap.impl.GrassDarkMap;
import com.henez.gauntlet.world.map.gamemap.impl.TestMap;
import com.henez.gauntlet.world.map.gamemap.impl.WorldMap;
import com.henez.gauntlet.world.map.tiles.TileTheme;
import lombok.Getter;

@Getter
public enum MapName {
    //misc
    test_grass("test grass","test", ImgMapBack.grass, ImgBb.forest),
    grass_dark("grass dark","test", ImgMapBack.grass_dark, ImgBb.forest, TileTheme.dark),
    world("world map","world", ImgMapBack.water, ImgBb.forest),
    cave("cave cave","cave", ImgMapBack.cave, ImgBb.forest),
    ;

    private final String name;
    private final String path;
    private final ImgMapBack back;
    private final ImgBb bbDefault;
    private TileTheme tileTheme = TileTheme.none;

    MapName(String name, String path, ImgMapBack back, ImgBb bbDefault) {
        this.name = name;
        this.path = path;
        this.back = back;
        this.bbDefault = bbDefault;
    }

    MapName(String name, String path, ImgMapBack back, ImgBb bbDefault, TileTheme tileTheme) {
        this.name = name;
        this.path = path;
        this.back = back;
        this.bbDefault = bbDefault;
        this.tileTheme = tileTheme;
    }

    public GameMap create() {
        switch(this) {
            //misc
        case test_grass: return new TestMap();
        case world: return new WorldMap();
        case cave: return new CaveMap();
        case grass_dark: return new GrassDarkMap();
        }
        return null;
    }
}
