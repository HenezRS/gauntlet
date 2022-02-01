package com.henez.gauntlet.drawers;

import com.badlogic.gdx.graphics.Color;
import com.henez.gauntlet.atlas.img.ImgUi;
import com.henez.gauntlet.datastructures.FacingDist;
import com.henez.gauntlet.datastructures.XY;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.misc.TextureRegionE;
import com.henez.gauntlet.singletons.Camera;
import lombok.Getter;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public class TextureDrawer {
    private static TextureDrawer textureDrawer = null;

    private Camera camera;

    private TextureDrawer() {
        camera = Camera.getInstance();
    }

    public void drawToWorld(Graphics g, TextureRegion tex, float x, float y) {
        g.drawTextureRegion(tex,x-camera.getX(),y-camera.getY());
    }
    public void drawToWorld(Graphics g, TextureRegionE tex, float x, float y) {
        g.drawTextureRegion(tex.getTex(),x-camera.getX()+tex.getAddX(),y-camera.getY()+tex.getAddY());
    }

    public void drawToCamera(Graphics g, TextureRegion tex, float x, float y) {
        g.drawTextureRegion(tex,x,y);
    }

    public void drawToCamera(Graphics g, TextureRegion tex, float x, float y, float rotation) {
        g.drawTextureRegion(tex,x,y, tex.getRegionWidth(),tex.getRegionHeight(),rotation);
    }

    public void drawToCameraWithAngle(Graphics g, TextureRegion tex, float x, float y, FacingDist facingDist) {
        g.drawTextureRegion(tex,x,y, tex.getRegionWidth(),tex.getRegionHeight(),facingDist.getFacing().angle);
    }

    public void drawToCamera(Graphics g, TextureRegionE tex, float x, float y) {
        g.setTint(new Color(1.0f,1.0f,1.0f,tex.getAlpha()));
        g.drawTextureRegion(tex.getTex(),x+tex.getAddX(),y+tex.getAddY());
        g.setTint(Colors.white.color);
    }

    public void drawCursorToCamera(Graphics g, float x, float y) {
        drawToCamera(g, ImgUi.cursor.asTex(), x-16,y-4);
    }

    public void drawCursorToCameraBattleTarget(Graphics g, XY xy) {
        drawToCamera(g, ImgUi.cursor.asTex(), xy.getX()-16,xy.getY()-4);
    }

    public void drawCursorPointingToCamera(Graphics g, float x, float y) {
        drawToCamera(g, ImgUi.cursor.asTex(), x-16,y-4);
    }

    public void drawCursorToCameraText(Graphics g, float x, float y) {
        drawToCamera(g, ImgUi.cursor.asTex(), x-16,y-1);
    }

    /*public void drawCursorToCameraFighter(Graphics g, Fighter fighter) {
        drawToCamera(g, ImgUi.cursor.asTex(), fighter.getX()+fighter.getCursorXAdd()-16+fighter.getTransformManager().getAddX(),fighter.getY()+fighter.getCursorYAdd()-2+fighter.getTransformManager().getAddY());
    }*/

    public static TextureDrawer getInstance() {
        if(textureDrawer ==null) {
            textureDrawer = new TextureDrawer();
        }
        return textureDrawer;
    }
}
