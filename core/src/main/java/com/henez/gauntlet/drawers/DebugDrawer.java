package com.henez.gauntlet.drawers;

import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.input.In;
import com.henez.gauntlet.misc.Framerate;
import com.henez.gauntlet.world.World;
import org.mini2Dx.core.graphics.Graphics;

public final class DebugDrawer {

    private DebugDrawer() {
    }

    public static void draw(Graphics g, World world, Framerate framerate) {
        TextDrawer textDrawer = TextDrawer.getInstance();

        /*for(int i=0;i<8; ++i) {
            TextureDrawer.getInstance().drawToCamera(g, ImgUi.nodebar.asTex(), 64+(32*i), 130, i*45);
            TextureDrawer.getInstance().drawToCamera(g, ImgUi.nodebar_diagonal.asTex(), 64+(32*i), 164, i*45);
        }*/

        /*TextureDrawer.getInstance().drawToCamera(g, ImgUi.nodebar.asTex(), 64, 100, 0);
        TextureDrawer.getInstance().drawToCamera(g, ImgUi.nodebar_diagonal.asTex(), 64+32, 100, 0);
        TextureDrawer.getInstance().drawToCamera(g, ImgUi.nodebar.asTex(), 64, 64, framerate.getMillisSinceGameStart()/10);
        TextureDrawer.getInstance().drawToCamera(g, ImgUi.nodebar_diagonal.asTex(), 64+32, 64, framerate.getMillisSinceGameStart()/10);*/

        textDrawer.drawToCamera(g, framerate.getFrameRate() + "", 0, 0);
        textDrawer.drawToCamera(g, framerate.getSecondsSinceGameStart() + "", 40, 0);
        //textDrawer.drawToCamera(g, world.getPlayerData().getEncounterSteps().getStepsUntilEncounter() + "", 80, 0);
        textDrawer.drawToCamera(g, In.showHeld(), 0, Constants.cameraPixelH - 20);
    }
}
