package com.henez.gauntlet.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class Framerate {
    private long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private float secondsSinceGameStart;

    public Framerate() {
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        secondsSinceGameStart = 0;
    }

    public void update(float delta) {
        lastTimeCounted = TimeUtils.millis();
        secondsSinceGameStart += delta;

        sinceChange += delta;
        if (sinceChange >= 1) {
            sinceChange -= 1;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    public float getFrameRate() {
        return frameRate;
    }

    public float getMillisSinceGameStart() {
        return secondsSinceGameStart * 1000;
    }

    public int getSecondsSinceGameStart() {
        return (int) secondsSinceGameStart;
    }
}
