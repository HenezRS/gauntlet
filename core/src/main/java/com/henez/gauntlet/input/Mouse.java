package com.henez.gauntlet.input;

import com.henez.gauntlet.datastructures.XY;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mouse {
    //XY within the window
    private int x;
    private int y;

    //XY within the game world grid
    private int gx;
    private int gy;

    //XY within the game world
    private int wx;
    private int wy;
    private boolean clicked = false;
    private boolean clicked2 = false;
    private boolean held = false;
    private boolean held2 = false;
    private boolean released = false;
    private boolean released2 = false;

    public Mouse() {

    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosWorld(int wx, int wy) {
        this.wx = wx;
        this.wy = wy;
    }

    public void setPosGrid(int gx, int gy) {
        this.gx = gx;
        this.gy = gy;
    }

    public boolean isMouseWithin(int xx, int yy, int w, int h) {
        return x > xx && x < xx + w && y > yy && y < yy + h;
    }

    public boolean isMouseWithinInclusive(int xx, int yy, int w, int h) {
        return x > xx - 1 && x < xx + w + 1 && y > yy - 1 && y < yy + h + 1;
    }

    public boolean isMouseWithinGrid(int xx, int yy) {
        return gx > xx - 1 && gx < xx + 1 && gy > yy - 1 && gy < yy + 1;
    }

    public boolean isMouseWithinGrid(XY xy) {
        return isMouseWithinGrid(xy.getX(), xy.getY());
    }

    public boolean isMouseWithinGridLarge(int xx, int yy) {
        return isMouseWithinGrid(xx, yy) || isMouseWithinGrid(xx + 1, yy) || isMouseWithinGrid(xx, yy + 1) || isMouseWithinGrid(xx + 1, yy + 1);
    }
}
