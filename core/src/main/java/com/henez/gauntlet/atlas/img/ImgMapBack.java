package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgMapBack {
    grass("mb_grass"),
    water("mb_water", 1, 0),
    cave("mb_cave"),
    black("mb_black"),
    grass_dark("mb_grass_dark"),
    ;

    private final String pathName;
    private final int moveX;
    private final int moveY;

    ImgMapBack(String pathName) {
        this.pathName = pathName;
        this.moveX = 0;
        this.moveY = 0;
    }

    ImgMapBack(String pathName, int moveX, int moveY) {
        this.pathName = pathName;
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
