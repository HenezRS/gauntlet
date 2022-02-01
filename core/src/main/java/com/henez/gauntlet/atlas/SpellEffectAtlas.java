package com.henez.gauntlet.atlas;

import com.henez.gauntlet.atlas.img.ImgUi;
import com.henez.gauntlet.misc.TextureRegionE;
import com.henez.gauntlet.world.sprite.AnimationDynamic;
import lombok.Getter;

import static com.henez.gauntlet.atlas.img.ImgSpells.*;
import static com.henez.gauntlet.constants.Constants.SEC10;
import static com.henez.gauntlet.constants.Constants.SEC20;

@Getter
public enum SpellEffectAtlas {
    slash(SEC20, 2, slash_0.asTex(), slash_1.asTex(), slash_2.asTex(), slash_3.asTex(), slash_4.asTex()),
    flame(SEC10, 1, flame_0.asTex(), flame_1.asTex(), flame_0.asTex(), flame_1.asTex(), flame_0.asTex(), flame_1.asTex()),
    greenflame(SEC10, 1, greenflame_0.asTex(), greenflame_1.asTex(), greenflame_0.asTex(), greenflame_1.asTex(), greenflame_0.asTex(), greenflame_1.asTex()),
    ice(SEC10, 3, ice_0.asTex(), ice_1.asTex(), ice_2.asTex(), ice_3.asTex(), ice_4.asTex(), ice_5.asTex(), ice_6.asTex(), ice_7.asTex()),
    bolt(SEC10, 1, bolt_0.asTex(), bolt_1.asTex(), bolt_0.asTex(), bolt_1.asTex(), bolt_0.asTex(), bolt_1.asTex()),
    cast(SEC10, 0, ImgUi.cast.asTex(), ImgUi.cast.asTex(), ImgUi.cast.asTex(), ImgUi.cast.asTex().withAlpha(0.75f), ImgUi.cast.asTex().withAlpha(0.5f), ImgUi.cast.asTex().withAlpha(0.25f)),
    fist_hit(SEC10, 0, ImgUi.cast.asTex(), ImgUi.cast.asTex().withAlpha(0.75f), ImgUi.cast.asTex().withAlpha(0.5f), ImgUi.cast.asTex().withAlpha(0.25f)),
    ;

    private float delay;
    private int keyFrame;
    private TextureRegionE[] textureRegions;

    SpellEffectAtlas(float delay, int keyFrame, TextureRegionE... textureRegions) {
        this.delay = delay;
        this.keyFrame = keyFrame;
        this.textureRegions = textureRegions;
    }

    public AnimationDynamic toDynamic() {
        return new AnimationDynamic(delay, keyFrame, textureRegions);
    }

    public AnimationDynamic toDynamicWithSpeed(float newSpeed) {
        return new AnimationDynamic(delay, keyFrame, textureRegions).withSpeed(newSpeed);
    }
}
