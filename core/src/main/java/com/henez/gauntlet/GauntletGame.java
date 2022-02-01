package com.henez.gauntlet;

import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.drawers.DebugDrawer;
import com.henez.gauntlet.drawers.ScreenLoadingDrawer;
import com.henez.gauntlet.drawers.TextureDrawer;
import com.henez.gauntlet.atlas.img.ImgActors;
import com.henez.gauntlet.drawers.WorldDrawer;
import com.henez.gauntlet.input.In;
import com.henez.gauntlet.misc.Framerate;
import com.henez.gauntlet.misc.Screenshotter;
import com.henez.gauntlet.singletons.Camera;
import com.henez.gauntlet.singletons.ScreenLoading;
import com.henez.gauntlet.world.World;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class GauntletGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.henez.gauntlet";

    private boolean firstDrawComplete = false;
    private boolean firstDrawReady = false;
    private boolean drawDebug = true;

    private In in;
    private Framerate framerate;
    private Camera camera;
    private World world;

	@Override
    public void initialise() {
        Atlas.load();
        in = new In();
        framerate = new Framerate();
        world = new World();
        camera = Camera.getInstance();
    }
    
    @Override
    public void update(float delta) {
        in.capture();
        if(In.debug.isPressed()) {
            drawDebug = !drawDebug;
        }
        camera.update();
        framerate.update(delta);
        ScreenLoading.getInstance().update();

        world.update();
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) {
        if(firstDrawReady) {
            camera.setViewPortGame(g);
            WorldDrawer.draw(g, world);
            ScreenLoadingDrawer.draw(g);
            if(drawDebug) {
                DebugDrawer.draw(g, world, framerate);
            }
            post();
        }
        firstDrawReady = true;
    }

    private void post() {
        if (!firstDrawComplete && framerate.getSecondsSinceGameStart() > 4) {
            firstDrawComplete = true;
            Screenshotter.saveScreenshot();
        }
    }
}
