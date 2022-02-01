package com.henez.gauntlet.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.Gson;
import com.henez.gauntlet.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshotter {
    private Screenshotter() {

    }

    public static void saveScreenshot() {
        Gson gson = new Gson();
        ScreenshotterSaver screenshotterSaver;
        FileHandle fileHandle = Gdx.files.local(Constants.PATH_LOCAL + "gameData.json");

        if (fileHandle.exists()) {
            screenshotterSaver = gson.fromJson(fileHandle.readString(), ScreenshotterSaver.class);

            //if the last taken screenshot date isn't todays date, start a new counter
            if (!screenshotterSaver.getDateToday().equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                screenshotterSaver.setDailyScreenshotCount(0);
                screenshotterSaver.setDateToday();
            }
        } else {
            screenshotterSaver = new ScreenshotterSaver();
        }

        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

        // this loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
        for (int i = 4; i < pixels.length; i += 4) {
            pixels[i - 1] = (byte) 255;
        }

        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        PixmapIO.writePNG(Gdx.files.external(Constants.PATH_EXTERNAL + "screenshots\\" + screenshotterSaver.getDateAsFolderName() + "\\" + Constants.GAME_PACKAGE_NAME + "_" + screenshotterSaver.getDateToday() + "_" + screenshotterSaver
                                  .getDailyScreenshotCount() + ".png"),
                          pixmap);
        pixmap.dispose();

        screenshotterSaver.incrementDailyScreenshotCount();
        fileHandle.writeString(gson.toJson(screenshotterSaver), false);
    }
}
