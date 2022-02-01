package com.henez.gauntlet.world.sprite;

import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.misc.TextureRegionE;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class SpriteAnimation {
    private GameList<TextureRegionE> texs;
    private float tick;
    private float delay;
    private float speed;
    private int currentFrame;
    private int frameCount;
    private boolean playOnce;
    private boolean donePlaying;

    private int keyFrame;
    private boolean keyFrameDone;
    private boolean keyFrameDoneThisFrame;

    public SpriteAnimation(AnimationDynamic animationDynamic) {
        texs = new GameList<>();
        texs.addAll(Arrays.asList(animationDynamic.getTexs()));

        delay = animationDynamic.getDelay();
        speed = animationDynamic.getSpeed();
        frameCount = texs.size();
        playOnce = false;
        keyFrame = animationDynamic.getKeyFrame();

        reset();
    }

    public void update() {
        update(1);
    }

    public void update(int speedMul) {
        keyFrameDoneThisFrame = false;

        tick += speed*speedMul;
        if(tick>=delay) {
            tick -= delay;
            progressFrame();
        }
    }

    private void progressFrame() {
        currentFrame++;
        if(currentFrame>=frameCount) {
            currentFrame = 0;
            donePlaying = true;
        }

        if (((currentFrame >= keyFrame) || donePlaying) && !keyFrameDone) {
            keyFrameDone = true;
            keyFrameDoneThisFrame = true;
        }
    }

    public void resetAndPlayOnce(float speed) {
        playOnce = true;
        this.speed = speed;
        reset();
    }

    public void reset() {
        currentFrame = 0;
        tick = 0;
        donePlaying = false;

        keyFrameDone = false;
        keyFrameDoneThisFrame = false;
    }

    public TextureRegionE getCurrent() {
        return texs.get(currentFrame);
    }
}
