package com.henez.gauntlet.world.map.tiles;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.datastructures.Numbers;
import com.henez.gauntlet.world.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class TileUtils {
    private TileUtils(){}

    public static TileStack getTileStackAt(GameList<TileStack> tileStacks, int gx, int gy) {
        return tileStacks.stream().filter(ts -> ts.getGx()==gx && ts.getGy()==gy).findFirst().orElse(null);
    }

    public static TileStack getTileStackTowards(GameList<TileStack> tileStacks, TileStack tileStack, Facing facing) {
        return tileStacks.stream().filter(ts -> ts.getGx()==tileStack.getGx()+facing.tx && ts.getGy()==tileStack.getGy()+facing.ty).findFirst().orElse(null);
    }

    public static Map<Facing,TileStack> getTileStacksTowards(GameList<TileStack> tileStacks, TileStack tileStack, Facing... facing) {
        Map<Facing,TileStack> filtered = new HashMap<>();
        Arrays.stream(facing).forEach(f -> {
            filtered.put(f, getTileStackTowards(tileStacks,tileStack,f));
        });
        return filtered;
    }

    public static int countSurroundingTileNames(TileStack tileStack, GameList<TileStack> tileStacks, TileName tileName, TileKey key, boolean cardinalOnly) {
        int count = 0;
        boolean isCardinal = false;

        int startX = Numbers.clamp(tileStack.getGx()-1, 0, World.MAP_GW-1);
        int endX = Numbers.clamp(tileStack.getGx()+1, 0, World.MAP_GW-1);
        int startY = Numbers.clamp(tileStack.getGy()-1, 0, World.MAP_GH-1);
        int endY = Numbers.clamp(tileStack.getGy()+1, 0, World.MAP_GH-1);

        for(int i=startX; i<=endX; ++i) {
            for(int j=startY; j<=endY; ++j) {
                if(tileStack.getGx() != i || tileStack.getGy() != j) {
                    if(!cardinalOnly || isCardinal) {
                        if (TileUtils.getTileStackAt(tileStacks, i, j).getTile(key).getTileName() == tileName) {
                            count++;
                        }
                    }
                }
                isCardinal = !isCardinal;
            }
        }
        return count;
    }

    public static boolean anySurroundingTilesMatch(TileStack tileStack, GameList<TileStack> tileStacks, TileName[] tileNames, TileKey key, boolean cardinalOnly) {
        for(TileName tn : tileNames) {
            if(countSurroundingTileNames(tileStack,tileStacks,tn,key,cardinalOnly)>=1) {
                return true;
            }
        }
        return false;
    }

    public static boolean noSurroundingTilesMatch(TileStack tileStack, GameList<TileStack> tileStacks, TileName[] tileNames, TileKey key, boolean cardinalOnly) {
        return !anySurroundingTilesMatch(tileStack,tileStacks,tileNames,key,cardinalOnly);
    }

    public static Facing findFirstFacingToTileName(TileStack tileStack, GameList<TileStack> tileStacks, TileName tileName, TileKey key, boolean cardinalOnly) {
        boolean isCardinal = false;

        int startX = Numbers.clamp(tileStack.getGx()-1, 0, World.MAP_GW-1);
        int endX = Numbers.clamp(tileStack.getGx()+1, 0, World.MAP_GW-1);
        int startY = Numbers.clamp(tileStack.getGy()-1, 0, World.MAP_GH-1);
        int endY = Numbers.clamp(tileStack.getGy()+1, 0, World.MAP_GH-1);

        for(int i=startX; i<=endX; ++i) {
            for(int j=startY; j<=endY; ++j) {
                if(tileStack.getGx() != i || tileStack.getGy() != j) {
                    if(!cardinalOnly || isCardinal) {
                        if (TileUtils.getTileStackAt(tileStacks, i, j).getTile(key).getTileName() == tileName) {
                            return Facing.byDir(tileStack.getGx(), tileStack.getGy(), i, j);
                        }
                    }
                }
                isCardinal = !isCardinal;
            }
        }
        return null;
    }

    public static GameList<TileStack> getSurroundingTileStacks(TileStack tileStack, GameList<TileStack> tileStacks, TileName tileName, TileKey key, boolean cardinalOnly) {
        GameList<TileStack> ts = new GameList<>();
        boolean isCardinal = false;

        int startX = Numbers.clamp(tileStack.getGx()-1, 0, World.MAP_GW-1);
        int endX = Numbers.clamp(tileStack.getGx()+1, 0, World.MAP_GW-1);
        int startY = Numbers.clamp(tileStack.getGy()-1, 0, World.MAP_GH-1);
        int endY = Numbers.clamp(tileStack.getGy()+1, 0, World.MAP_GH-1);

        for(int i=startX; i<=endX; ++i) {
            for(int j=startY; j<=endY; ++j) {
                if(tileStack.getGx() != i || tileStack.getGy() != j) {
                    if(!cardinalOnly || isCardinal) {
                        TileStack t = TileUtils.getTileStackAt(tileStacks, i, j);
                        if (t.getTile(key).getTileName() == tileName) {
                            ts.add(t);
                        }
                    }
                }
                isCardinal = !isCardinal;
            }
        }
        return ts;
    }

    public static boolean noMatch4Ways(TileStack tileStack, GameList<TileStack> tileStacks, TileKey key, TileName... tileName) {
        int c = 0;
        for(TileName tn : tileName) {
            c += countSurroundingTileNames(tileStack,tileStacks,tn, key, true);
        }
        return c==0;
    }

    public static boolean countMatch4WaysLessOrEqualTo(int count, TileStack tileStack, GameList<TileStack> tileStacks, TileKey key, TileName... tileName) {
        int c = 0;
        for(TileName tn : tileName) {
            c += countSurroundingTileNames(tileStack,tileStacks,tn, key, true);
        }
        return count>=c;
    }

    private static boolean allMatch(Map<Facing, TileStack> tileStackMap, TileName tileName, TileKey key, Facing... facings) {
        return Arrays.stream(facings).allMatch(f -> tileStackMap.get(f).getTile(key).getTileName()==tileName);
    }

    private static boolean allMatch(Map<Facing, TileStack> tileStackMap, TileName[] tileNames, TileKey key, Facing... facings) {
        for(Facing f : facings) {
            boolean match = false;
            for(TileName tn : tileNames) {
                if(tileStackMap.get(f)!=null && tileStackMap.get(f).getTile(key).getTileName()==tn) {
                    match = true;
                }
            }

            if(!match) {
                return false;
            }
        }
        return true;
    }

    private static boolean noneMatch(Map<Facing, TileStack> tileStackMap, TileName tileName, TileKey key, Facing... facings) {
        return Arrays.stream(facings).noneMatch(f -> tileStackMap.get(f).getTile(key).getTileName()==tileName);
    }

    private static boolean noneMatch(Map<Facing, TileStack> tileStackMap, TileName[] tileNames, TileKey key, Facing... facings) {
        for(Facing f : facings) {
            boolean match = false;
            for(TileName tn : tileNames) {
                if(tileStackMap.get(f)!=null && tileStackMap.get(f).getTile(key).getTileName()==tn) {
                    match = true;
                }
            }

            if(match) {
                return false;
            }
        }
        return true;
    }

    public static boolean onlyMatch4Ways(Map<Facing, TileStack> tileStackMap, TileName tileName, TileKey key, Facing... facings) {
        if(!tileStackMapContainsAllFaces(tileStackMap, facings)) {
            return false;
        }

        GameList<Facing> filteredFacing = new GameList<>();
        filteredFacing.addAll(Facing.get4Ways());
        Arrays.stream(facings).forEach(filteredFacing::remove);

        return allMatch(tileStackMap, tileName, key, facings) && noneMatch(tileStackMap, tileName, key, filteredFacing.toArray(Facing[]::new));
    }

    public static boolean onlyMatch4Ways(Map<Facing, TileStack> tileStackMap, TileName[] tileName, TileKey key, Facing... facings) {
        if(!tileStackMapContainsAllFaces(tileStackMap, facings)) {
            return false;
        }

        GameList<Facing> filteredFacing = new GameList<>();
        filteredFacing.addAll(Facing.get4Ways());
        Arrays.stream(facings).forEach(filteredFacing::remove);

        return allMatch(tileStackMap, tileName, key, facings) && noneMatch(tileStackMap, tileName, key, filteredFacing.toArray(Facing[]::new));
    }

    public static boolean tileStackMapContainsAllFaces(Map<Facing, TileStack> tileStackMap, Facing... facings) {
        return Arrays.stream(facings).allMatch(tileStackMap::containsKey);
    }

    public static void transformStack(TileStack tileStack, TileName tileName, TileKey key) {
        tileStack.getTile(key).build(tileName);
    }
}
