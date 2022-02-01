package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public enum ImgUi {
    cursor(0, 0, 14, 10),
    cursor_down(14, 0, 10, 6),
    nodebar(0, 10, 17, 3),
    nodebar_diagonal(0, 13, 18, 18),
    grapple(0, 32, 16, 16),
    bomb(16, 32, 16, 16),
    cast(24, 0, 16, 16),
    fighterbox_player(40, 0, 17, 9),
    fighterbox_enemy(40, 9, 17, 9),
    fighterbox_cursor(40, 18, 4, 5),
    ;

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    ImgUi(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TextureRegionE asTex() {
        return Atlas.toTex(this);
    }
}
