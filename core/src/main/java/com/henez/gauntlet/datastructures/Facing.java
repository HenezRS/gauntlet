package com.henez.gauntlet.datastructures;

import java.util.Arrays;

public enum Facing {
    right(0, 1, 0, 0),
    up(1, 0, -1, 270),
    left(2, -1, 0, 180),
    down(3, 0, 1, 90),
    upright(4, 1, -1, 315),
    upleft(5, -1, -1, 225),
    downleft(6, -1, 1, 135),
    downright(7, 1, 1, 45),
    ;

    public final int dir;
    public final int tx;
    public final int ty;
    public final float angle;
    public final boolean isDiagonal;

    Facing(int dir, int tx, int ty, float angle) {
        this.dir = dir;
        this.tx = tx;
        this.ty = ty;
        this.angle = angle;
        this.isDiagonal = dir>3;
    }

    public static Facing mapTo4(Facing facing) {
        switch (facing) {
        case upright:
        case downright:
            return Facing.right;
        case upleft:
        case downleft:
            return Facing.left;
        default:
            return facing;
        }
    }

    public Facing getNextRight() {
        switch (dir) {
        case 0:
            return Facing.down;
        case 1:
            return Facing.right;
        case 2:
            return Facing.up;
        default:
            return Facing.left;
        }
    }

    public Facing getNextLeft() {
        switch (dir) {
        case 0:
            return Facing.up;
        case 1:
            return Facing.left;
        case 2:
            return Facing.down;
        default:
            return Facing.right;
        }
    }

    public Facing getOpposite() {
        switch (dir) {
        case 0:
            return Facing.left;
        case 1:
            return Facing.down;
        case 2:
            return Facing.right;
        case 3:
            return Facing.up;
        case 4:
            return Facing.downleft;
        case 5:
            return Facing.downright;
        case 6:
            return Facing.upright;
        case 7:
            return Facing.upleft;
        }
        return null;
    }

    public static Facing byDir2(int x, int xx) {
        return x <= xx ? Facing.right : Facing.left;
    }

    public static Facing byDir(int x, int y, int xx, int yy) {
        return Arrays.stream(Facing.values()).filter(f -> f.tx==xx-x && f.ty==yy-y).findFirst().orElse(null);
    }

    public static Facing actorDefault() {
        return Facing.down;
    }

    public static Facing[] get4Ways() {
        return new Facing[]{Facing.right, Facing.up, Facing.left, Facing.down};
    }

    public static boolean same(Facing f, Facing ff) {
        return f.dir == ff.dir;
    }
}