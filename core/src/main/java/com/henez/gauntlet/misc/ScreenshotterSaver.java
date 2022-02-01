package com.henez.gauntlet.misc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotterSaver {
    public String dateToday;
    public int dailyScreenshotCount;

    public ScreenshotterSaver() {
        dateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dailyScreenshotCount = 0;
    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday() {
        dateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public String getDateAsFolderName() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

    public int getDailyScreenshotCount() {
        return dailyScreenshotCount;
    }

    public void setDailyScreenshotCount(int dailyScreenshotCount) {
        this.dailyScreenshotCount = dailyScreenshotCount;
    }

    public void incrementDailyScreenshotCount() {
        dailyScreenshotCount++;
    }
}
