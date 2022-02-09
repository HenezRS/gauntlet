package com.henez.gauntlet.constants;

public class Constants {
    public static String GAME_PACKAGE_NAME = "gauntlet";
    public static String PATH_EXTERNAL = String.format("\\Dropbox\\Java Projects\\Mini2Dx projects\\%s\\docs\\", GAME_PACKAGE_NAME);
    public static String PATH_LOCAL = "bin/";
    public static String PATH_MAPS = "maps/";

    public static int gameScale = 3;
    public static int cameraPixelW = 480;
    public static int cameraPixelH = 270;
    public static int cameraPixelWW = cameraPixelW / 2;
    public static int cameraPixelHH = cameraPixelH / 2;
    public static int windowPixelW = cameraPixelW * gameScale; //1440 - tiles: 30
    public static int windowPixelH = cameraPixelH * gameScale; //810 - tiles: 16.8
    public static int tilePixelSize = 16;
    public static int tilesDocumentWidthInTiles = 32;
    public static int instanceRoomTileW = 30;
    public static int instanceRoomTileH = 16;

    public static int SEC = 60;
    public static int SEC2 = SEC / 2;
    public static int SEC3 = SEC / 3;
    public static int SEC4 = SEC / 4;
    public static int SEC5 = SEC / 5;
    public static int SEC6 = SEC / 6;
    public static int SEC10 = SEC / 10;
    public static int SEC12 = SEC / 12;
    public static int SEC15 = SEC / 15;
    public static int SEC20 = SEC / 20;
    public static int SEC30 = SEC / 30;
    public static int SEC60 = SEC / 60;

    public static String LAYER_OBJ = "obj";
    public static String LAYER_DECO = "deco";
    public static String LAYER_FLOOR = "floor";
    public static String LAYER_BACK = "back";
}
