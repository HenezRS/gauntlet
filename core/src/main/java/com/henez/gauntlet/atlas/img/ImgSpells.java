package com.henez.gauntlet.atlas.img;
;
import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public enum ImgSpells {
    slash_0(0, 0),
    slash_1(1, 0),
    slash_2(2, 0),
    slash_3(3, 0),
    slash_4(4, 0),
    flame_0(0, 1),
    flame_1(1, 1),
    greenflame_0(2, 1),
    greenflame_1(3, 1),
    bolt_0(0, 2),
    bolt_1(1, 2),
    ice_0(0, 3),
    ice_1(1, 3),
    ice_2(2, 3),
    ice_3(3, 3),
    ice_4(4, 3),
    ice_5(5, 3),
    ice_6(6, 3),
    ice_7(7, 3),
    ;

    private final int x;
    private final int y;

    ImgSpells(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionE asTex() {
        return Atlas.toTex(this);
    }
}
