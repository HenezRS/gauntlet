package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.constants.Constants;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgTiles {
    none,
    grass,
    grass_tall,
    flowers,
    tree_base,
    tree,
    tree_top,
    chest,
    chest_open,
    cliff,
    water,
    stairs,
    pavement,
    fountain,
    door,
    roof_brown,
    roof_brown_top,
    chimney,
    roof_detail_left,
    roof_detail_top,
    roof_detail_right,
    roof_detail_middle,
    roof_grey_top,
    roof_grey,
    wall_house,
    wall_house_2,
    wall_stone,
    wall_stone_lower,
    edge_left,
    edge_topleft,
    edge_top,
    edge_topright,
    edge_right,
    waterfall,
    world_grass,
    world_mountain,
    world_cave_door,
    world_forest,
    world_town,
    world_mountain_tl,
    world_mountain_t,
    world_mountain_tr,
    world_mountain_l,
    world_mountain_r,
    world_mountain_bl,
    world_mountain_b,
    world_mountain_br,
    world_mountain_single,
    world_forest_tl,
    world_forest_t,
    world_forest_tr,
    world_forest_l,
    world_forest_r,
    world_forest_bl,
    world_forest_b,
    world_forest_br,
    world_water_edge_tl,
    world_water_edge_t,
    world_water_edge_tr,
    world_water_edge_l,
    world_water_edge_r,
    world_water_edge_bl,
    world_water_edge_b,
    world_water_edge_br,
    cave_floor,
    cave_wall,
    cave_wall_tl,
    cave_wall_t,
    cave_wall_tr,
    cave_wall_l,
    cave_wall_r,
    cave_wall_bl,
    cave_wall_b,
    cave_wall_br,
    rcave_wall,
    rcave_wall_d,
    rcave_floor,
    rcave_stairs,
    cave_entry,
    stairs_down,
    stairs_up,
    black,
    crystal_ball,
    gate,
    purple_grass,
    purple_grass_tall,
    purple_flowers,
    purple_tree_base,
    purple_tree,
    purple_tree_top,
    ;

    private final int x;
    private final int y;

    ImgTiles() {
        this.x = this.ordinal() % Constants.tilesDocumentWidthInTiles;
        this.y = this.ordinal() / Constants.tilesDocumentWidthInTiles;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
