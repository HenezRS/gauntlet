package com.henez.gauntlet.world.mapobjects;

import com.henez.gauntlet.atlas.imgset.ImgSetActors;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.input.In;
import com.henez.gauntlet.world.mapobjects.actions.Movement;
import com.henez.gauntlet.world.sprite.MapActorSprite;
import lombok.Getter;
import org.mini2Dx.core.graphics.Graphics;

@Getter
public class MapActor {
    protected float x;
    protected float y;
    protected int gx;
    protected int gy;
    protected MapActorSprite sprite;
    protected int depth;
    protected int id;
    protected int size = Constants.tilePixelSize;
    protected Movement movement;
    protected Facing facing;
    protected boolean isSprinting = false;

    public MapActor(int gx, int gy, ImgSetActors imgSetActors) {
        movement = new Movement();
        sprite = new MapActorSprite(imgSetActors);
        facing = Facing.down;

        resetPosition(gx, gy, depth);
        this.id = ActorFactory.getNextIdAndIncrement();
    }

    public void update() {
        if (movement.update()) {
            setFacing(movement.getFacing());
            sprite.update(isSprinting);
            move(movement.addMoveX(), movement.addMoveY());
            if (!movement.isMoving()) {
                completeMove();
            }
        }
    }

    public void draw(Graphics g) {
        sprite.draw(g,x,y);
    }

    public void resetPosition(int gx, int gy, int depth) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Constants.tilePixelSize;
        this.y = gy * Constants.tilePixelSize;
        this.depth = depth;
    }

    public void setPosition(int gx, int gy) {
        this.gx = gx;
        this.gy = gy;
        this.x = gx * Constants.tilePixelSize;
        this.y = gy * Constants.tilePixelSize;
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    protected void snapToGrid() {
        this.x = Math.round(x);
        this.y = Math.round(y);
        gx = (int) x / Constants.tilePixelSize;
        gy = (int) y / Constants.tilePixelSize;
    }

    public void beginMove(Facing facing, boolean isWorldMap) {
        isSprinting = In.b.isHeld();
        movement.begin(facing, isSprinting, isWorldMap);
    }

    public void completeMove() {
        snapToGrid();
    }

    public void setFacing(Facing facing) {
        this.facing = Facing.mapTo4(facing);
        sprite.setFacing(this.facing);
    }

}
