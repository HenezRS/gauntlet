package com.henez.gauntlet.drawers;

import com.badlogic.gdx.graphics.Color;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.Numbers;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.singletons.Camera;
import lombok.Getter;
import org.mini2Dx.core.graphics.Graphics;

@Getter
public class ShapeDrawer {
    private static ShapeDrawer drawer = null;

    public static Color COLOR_TEXT_DEFAULT = Colors.white.color;

    private Camera camera;

    private ShapeDrawer() {
        camera = Camera.getInstance();
    }

    public void drawHardwareLineToCamera(Graphics g, float x, float y, float xx, float yy, Color color, int size) {
        g.setLineHeight(size);
        g.setColor(color);
        g.drawLineSegment(x, y, xx, yy);
    }

    public void drawFillToCamera(Graphics g, float x, float y, float w, float h, Color color) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public void drawBlackToCameraFade(Graphics g, float x, float y, float w, float h) {
        drawFillToCamera(g, x-6, y-6, w+14, h+14, Colors.black_25.color);
        drawFillToCamera(g, x-4, y-4, w+10, h+10, Colors.black_25.color);
        drawFillToCamera(g, x, y, w, h, Colors.black_25.color);
    }

    public void drawBlackToCameraFadeFull(Graphics g, float x, float y, float w, float h) {
        drawFillToCamera(g, x-4, y-4, w+8, h+8, Colors.black_25.color);
        drawFillToCamera(g, x-2, y-2, w+4, h+4, Colors.black_25.color);
        drawFillToCamera(g, x, y, w, h, Colors.black.color);
    }

    public void drawFillEntireCamera(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, Constants.cameraPixelW, Constants.cameraPixelH);
    }

    public void drawWindowToCamera(Graphics g, float x, float y, float w, float h) {
        Color back = Colors.window_back.color;
        Color borderOuter = Colors.window_outer_border.color;

        //drawFillToCamera(g, x, y, w, h, back);
        //drawFillToCamera(g, x+1, y+1, w-2, h-2, borderOuter);
        drawFillToCamera(g, x, y, w, h, borderOuter);
        drawFillToCamera(g, x+1, y+1, w-2, h-2, back);
    }

    public void drawSubWindowToCamera(Graphics g, float x, float y, float w, float h) {
        Color back = Colors.window_back_darker.color;
        Color borderOuter = Colors.window_outer_border.color;
        Color borderInner = Colors.window_inner_border.color;

        drawFillToCamera(g, x, y, w, h, back);
        drawFillToCamera(g, x+1, y+1, w-2, h-2, borderOuter);
        drawFillToCamera(g, x+2, y+2, w-4, h-4, borderInner);
        drawFillToCamera(g, x+3, y+3, w-6, h-6, back);
    }

    public void drawBasicWindowToCamera(Graphics g, float x, float y, float w, float h) {
        Color borderInner = Colors.window_inner_border.color;

        drawFillToCamera(g, x, y, w, h, borderInner);
    }

    public void drawBarToCamera(Graphics g, float x, float y, float w, float h, float percent, Color color, Color colorUnder) {
        drawFillToCamera(g, x, y, w, h, colorUnder);
        float percentW = Numbers.floor(w * percent);
        /*percentW = Numbers.clamp((int)percentW+1,0,w);
        if (w > 1) {
            drawFillToCamera(g, x, y, (int)percentW, h, Colors.ui_back.color);
        }*/
        //percentW = Numbers.clamp((int)percentW,0,w);
        drawFillToCamera(g, x, y, (int)percentW, h, color);
    }

    public void drawBarNoBackToCamera(Graphics g, float x, float y, float w, float h, float percent, Color color) {
        float percentW = Numbers.floor(w * percent);
        drawFillToCamera(g, x, y, (int)percentW, h, color);
    }

    public void drawFillToCameraFull(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0,0, camera.getW(), camera.getH());
    }

    public void drawPixelToCamera(Graphics g, float x, float y, Color color) {
        g.setColor(color);
        g.fillRect(x,y,1,1);
    }

    public static ShapeDrawer getInstance() {
        if(drawer==null) {
            drawer = new ShapeDrawer();
        }
        return drawer;
    }
}
