package com.henez.gauntlet.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.henez.gauntlet.atlas.img.*;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.misc.TextureRegionE;
import org.mini2Dx.core.graphics.TextureRegion;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Atlas {
    private static TextureRegion[][] texTiles;
    private static TextureRegion[][] texActors;
    private static TextureRegion[][] texFighters;
    private static TextureRegion texFighterPortraits; //todo: fix
    private static TextureRegion[][] texSpells;
    private static TextureRegion[][] texSpellIcons;
    private static TextureRegion[][] texNodes;
    private static TextureRegion[][] texWeps;
    private static TextureRegion[][] texWepsFlip;

    private static Map<ImgBb, TextureRegion> texBb;
    private static Map<ImgUi, TextureRegion> texUi;
    private static Map<ImgEnemies, TextureRegion>  texEnemies;
    private static Map<ImgEnemiesBig, TextureRegion> texEnemiesBig;
    private static Map<ImgMapBack, TextureRegion> texMapBack;

    public static void load() {
        texTiles = loadTilesFromFile("img/tiles.png");
        texActors = loadTilesFromFile("img/actors.png");
        texFighters = loadTilesFromFile("img/fighters.png", 24, 24, 16);
        texFighterPortraits = loadFighterPortraits("img/fighters.png");
        texSpells = loadTilesFromFile("img/spells.png", 40, 60, 12);
        texSpellIcons = loadTilesFromFile("img/spellicons.png", 7, 7, 512/7);
        texNodes = loadTilesFromFile("img/nodes.png", 15, 15, 512/15);
        texWeps = loadTilesFromFile("img/weps.png", 32, 32, 16);
        texWepsFlip = flipX(loadTilesFromFile("img/weps.png", 32, 32, 16));
        texBb = loadBbs();
        texUi = loadUi();
        texEnemies = loadEnemies();
        texEnemiesBig = loadEnemiesBig();
        texMapBack = loadMapBack();
    }

    public static TextureRegion toTex(ImgTiles img) {
        return texTiles[img.getX()][img.getY()];
    }

    public static TextureRegionE toTex(ImgActors img) {
        return new TextureRegionE(texActors[img.getX()][img.getY()]);
    }

    public static TextureRegionE toTex(ImgFighters img) {
        return new TextureRegionE(texFighters[img.getX()][img.getY()]);
    }

    public static TextureRegionE toTexPortrait() {
        return new TextureRegionE(texFighterPortraits);
    }

    public static TextureRegionE toTex(ImgSpells img) {
        return new TextureRegionE(texSpells[img.getX()][img.getY()]);
    }

    public static TextureRegion toTex(ImgIcons img) {
        return texSpellIcons[img.getX()][img.getY()];
    }

    public static TextureRegion toTex(ImgNodes img) {
        return texNodes[img.getX()][img.getY()];
    }

    public static TextureRegion toTex(ImgWeps img) {
        return texWeps[img.getX()][img.getY()];
    }

    public static TextureRegion toTexFlip(ImgWeps img) {
        return texWepsFlip[img.getX()][img.getY()];
    }

    public static TextureRegion toTex(ImgEnemies img) {
        return texEnemies.get(img);
    }

    public static TextureRegion toTex(ImgBb img) {
        return texBb.get(img);
    }

    public static TextureRegionE toTex(ImgUi img) {
        return new TextureRegionE(texUi.get(img));
    }

    public static TextureRegion toTex(ImgMapBack img) {
        return texMapBack.get(img);
    }

    public static TextureRegion toTex(ImgEnemiesBig img) {
        return texEnemiesBig.get(img);
    }

    private static TextureRegion[][] flipX(TextureRegion[][] texes) {
        Arrays.stream(texes).forEach(texArray -> {
            Arrays.stream(texArray).forEach(tex -> {
                tex.flip(true,false);
            });
        });
        return texes;
    }

    private static TextureRegion[][] loadTilesFromFile(String path) {
        return loadTilesFromFile(path, Constants.tilePixelSize, Constants.tilePixelSize, Constants.tilesDocumentWidthInTiles);
    }

    private static TextureRegion[][] loadTilesFromFile(String path, int pixelW, int pixelH, int documentW) {
        Texture t = new Texture(Gdx.files.internal(path));
        int w = documentW;
        int h = documentW;
        TextureRegion[][] dest = new TextureRegion[w][h];
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                dest[i][j] = new TextureRegion(t, (i * pixelW), (j * pixelH), pixelW, pixelH);
            }
        }
        return dest;
    }

    private static TextureRegion loadFighterPortraits(String path) {
        Texture t = new Texture(Gdx.files.internal(path));
        return new TextureRegion(t, 4,1, 12, 12);
    }

    private static Map<ImgUi, TextureRegion> loadUi() {
        String path = "img/ui.png";
        Texture tex = new Texture(Gdx.files.internal(path));
        Map<ImgUi, TextureRegion> dest = new EnumMap<>(ImgUi.class);

        Arrays.stream(ImgUi.values()).forEach(img -> {
            dest.put(img, new TextureRegion(tex, img.getX(), img.getY(), img.getW(), img.getH()));
        });

        return dest;
    }

    private static Map<ImgBb, TextureRegion> loadBbs() {
        String path = "bb/{name}.png";
        Map<ImgBb, TextureRegion> dest = new EnumMap<>(ImgBb.class);

        Arrays.stream(ImgBb.values()).forEach(img -> {
            Texture tex = new Texture(Gdx.files.internal(path.replace("{name}", img.getPathName())));
            dest.put(img, new TextureRegion(tex, 0, 0, Constants.cameraPixelW, Constants.cameraPixelH));
        });

        return dest;
    }

    private static Map<ImgMapBack, TextureRegion> loadMapBack() {
        String path = "mapback/{name}.png";
        Map<ImgMapBack, TextureRegion> dest = new EnumMap<>(ImgMapBack.class);

        Arrays.stream(ImgMapBack.values()).forEach(img -> {
            Texture tex = new Texture(Gdx.files.internal(path.replace("{name}", img.getPathName())));
            dest.put(img, new TextureRegion(tex, 0, 0, Constants.cameraPixelW+64, Constants.cameraPixelH+64));
        });

        return dest;
    }

    private static Map<ImgEnemies, TextureRegion> loadEnemies() {
        String path = "img/enemies.png";
        Texture tex = new Texture(Gdx.files.internal(path));
        Map<ImgEnemies, TextureRegion> dest = new EnumMap<>(ImgEnemies.class);

        int ew = 40;
        int eh = 40;

        Arrays.stream(ImgEnemies.values()).forEach(img -> {
            dest.put(img, new TextureRegion(tex, ew*img.getX(), eh*img.getY(), ew*img.getW(), eh));
        });

        return dest;
    }

    private static Map<ImgEnemiesBig, TextureRegion> loadEnemiesBig() {
        String path = "img/enemiesBig.png";
        Texture tex = new Texture(Gdx.files.internal(path));
        Map<ImgEnemiesBig, TextureRegion> dest = new EnumMap<>(ImgEnemiesBig.class);

        int ew = 40;
        int eh = 60;

        Arrays.stream(ImgEnemiesBig.values()).forEach(img -> {
            dest.put(img, new TextureRegion(tex, ew*img.getX(), eh*img.getY(), ew*img.getW(), eh*img.getH()));
        });

        return dest;
    }
}
