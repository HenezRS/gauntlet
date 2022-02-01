package com.henez.gauntlet.world.map.tiles;

import com.henez.gauntlet.atlas.img.ImgTiles;
import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.datastructures.GameList;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TileName {
    none(-1,-1,null),
    grass(0,0,null),
    grass_tall(1,0,ImgTiles.grass_tall),
    flowers(2,0,ImgTiles.flowers),
    tree(3,0,ImgTiles.tree, false),
    chest(4,0, ImgTiles.chest, false),
    cliff(5,0,ImgTiles.cliff, false),
    water(6,0,ImgTiles.water, false),
    stairs(7,0,ImgTiles.stairs),
    pavement(8,0,ImgTiles.pavement),
    wall_house(9,0,ImgTiles.wall_house, false),
    wall_house_2(10,0,ImgTiles.wall_house_2, false),
    door(11,0,ImgTiles.door),
    chimney(12,0,ImgTiles.chimney, false),
    roof_brown(13,0,ImgTiles.roof_brown, false),
    roof_detail(14,0,ImgTiles.roof_detail_middle, false),
    fountain(15,0,ImgTiles.fountain, false),
    wall_stone(16,0,ImgTiles.wall_stone, false),
    edge_left(17,0,ImgTiles.edge_left, Facing.left, Facing.downleft, Facing.upleft),
    edge_topleft(18,0,ImgTiles.edge_topleft, Facing.left, Facing.downleft, Facing.upleft, Facing.up, Facing.upright),
    edge_top(19,0,ImgTiles.edge_top, Facing.up, Facing.upleft, Facing.upright),
    edge_topright(20,0,ImgTiles.edge_topright, Facing.right, Facing.downright, Facing.upright, Facing.up, Facing.upleft),
    edge_right(21,0,ImgTiles.edge_right, Facing.right, Facing.downright, Facing.upright),
    waterfall(22,0,ImgTiles.waterfall, false),
    roof_grey(23,0,ImgTiles.roof_grey, false),

    world_grass(0,1,ImgTiles.world_grass),
    world_mountain(1,1,ImgTiles.world_mountain, false),
    world_cave_door(2,1,ImgTiles.world_cave_door),
    world_forest(3,1,ImgTiles.world_forest),
    world_town(4,1,ImgTiles.world_town),
    world_water(5,1,null, false),
    world_shore(6,1,null, false),

    cave_floor(0,2,null),
    cave_wall(1,2,ImgTiles.cave_wall, false),
    black(2,2,ImgTiles.black, false),
    crystal_ball(3,2,ImgTiles.crystal_ball, false),
    gate(4,2,ImgTiles.gate, false),

    rcave_floor(0,3,ImgTiles.rcave_floor),
    rcave_wall(1,3,ImgTiles.rcave_wall, false),
    rcave_stairs(2,3,ImgTiles.rcave_stairs),
    cave_entry(3,3,ImgTiles.cave_entry),
    stairs_down(4,3,ImgTiles.stairs_down),
    stairs_up(5,3,ImgTiles.stairs_up),
    ;

    private int index;
    private ImgTiles img;
    private boolean walkable;
    private GameList<Facing> forbiddenMovement;

    TileName(int x, int y, ImgTiles img) {
        this.img = img;
        this.walkable = true;
        forbiddenMovement = new GameList<>();
        this.index = ((y*32)+x);
    }

    TileName(int x, int y, ImgTiles img, boolean walkable) {
        this.img = img;
        this.walkable = walkable;
        forbiddenMovement = new GameList<>();
        this.index = ((y*32)+x);
    }

    TileName(int x, int y, ImgTiles img, Facing... forbiddenMovement) {
        this.img = img;
        this.walkable = true;
        this.forbiddenMovement = new GameList<>();
        this.forbiddenMovement.addAll(forbiddenMovement);
        this.index = ((y*32)+x);
    }

    public static TileName fromIndex(int i) {
        return Arrays.stream(TileName.values()).filter(tn -> tn.getIndex()==i).findFirst().orElse(TileName.grass);
    }
}
