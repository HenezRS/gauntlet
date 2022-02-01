package com.henez.gauntlet.datastructures;

import lombok.Getter;

import static java.lang.Math.abs;

@Getter
public class FacingDist {
    private Facing facing;
    private int dist;

    public FacingDist(Facing facing, int dist) {
        this.facing = facing;
        this.dist = dist;
    }

    public static FacingDist fromPair(XYPair pair) {
        int distX = pair.getXX() - pair.getX();
        int distY = pair.getYY() - pair.getY();

        int dx = abs(distX);
        int dy = abs(distY);

        if(distX>=1 && distY>=1) {
            return new FacingDist(Facing.downright, dx);
        } else if(distX<=-1 && distY>=1) {
            return new FacingDist(Facing.downleft, dx);
        } else if(distX<=-1 && distY<=-1) {
            return new FacingDist(Facing.upleft, dx);
        } else if(distX>=1 && distY<=-1) {
            return new FacingDist(Facing.upright, dx);
        } else if(distX>=1) {
            return new FacingDist(Facing.right, dx);
        } else if(distX<=-1) {
            return new FacingDist(Facing.left, dx);
        } else if(distY>=1) {
            return new FacingDist(Facing.down, dy);
        } else if(distY<=-1) {
            return new FacingDist(Facing.up, dy);
        }

        return null;
    }
}
