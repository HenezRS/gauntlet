package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public enum ImgActors {
    hero_right_0(0, 0),
    hero_right_1(1, 0),
    hero_up_0(2, 0),
    hero_up_1(3, 0),
    hero_left_0(4, 0),
    hero_left_1(5, 0),
    hero_down_0(6, 0),
    hero_down_1(7, 0),

    hero_mini_right_0(0, 1),
    hero_mini_right_1(1, 1),
    hero_mini_up_0(2, 1),
    hero_mini_up_1(3, 1),
    hero_mini_left_0(4, 1),
    hero_mini_left_1(5, 1),
    hero_mini_down_0(6, 1),
    hero_mini_down_1(7, 1),
    ;

    private final int x;
    private final int y;

    ImgActors(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionE asTex() {
        return Atlas.toTex(this);
    }
}
