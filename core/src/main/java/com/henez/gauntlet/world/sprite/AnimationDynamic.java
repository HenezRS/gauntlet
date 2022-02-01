package com.henez.gauntlet.world.sprite;

import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

@Getter
public class AnimationDynamic {
    private TextureRegionE[] texs;
    private float delay;
    private float speed;
    private int keyFrame;

    public AnimationDynamic(TextureRegionE... texs) {
        this.texs = texs;
        this.speed = 1.0f;
        this.delay = 1.0f;
        this.keyFrame = 0;
    }

    public AnimationDynamic(float delay, int keyFrame, TextureRegionE... texs) {
        this.texs = texs;
        this.speed = 1.0f;
        this.delay = delay;
        this.keyFrame = keyFrame;
    }

    public AnimationDynamic withSpeed(float speed) {
        this.speed = speed;
        return this;
    }
}
