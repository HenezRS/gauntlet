package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgEnemies {
    imp(0, 0),
    orc(1, 0),
    fairy_eater(6,0,2)
    ;

    private final int x;
    private final int y;
    private final int w;

    ImgEnemies(int x, int y) {
        this.x = x;
        this.y = y;
        w = 1;
    }

    ImgEnemies(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
