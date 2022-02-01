package com.henez.gauntlet.world.map;

import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.world.map.tiles.TileName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MapData {
    private int width;
    private int height;
    private GameList<MapLayer> layers;
    private Map<String, int[]> namedLayer;

    public void loadTileLayers() {
        namedLayer = new HashMap<>();
        namedLayer.put(Constants.LAYER_OBJ, layers.stream().filter(layer -> layer.getName().equals(Constants.LAYER_OBJ)).findFirst().orElse(new MapLayer()).getData());
        namedLayer.put(Constants.LAYER_DECO, layers.stream().filter(layer -> layer.getName().equals(Constants.LAYER_DECO)).findFirst().orElse(new MapLayer()).getData());
        namedLayer.put(Constants.LAYER_FLOOR, layers.stream().filter(layer -> layer.getName().equals(Constants.LAYER_FLOOR)).findFirst().orElse(new MapLayer()).getData());
        namedLayer.put(Constants.LAYER_BACK, layers.stream().filter(layer -> layer.getName().equals(Constants.LAYER_BACK)).findFirst().orElse(new MapLayer()).getData());
    }

    public int getTileIndex(int gx, int gy, String layerName) {
        return namedLayer.get(layerName) != null ? namedLayer.get(layerName)[(gx) + (gy * width)] : TileName.none.getIndex();
    }
}
