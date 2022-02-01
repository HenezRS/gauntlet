package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgEnemiesBig {
    tank(0, 0, 3, 2)
    ;

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    ImgEnemiesBig(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
