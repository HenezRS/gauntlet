package com.henez.gauntlet.world.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.henez.gauntlet.constants.Constants;

public class MapDataReader {
    public MapDataReader() {
    }

    public MapData read(String mapFileName) {
        Gson gson = new Gson();
        FileHandle fileHandle = Gdx.files.local(Constants.PATH_MAPS + String.format("%s.json", mapFileName));
        MapData mapData = gson.fromJson(fileHandle.readString(), MapData.class);
        mapData.loadTileLayers();
        return mapData;
    }
}
