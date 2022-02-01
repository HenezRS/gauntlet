package com.henez.gauntlet.singletons;

import com.henez.gauntlet.drawers.TextDrawer;
import com.henez.gauntlet.atlas.img.ImgMapBack;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.misc.Timer;
import lombok.Setter;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.viewport.StretchViewport;

import static com.henez.gauntlet.constants.Constants.SEC4;

public class Camera {
    private static Camera camera = null;

    private float x;
    private float y;
    private int w;
    private int h;

    //todo: make object
    private float mapBackX;
    private float mapBackY;
    @Setter private ImgMapBack mapBack;
    private float mapBackAddX;
    private float mapBackAddY;
    private Timer mapBackTimer;

    private StretchViewport viewportGame;
    private StretchViewport viewportText;

    private Camera() {
        x = 0;
        y = 0;
        w = Constants.cameraPixelW;
        h = Constants.cameraPixelH;
        viewportGame = new StretchViewport(Constants.cameraPixelW, Constants.cameraPixelH);
        viewportText = new StretchViewport(Constants.windowPixelW, Constants.windowPixelH);
        mapBackTimer = new Timer(SEC4);
    }

    public void update() {
        if(mapBackTimer.update()) {
            mapBackTimer.reset();
            /*mapBackAddX += mapBack.getMoveX();
            mapBackAddY += mapBack.getMoveY();*/
            if(mapBackAddX>=16) {
                mapBackAddX-=16;
            }
            if(mapBackAddY>=16) {
                mapBackAddY-=16;
            }
        }
    }

    public void translate(float addX, float addY) {
        x += addX;
        y += addY;
    }

    /*public void setPosCenteredOnMapObject(MapActor obj, int mapW, int mapH) {
        this.x = Numbers.clamp((int)(obj.getX()+(Constants.tilePixelSize/2)-(Constants.cameraPixelW/2)),0,mapW-Constants.cameraPixelW);
        this.y = Numbers.clamp((int)(obj.getY()+(Constants.tilePixelSize/2)-(Constants.cameraPixelH/2)),0,mapH-Constants.cameraPixelH);

        *//*this.x = (int)(obj.getX()+(Constants.tilePixelSize/2)-(Constants.cameraPixelW/2));
        this.y = (int)(obj.getY()+(Constants.tilePixelSize/2)-(Constants.cameraPixelH/2));*//*
    }

    public void initialPosCenteredOnMapObject(MapActor obj, int mapW, int mapH) {
        setPosCenteredOnMapObject(obj, mapW, mapH);
        snapMapBack();
    }*/

    public void snapMapBack() {
        mapBackX = x-32;
        mapBackY = y-32;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public float getTotalMapBackX() {
        return mapBackX+mapBackAddX;
    }

    public float getTotalMapBackY() {
        return mapBackY+mapBackAddY;
    }

    public void setViewPortText(Graphics g) {
        viewportText.apply(g);
        TextDrawer.getInstance().setLowResMode(false);
    }

    public void setViewPortGame(Graphics g) {
        viewportGame.apply(g);
        TextDrawer.getInstance().setLowResMode(true);
    }

    public void resetViewport(Graphics g) {
        setViewPortGame(g);
    }

    public static Camera getInstance() {
        if(camera==null) {
            camera = new Camera();
        }
        return camera;
    }
}
