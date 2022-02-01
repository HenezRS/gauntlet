package com.henez.gauntlet.utils;

import com.henez.gauntlet.datastructures.XY;

public final class XYUtils {
    private XYUtils() {}

    public static boolean equal(XY xy, XY xy2) {
        return xy.getX() == xy2.getX() && xy.getY() == xy2.getY();
    }

    public static boolean equal(int x, int y, XY xy2) {
        return x == xy2.getX() && y == xy2.getY();
    }
}
