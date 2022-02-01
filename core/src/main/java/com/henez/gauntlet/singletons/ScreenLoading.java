package com.henez.gauntlet.singletons;

import com.henez.gauntlet.misc.Timer;
import com.henez.gauntlet.world.teleport.TeleportDestination;
import lombok.Getter;
import lombok.Setter;

import static com.henez.gauntlet.constants.Constants.SEC3;
import static com.henez.gauntlet.constants.Constants.SEC4;

@Getter
public class ScreenLoading {
    private static ScreenLoading screenLoading = null;

    private Timer start;
    private Timer pause;
    private Timer end;
    @Setter private boolean transitionReady;
    private boolean transitionReadyNextFrame;
    private boolean shouldDraw;

    private TeleportDestination dest;

    private ScreenLoading() {
        start = new Timer(SEC4);
        pause = new Timer(SEC3);
        end = new Timer(SEC4);

        start.setFinish();
        shouldDraw = true;
        transitionReadyNextFrame = false;
    }

    public void update() {
        shouldDraw = true;
        transitionReady = false;

        if(transitionReadyNextFrame) {
            transitionReadyNextFrame = false;
            transitionReady = true;
        }

        if (!start.isDone()) {
            if(start.update()) {
                transitionReadyNextFrame = true;
            }
        } else if (!pause.isDone()) {
            pause.update();
        } else if (!end.isDone()) {
            end.update();

            if(end.isDone()) {
                shouldDraw = false;
            }
        } else {
            shouldDraw = false;
        }
    }

    public void reset() {
        start.reset();
        pause.reset();
        end.reset();
        transitionReady = false;
        transitionReadyNextFrame = false;
    }

    public void beginNewTeleport(TeleportDestination dest) {
        reset();
        this.dest = dest;
    }

    public boolean isInProgress() {
        return shouldDraw;
    }

    public static ScreenLoading getInstance() {
        if(screenLoading ==null) {
            screenLoading = new ScreenLoading();
        }
        return screenLoading;
    }
}
