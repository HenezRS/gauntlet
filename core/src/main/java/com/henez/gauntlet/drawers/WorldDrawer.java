package com.henez.gauntlet.drawers;

import com.henez.gauntlet.singletons.Camera;
import com.henez.gauntlet.world.World;
import com.henez.gauntlet.world.map.tiles.TileKey;
import com.henez.gauntlet.world.map.tiles.TileName;
import com.henez.gauntlet.world.mapobjects.actions.ControlledPlayer;
import org.mini2Dx.core.graphics.Graphics;

public final class WorldDrawer {

    private WorldDrawer() {
    }

    public static void draw(Graphics g, World world) {
        TextureDrawer textureDrawer = TextureDrawer.getInstance();
        ShapeDrawer shapeDrawer = ShapeDrawer.getInstance();

        textureDrawer.drawToWorld(g, world.getCurrentMap().getMapBackTex(), Camera.getInstance().getTotalMapBackX(), Camera.getInstance().getTotalMapBackY());
        //textureDrawer.drawToWorld(g, world.getCurrentMap().getMapTex(), 0, 0);
        world.getCurrentMap().getTiles().forEach(tileStack -> {
            if (tileStack.getTile(TileKey.floor).isDrawable()) {
                textureDrawer.drawToWorld(g, tileStack.getTile(TileKey.floor).getTex(), tileStack.getX(), tileStack.getY());
            }
            if (tileStack.getTile(TileKey.deco).isDrawable()) {
                textureDrawer.drawToWorld(g, tileStack.getTile(TileKey.deco).getTex(), tileStack.getX(), tileStack.getY());
            }
        });

        ControlledPlayer p = world.getPlayer();
        p.draw(g, world.getCurrentMap().getTileStackAt(p.getGx(), p.getGy()).contains(TileName.world_forest));

        world.getObjects().forEach(obj -> {
            obj.draw(g);
        });

        MinimapDrawer.draw(g, p);
    }
}
