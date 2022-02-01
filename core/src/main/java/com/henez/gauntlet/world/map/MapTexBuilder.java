package com.henez.gauntlet.world.map;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.TimeUtils;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.world.map.tiles.Tile;
import com.henez.gauntlet.world.map.tiles.TileKey;
import com.henez.gauntlet.world.map.tiles.TileStack;
import org.mini2Dx.core.graphics.TextureRegion;

public class MapTexBuilder {

    public MapTexBuilder() {
    }

    public TextureRegion draw(int width, int height, GameList<TileStack> tiles) {
        int w = width * Constants.tilePixelSize;
        int h = height * Constants.tilePixelSize;

        Pixmap pixmapMap = new Pixmap(w, h, Pixmap.Format.RGBA8888);

        Texture map = new Texture(w, h, Pixmap.Format.RGBA8888);
        Long millis = TimeUtils.millis();
        System.out.println("START: ");
        tiles.forEach(tileStack -> {
            //drawTile(pixmapMap, tileStack.getTile(TileKey.back), tileStack.getX(), tileStack.getY());
            drawTile(pixmapMap, tileStack.getTile(TileKey.floor), tileStack.getX(), tileStack.getY());
            drawTile(pixmapMap, tileStack.getTile(TileKey.deco), tileStack.getX(), tileStack.getY());

            //todo: obj should not be drawn
            drawTile(pixmapMap, tileStack.getTile(TileKey.obj), tileStack.getX(), tileStack.getY());
        });
        System.out.println("END: "+((TimeUtils.millis()-millis))+"ms");
        map.draw(pixmapMap, 0, 0);
        pixmapMap.dispose();
        TextureRegion tex = new TextureRegion(map);
        return tex;
    }

    private void drawTile(Pixmap pixmapMap, Tile tile, int x, int y) {
        if (tile.isDrawable()) {
            Pixmap pm = toPixmap(tile.getTex());
            /*if (tile.getRotation() != 0) {
                pm = rotatePixmap(pm);
            }*/
            pixmapMap.drawPixmap(pm, x, y);
            pm.dispose();
        }
    }

    private Pixmap toPixmap(TextureRegion textureRegion) {
        // texture flipping fix
        int yy = 0;//-textureRegion.getRegionHeight();

        TextureData textureData = textureRegion.getTexture().getTextureData();
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        Pixmap pixmap = new Pixmap(
                textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(),
                textureData.getFormat()
        );
        pixmap.setBlending(Pixmap.Blending.None);
        /*pixmap.setColor(0, 0, 0, 0.0f);
        pixmap.fill();*/
        Pixmap pmFrom = textureData.consumePixmap();

        for (int i = 0; i < pixmap.getWidth(); ++i) {
            for (int j = 0; j < pixmap.getHeight(); ++j) {
                int pix = pmFrom.getPixel(textureRegion.getRegionX() + i, textureRegion.getRegionY() + yy + j);
                pixmap.drawPixel(i, j, pix);
            }
        }

        pmFrom.dispose();
        textureData.disposePixmap();

        return pixmap;
    }
}
