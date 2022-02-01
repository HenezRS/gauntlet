package com.henez.gauntlet.atlas.imgset;

import com.henez.gauntlet.atlas.img.ImgActors;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public enum ImgSetActors {
    hero(ImgActors.hero_right_0,ImgActors.hero_right_1,ImgActors.hero_up_0, ImgActors.hero_up_1, ImgActors.hero_left_0, ImgActors.hero_left_1, ImgActors.hero_down_0, ImgActors.hero_down_1),
    hero_mini(ImgActors.hero_mini_right_0,ImgActors.hero_mini_right_1,ImgActors.hero_mini_up_0, ImgActors.hero_mini_up_1,
            ImgActors.hero_mini_left_0, ImgActors.hero_mini_left_1, ImgActors.hero_mini_down_0, ImgActors.hero_mini_down_1);

    TextureRegionE right0;
    TextureRegionE right1;
    TextureRegionE up0;
    TextureRegionE up1;
    TextureRegionE left0;
    TextureRegionE left1;
    TextureRegionE down0;
    TextureRegionE down1;

    ImgSetActors(ImgActors right0,
            ImgActors right1,
            ImgActors up0,
            ImgActors up1,
            ImgActors left0,
            ImgActors left1,
            ImgActors down0,
            ImgActors down1) {
        this.right0 = right0.asTex();
        this.right1 = right1.asTex();
        this.up0 = up0.asTex();
        this.up1 = up1.asTex();
        this.left0 = left0.asTex();
        this.left1 = left1.asTex();
        this.down0 = down0.asTex();
        this.down1 = down1.asTex();
    }
}
