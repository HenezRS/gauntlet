package com.henez.gauntlet.misc;

import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.Numbers;
import com.henez.gauntlet.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timer {
    protected int tick;
    protected int delay;
    protected boolean done;
    protected boolean disabled;

    public Timer() {
        disabled = false;
        this.delay = 0;
        reset();
    }

    public Timer(int delay) {
        disabled = false;
        this.delay = delay;
        reset();
    }

    public void setReady() {
        tick = delay;
    }

    public void reset() {
        tick = 0;
        done = false;
    }

    public void reset(int newDelay) {
        delay = newDelay;
        reset();
    }

    public boolean update() {
        if (!done && !disabled) {
            tick++;
            done = tick >= delay && delay > 0;
        }
        return done && !disabled;
    }

    public void setFinish() {
        done = true;
        tick = delay;
    }

    public String getSeconds() {
        float f = (float) tick / Constants.SEC;
        return StringUtils.decimal2(f);
    }

    public float getPercentRemaining() {
        return 1 - getPercentDone();
    }

    public float getPercentDone() {
        return Numbers.percent(tick, delay);
    }

    public float getSecondsRemaining() {
        return delay-tick;
    }
}
