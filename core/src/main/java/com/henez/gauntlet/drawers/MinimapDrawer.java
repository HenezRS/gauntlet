package com.henez.gauntlet.drawers;

import com.badlogic.gdx.graphics.Color;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.world.map.minimap.Minimap;
import com.henez.gauntlet.world.map.minimap.MinimapCamera;
import com.henez.gauntlet.world.mapobjects.actions.ControlledPlayer;
import org.mini2Dx.core.graphics.Graphics;

public class MinimapDrawer {
    private static final ShapeDrawer shapeDrawer = ShapeDrawer.getInstance();
    private static final Minimap minimap = Minimap.getInstance();
    private static final MinimapCamera cam = Minimap.getInstance().getMinimapCamera();

    public static void draw(Graphics g, ControlledPlayer player) {
        Color[][] raw = minimap.getCurrentRaw();
        int s = minimap.getScale();

        if(s>0) {
            shapeDrawer.drawWindowToCamera(g, cam.getScreenX(), cam.getScreenY(), (cam.getW()*s)+(6-s), (cam.getH()*s)+(6-s));
        }
        //shapeDrawer.drawFillToCamera(g, (-s)+(cam.getScreenX()+3), (-s)+cam.getScreenY()+3, (s)+(s*cam.getW()), (s)+(s*cam.getH()), Colors.purple_raw.color);
        for(int i=0; i<cam.getW()-1; ++i) {
            for(int j=0; j<cam.getH()-1; ++j) {
                shapeDrawer.drawFillToCamera(g, (i*s)+cam.getScreenX()+3, (j*s)+cam.getScreenY()+3, s, s,raw[i + cam.getX()][j + cam.getY()]);
                if(player.getGx()==(i + cam.getX()) && player.getGy()==(j + cam.getY())) {
                    shapeDrawer.drawFillToCamera(g, (i*s)+cam.getScreenX()+3, (j*s)+cam.getScreenY()+3, s, s, Colors.red_raw.color);
                }
            }
        }
    }
}
