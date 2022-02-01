package com.henez.gauntlet.world.mapobjects.actions;

import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.Facing;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement {
    private Facing facing;
    private Facing lastMovementFacing = Facing.actorDefault();
    float speed = 2.0f;
    float speedSprinting = 3.2f;
    float progress;
    float goal;
    boolean isMoving;
    boolean isSprinting;
    boolean isWorldMap;
    boolean justFinishedMovement;

    public Movement() {
        isMoving = false;
        isSprinting = false;
        justFinishedMovement = false;
    }

    public boolean update() {
        justFinishedMovement = false;

        if (isMoving) {
            progress += getSpeed();
            if (progress >= goal) {
                progress = goal;
                finish();
                return true;
            }
        }

        return isMoving;
    }

    public void begin(Facing facing, boolean isSprinting, boolean isWorldMap) {
        isMoving = true;
        this.isSprinting = isSprinting;
        this.isWorldMap = isWorldMap;
        this.facing = facing;
        this.lastMovementFacing = facing;
        progress = 0;
        goal = Constants.tilePixelSize;
    }

    public float addMoveX() {
        return facing.tx * getSpeed();
    }

    public float addMoveY() {
        return facing.ty * getSpeed();
    }

    private void finish() {
        isMoving = false;
        justFinishedMovement = true;
    }

    private float getSpeed() {
        return isWorldMap ? 1.0f
                : isSprinting ? speedSprinting : speed;
    }
}
