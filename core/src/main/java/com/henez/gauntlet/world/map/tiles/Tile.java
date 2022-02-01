package com.henez.gauntlet.world.map.tiles;

import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public class Tile {
    private TileName tileName;
    private TextureRegion tex;
    private boolean drawable;
    private boolean walkable;

    public Tile(TileName tileName) {
        build(tileName);
    }

    public void build(TileName tileName) {
        this.tileName = tileName;
        walkable = tileName.isWalkable();

        drawable = false;
        if(tileName.getImg() != null) {
            setTex(tileName.getImg().asTex());
        }
    }

    public void setTex(TextureRegion tex) {
        this.tex = tex;
        drawable = true;
    }
}
