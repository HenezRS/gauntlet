package com.henez.gauntlet.drawers;

import com.badlogic.gdx.graphics.Color;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.singletons.ScreenLoading;
import org.mini2Dx.core.graphics.Graphics;

public final class ScreenLoadingDrawer {
    private ScreenLoadingDrawer() {

    }

    public static void draw(Graphics g) {
        ScreenLoading screenLoading = ScreenLoading.getInstance();

        if(screenLoading.isShouldDraw()) {
            Color c = Colors.black.create();
            if (!screenLoading.getStart().isDone()) {
                c.a = screenLoading.getStart().getPercentDone();
            } else if (!screenLoading.getPause().isDone()) {
                c.a = 1;
            } else if (!screenLoading.getEnd().isDone()) {
                c.a = screenLoading.getEnd().getPercentRemaining();
            }

            ShapeDrawer.getInstance().drawFillEntireCamera(g, c);
        }
    }
}
