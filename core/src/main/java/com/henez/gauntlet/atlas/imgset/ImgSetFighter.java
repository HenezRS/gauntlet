package com.henez.gauntlet.atlas.imgset;

import com.henez.gauntlet.atlas.img.ImgFighters;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public enum ImgSetFighter {
    warrior(ImgFighters.warrior_idle_0, ImgFighters.warrior_idle_1, ImgFighters.warrior_attack_0, ImgFighters.warrior_attack_1, ImgFighters.warrior_prepare_skill,
            ImgFighters.warrior_prepare_cast_0, ImgFighters.warrior_prepare_cast_1, ImgFighters.warrior_cast, ImgFighters.warrior_victory, ImgFighters.warrior_hit, ImgFighters.warrior_low, ImgFighters.warrior_dead),
    hero(ImgFighters.hero_idle_0, ImgFighters.hero_idle_1, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0,
            ImgFighters.hero_idle_0, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0, ImgFighters.hero_idle_0);

    TextureRegionE idle0;
    TextureRegionE idle1;
    TextureRegionE attack0;
    TextureRegionE attack1;
    TextureRegionE prepare;
    TextureRegionE cast0;
    TextureRegionE cast1;
    TextureRegionE castFinish;
    TextureRegionE victory;
    TextureRegionE hit0;
    TextureRegionE hit1;
    TextureRegionE low;
    TextureRegionE dead;
    TextureRegionE portrait;

    ImgSetFighter(ImgFighters idle0,
                  ImgFighters idle1,
                  ImgFighters attack0,
                  ImgFighters attack1,
                  ImgFighters prepare,
                  ImgFighters cast0,
                  ImgFighters cast1,
                  ImgFighters castFinish,
                  ImgFighters victory,
                  ImgFighters hit,
                  ImgFighters low,
                  ImgFighters dead) {
        this.idle0 = idle0.asTex();
        this.idle1 = idle1.asTex();
        this.attack0 = attack0.asTex();
        this.attack1 = attack1.asTex();
        this.prepare = prepare.asTex();
        this.cast0 = cast0.asTex();
        this.cast1 = cast1.asTex();
        this.castFinish = castFinish.asTex();
        this.victory = victory.asTex();
        this.hit0 = hit.asTex().withAdd(2, 0);
        this.hit1 = hit.asTex().withAdd(0, 0);
        this.low = low.asTex();
        this.dead = dead.asTex();
        this.portrait = idle0.asTexPortrait();
    }
}
