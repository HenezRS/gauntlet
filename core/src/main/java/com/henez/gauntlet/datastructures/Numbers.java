package com.henez.gauntlet.datastructures;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Numbers {

    public static Random random = new Random(255);

    private Numbers() {

    }

    /**
     * Returns {@code int} value between min and max inclusive of both bounds.
     */
    public static int nextIntBetween(int min, int max) {
        //System.out.println(min + " - " + max + " = " + (min + random.nextInt((max + 1) - min)));
        return min + random.nextInt((max + 1) - min);
    }

    public static float nextFloatBetween(float min, float max) {
        return (min + ((random.nextFloat() + 0.0001f) * (max - min)));
    }

    public static boolean rollPercent(float percent) {
        return nextFloatBetween(0, 1) <= percent;
    }

    public static boolean flip() {
        return random.nextInt(2) == 1;
    }

    public static boolean flip(int power) {
        return random.nextInt((int) Math.pow(2, power)) == 1;
    }

    public static float floor(float in) {
        int v = (int) in;
        return (float) v;
    }

    public static int clamp(int value, int min) {
        return clamp(value, min, Integer.MAX_VALUE);
    }

    public static int clamp(int value, int min, int max) {
        int v = Math.min(value, max);
        return Math.max(v, min);
    }

    public static float clamp(float value, float min, float max) {
        float v = Math.min(value, max);
        return Math.max(v, min);
    }

    public static int round(float value) {
        return Math.round(value);
    }

    public static float roundDecimal2(float value) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(2, RoundingMode.CEILING);
        return bd.floatValue();
    }

    public static float percent(float left, float right) {
        if (left < 0) {
            left = 0;
        }
        BigDecimal bd = new BigDecimal(Float.toString(left / right));
        bd = bd.setScale(2, RoundingMode.CEILING);
        return bd.floatValue();
    }
}
