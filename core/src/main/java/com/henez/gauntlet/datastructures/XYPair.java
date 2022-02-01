package com.henez.gauntlet.datastructures;

public class XYPair {
    private XY xy1;
    private XY xy2;

    public XYPair(XY xy1, XY xy2) {
        this.xy1 = xy1;
        this.xy2 = xy2;
    }

    public int getX() {
        return xy1.getX();
    }

    public int getY() {
        return xy1.getY();
    }

    public int getXX() {
        return xy2.getX();
    }

    public int getYY() {
        return xy2.getY();
    }
}
