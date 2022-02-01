package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public enum ImgFighters {
    warrior_idle_0(0, 0),
    warrior_idle_1(1, 0),
    warrior_attack_0(2, 0),
    warrior_attack_1(3, 0),
    warrior_prepare_skill(4, 0),
    warrior_prepare_cast_0(5, 0),
    warrior_prepare_cast_1(6, 0),
    warrior_cast(7, 0),
    warrior_victory(8, 0),
    warrior_hit(9, 0),
    warrior_low(10, 0),
    warrior_dead(11, 0),
    hero_idle_0(0, 1),
    hero_idle_1(1, 1),
    ;

    private final int x;
    private final int y;

    ImgFighters(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TextureRegionE asTex() {
        return Atlas.toTex(this);
    }

    public TextureRegionE asTexPortrait() {
        return Atlas.toTexPortrait();
    }
}
