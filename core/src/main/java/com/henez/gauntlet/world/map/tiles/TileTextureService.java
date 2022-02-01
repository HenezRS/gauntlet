package com.henez.gauntlet.world.map.tiles;

import com.henez.gauntlet.atlas.img.ImgTiles;
import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.datastructures.GameList;

import java.util.Map;

public final class TileTextureService {
    /*public static int count = 0;
    public static int shoreCount = 0;*/

    private TileTextureService(){}

    public static void correctTextures(GameList<TileStack> tileStacks) {
        tileStacks.forEach(tileStack -> correctTexture(tileStack,tileStacks));
    }

    public static void transformTiles(GameList<TileStack> tileStacks) {
        tileStacks.forEach(tileStack -> transform(tileStack, tileStacks, TileKey.floor));
    }

    private static void correctTexture(TileStack tileStack, GameList<TileStack> tileStacks) {
        correct(tileStack, tileStacks, TileKey.floor);
        correct(tileStack, tileStacks, TileKey.deco);
    }

    private static void correct(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        switch(tileStack.getTile(key).getTileName()) {
        case tree: correctTree(tileStack, tileStacks, key); break;
        case roof_detail: correctRoofDetail(tileStack, tileStacks, key); break;
        case roof_grey: correctRoofGrey(tileStack, tileStacks, key); break;
        case roof_brown: correctRoofBrown(tileStack, tileStacks, key); break;
        case world_mountain: correctWorldMountain(tileStack, tileStacks, key); break;
        case world_forest: correctWorldForest(tileStack, tileStacks, key); break;
        case world_shore: correctWorldShore(tileStack, tileStacks, key); break;
        case cave_wall: correctCaveWall(tileStack, tileStacks, key); break;
        default: break;
        }
    }

    private static void transform(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        if (tileStack.getTile(key).getTileName() == TileName.world_grass) {
            transformWorldGrassIntoShore(tileStack, tileStacks, key);
        }
    }

    private static void correctTree(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        TileStack above = TileUtils.getTileStackTowards(tileStacks, tileStack, Facing.up);
        TileStack below = TileUtils.getTileStackTowards(tileStacks, tileStack, Facing.down);

        if(above != null && above.getTile(key).getTileName() != tileStack.getTile(key).getTileName()) {
            tileStack.getTile(key).setTex(ImgTiles.tree_top.asTex());
        }

        if(below != null && below.getTile(key).getTileName() != tileStack.getTile(key).getTileName()) {
            tileStack.getTile(key).setTex(ImgTiles.tree_base.asTex());
        }
    }

    private static void correctRoofDetail(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        if(TileUtils.countSurroundingTileNames(tileStack, tileStacks,TileName.roof_detail, key, true)==3) {
            tileStack.getTile(key).setTex(ImgTiles.roof_detail_middle.asTex());
        } else {
            Facing f = TileUtils.findFirstFacingToTileName(tileStack, tileStacks,TileName.roof_detail, key, true);
            if(f != null) {
                switch (f) {
                case right:
                    tileStack.getTile(key).setTex(ImgTiles.roof_detail_left.asTex());
                    break;
                case down:
                    tileStack.getTile(key).setTex(ImgTiles.roof_detail_top.asTex());
                    break;
                case left:
                    tileStack.getTile(key).setTex(ImgTiles.roof_detail_right.asTex());
                    break;
                }
            }
        }
    }

    private static void correctRoofGrey(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        TileStack above = TileUtils.getTileStackTowards(tileStacks, tileStack, Facing.up);

        if(above != null && above.getTile(key).getTileName() != tileStack.getTile(key).getTileName()) {
            tileStack.getTile(key).setTex(ImgTiles.roof_grey_top.asTex());
        }
    }

    private static void correctRoofBrown(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        TileStack above = TileUtils.getTileStackTowards(tileStacks, tileStack, Facing.up);

        if(above != null && above.getTile(key).getTileName() != tileStack.getTile(key).getTileName()) {
            if(TileName.chimney != above.getTile(key).getTileName()) {
                tileStack.getTile(key).setTex(ImgTiles.roof_brown_top.asTex());
            }
        }
    }

    private static void correctWorldMountain(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        Map<Facing, TileStack> tileStackMap = TileUtils.getTileStacksTowards(tileStacks,tileStack,Facing.up,Facing.left,Facing.down,Facing.right);
        TileName[] tilesToMatch = new TileName[]{TileName.world_mountain, TileName.world_cave_door};

        if(TileUtils.noSurroundingTilesMatch(tileStack, tileStacks,tilesToMatch, key, true)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_single.asTex());
            return;
        }

        if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_tl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_tr.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_br.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_bl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_b.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_t.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_l.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.world_mountain_r.asTex());
        }
    }

    private static void correctWorldForest(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        Map<Facing, TileStack> tileStackMap = TileUtils.getTileStacksTowards(tileStacks,tileStack,Facing.up,Facing.left,Facing.down,Facing.right);
        TileName[] tilesToMatch = new TileName[]{TileName.world_forest};

        if(TileUtils.noSurroundingTilesMatch(tileStack, tileStacks,tilesToMatch, key, true)) {
            return;
        }

        if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_tl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_tr.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_br.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_bl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_b.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_t.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_l.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.world_forest_r.asTex());
        }
    }

    private static void correctWorldShore(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        Map<Facing, TileStack> tileStackMap = TileUtils.getTileStacksTowards(tileStacks,tileStack,Facing.up,Facing.left,Facing.down,Facing.right);
        TileName[] tilesToMatch = new TileName[]{TileName.world_water, TileName.world_shore};

        if(TileUtils.noSurroundingTilesMatch(tileStack, tileStacks,tilesToMatch, key, true)) {
            return;
        }

        if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_tl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_tr.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_br.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_bl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_b.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_t.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_l.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.world_water_edge_r.asTex());
        }
    }

    private static void correctCaveWall(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        Map<Facing, TileStack> tileStackMap = TileUtils.getTileStacksTowards(tileStacks,tileStack,Facing.up,Facing.left,Facing.down,Facing.right);
        TileName[] tilesToMatch = new TileName[]{TileName.cave_wall};

        if(TileUtils.noSurroundingTilesMatch(tileStack, tileStacks,tilesToMatch, key, true)) {
            return;
        }

        if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_tl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_tr.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_br.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_bl.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.up, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_b.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.left)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_t.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.right, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_l.asTex());
        } else if(TileUtils.onlyMatch4Ways(tileStackMap, tilesToMatch, key, Facing.down, Facing.left, Facing.up)) {
            tileStack.getTile(key).setTex(ImgTiles.cave_wall_r.asTex());
        }
    }

    private static void transformWorldGrassIntoShore(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key) {
        TileName tileToMatch = TileName.world_water;
        TileUtils.getSurroundingTileStacks(tileStack,tileStacks,tileToMatch,key,false).forEach(stack -> {
            TileUtils.transformStack(stack, TileName.world_shore, key);
        });
    }
}
