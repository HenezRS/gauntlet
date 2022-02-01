package com.henez.gauntlet.world.map.tiles;

import com.henez.gauntlet.atlas.img.ImgTiles;
import com.henez.gauntlet.datastructures.GameList;
import org.mini2Dx.core.graphics.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public final class TileThemeSwapService {
    /*public static int count = 0;
    public static int shoreCount = 0;*/

    private TileThemeSwapService(){}

    public static void swapTextures(GameList<TileStack> tileStacks, TileTheme tileTheme) {
        if(tileTheme == TileTheme.none) {
            return;
        }

        Map<TextureRegion, TextureRegion> swapMap = new HashMap<>();

        if(tileTheme == TileTheme.dark) {
            swapMap.put(ImgTiles.tree.asTex(), ImgTiles.purple_tree.asTex());
            swapMap.put(ImgTiles.tree_base.asTex(), ImgTiles.purple_tree_base.asTex());
            swapMap.put(ImgTiles.tree_top.asTex(), ImgTiles.purple_tree_top.asTex());
            swapMap.put(ImgTiles.flowers.asTex(), ImgTiles.purple_flowers.asTex());
            swapMap.put(ImgTiles.grass.asTex(), ImgTiles.purple_grass.asTex());
            swapMap.put(ImgTiles.grass_tall.asTex(), ImgTiles.purple_grass_tall.asTex());
        }

        tileStacks.forEach(tileStack -> swapTexture(tileStack, swapMap));
    }

    private static void swapTexture(TileStack tileStack, Map<TextureRegion, TextureRegion> swapMap) {
        swap(tileStack.getTile(TileKey.floor), swapMap);
        swap(tileStack.getTile(TileKey.deco), swapMap);
    }

    private static void swap(Tile tile, Map<TextureRegion, TextureRegion> swapMap) {
        if(swapMap.containsKey(tile.getTex())) {
            tile.setTex(swapMap.get(tile.getTex()));
        }
    }
}
