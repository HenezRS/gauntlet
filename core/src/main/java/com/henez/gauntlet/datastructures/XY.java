package com.henez.gauntlet.datastructures;

import lombok.Getter;
import lombok.Setter;

@Getter
public class XY {
    private int x;
    private int y;
    private int wrapX;
    private int wrapY;

    private int lastAddX = 0;
    private int lastAddY = 0;

    @Setter private boolean enableWrap = false;

    public XY(XY xy) {
        this.x = xy.getX();
        this.y = xy.getY();
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void transform(int addX, int addY) {
        x+=addX;
        y+=addY;

        lastAddX = addX;
        lastAddY = addY;

        if(enableWrap) {
            wrap();
        }
    }

    public void transformRevert() {
        x-=lastAddX;
        y-=lastAddY;

        lastAddX = 0;
        lastAddY = 0;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWrap(int maxX, int maxY) {
        this.wrapX = maxX;
        this.wrapY = maxY;
    }

    public int getXAdd(int addX) {
        int xx = x+addX;
        if(xx>wrapX) {
            return xx-wrapX;
        }
        if(xx<0) {
            return xx+wrapX;
        }
        return xx;
    }

    public int getYAdd(int addY) {
        int yy = y+addY;
        if(yy>wrapY) {
            return yy-wrapY;
        }
        if(yy<0) {
            return yy+wrapY;
        }
        return yy;
    }

    private void wrap() {
        if(x>=wrapX) {
            x -= wrapX;
        }

        if(y>=wrapY) {
            y -= wrapY;
        }

        if(x<0) {
            x += wrapX;
        }

        if(y<0) {
            y += wrapY;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XY)) {
            return false;
        }

        XY xy = (XY) o;
        return xy.getX()==getX() && xy.getY()==getY();
    }

    @Override
    public int hashCode() {
        return getX()+(getY()*10000);
    }
}
