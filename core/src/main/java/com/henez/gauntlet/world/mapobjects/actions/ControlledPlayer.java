package com.henez.gauntlet.world.mapobjects.actions;

import com.henez.gauntlet.atlas.imgset.ImgSetActors;
import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.input.In;
import com.henez.gauntlet.world.map.MapController;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.map.gamemap.MapName;
import com.henez.gauntlet.world.map.tiles.TileStack;
import com.henez.gauntlet.world.mapobjects.MapActor;
import com.henez.gauntlet.world.sprite.MapActorSprite;
import org.mini2Dx.core.graphics.Graphics;

public class ControlledPlayer extends MapActor {
    private MapActorSprite spriteNormal;
    private MapActorSprite spriteWorld;

    private boolean canBeginNewMove;

    public ControlledPlayer() {
        super(0, 0, ImgSetActors.hero);
        spriteNormal = sprite;
        spriteWorld = new MapActorSprite(ImgSetActors.hero_mini);

    }

    @Override
    public void update() {
        super.update();
        canBeginNewMove = !movement.isMoving();
    }

    public void draw(Graphics g, boolean isUnderForest) {
        if(isUnderForest) {
            g.setTint(Colors.white.withAlpha(0.5f));
        }
        sprite.draw(g,x,y);
        g.removeTint();
    }

    public void setSprite(boolean worldSprite) {
        sprite = worldSprite ? this.spriteWorld : this.spriteNormal;
    }

    public void checkMovementIsPressed() {
        GameMap map = MapController.getInstance().getCurrentMap();

        if (canBeginNewMove) {
            for(Facing facing : pollInputMovement()) {
                if(tilesAreFreeForMovement(map, facing) && currentTileAllowsMovement(map, facing)) {
                    beginMove(facing, map.getMapName() == MapName.world);
                    break;
                } else {
                    setFacing(facing);
                }
            }
        }
    }

    private boolean tilesAreFreeForMovement(GameMap map, Facing facing) {
        GameList<TileStack> tileStacks = new GameList<>();
        tileStacks.add(map.getTileStackAt(gx+facing.tx, gy+facing.ty));

        switch(facing) {
        case upright:
            tileStacks.add(map.getTileStackAt(gx+facing.tx-1, gy+facing.ty));
            tileStacks.add(map.getTileStackAt(gx+facing.tx, gy+facing.ty+1));
            break;
        case upleft:
            tileStacks.add(map.getTileStackAt(gx+facing.tx+1, gy+facing.ty));
            tileStacks.add(map.getTileStackAt(gx+facing.tx, gy+facing.ty+1));
            break;
        case downleft:
            tileStacks.add(map.getTileStackAt(gx+facing.tx+1, gy+facing.ty));
            tileStacks.add(map.getTileStackAt(gx+facing.tx, gy+facing.ty-1));
            break;
        case downright:
            tileStacks.add(map.getTileStackAt(gx+facing.tx-1, gy+facing.ty));
            tileStacks.add(map.getTileStackAt(gx+facing.tx, gy+facing.ty-1));
            break;
        }

        return tileStacks.stream().allMatch(TileStack::isWalkable);
    }

    private boolean currentTileAllowsMovement(GameMap map, Facing facing) {
        GameList<Facing> forbiddenFacing = new GameList<>();
        GameList<Facing> forbiddenFacingReverse = new GameList<>();

        //tile ON
        map.getTileStackAt(gx, gy).getTilesMap().forEach((k,v) -> {
            forbiddenFacing.addAll(v.getTileName().getForbiddenMovement());
        });


        //tile TO
        map.getTileStackAt(gx+facing.tx, gy+facing.ty).getTilesMap().forEach((k,v) -> {
            forbiddenFacingReverse.addAll(v.getTileName().getForbiddenMovement());
        });

        forbiddenFacingReverse.forEach(f -> {
            forbiddenFacing.add(f.getOpposite());
        });

        return forbiddenFacing.stream().noneMatch(f -> f.dir == facing.dir);
    }

    private GameList<Facing> pollInputMovement() {
        GameList<Facing> facingList = new GameList<>();
        if (In.right.isHeld() && In.up.isHeld() && !In.left.isHeld() && !In.down.isHeld()) {
            facingList.add(Facing.upright);
        } else if (In.left.isHeld() && In.up.isHeld() && !In.right.isHeld() && !In.down.isHeld()) {
            facingList.add(Facing.upleft);
        } else if (In.left.isHeld() && In.down.isHeld() && !In.right.isHeld() && !In.up.isHeld()) {
            facingList.add(Facing.downleft);
        } else if (In.right.isHeld() && In.down.isHeld() && !In.left.isHeld() && !In.up.isHeld()) {
            facingList.add(Facing.downright);
        }

        if (In.right.isHeld() && !In.left.isHeld()) {
            facingList.add(Facing.right);
        }

        if (In.left.isHeld() && !In.right.isHeld()) {
            facingList.add(Facing.left);
        }

        if (In.up.isHeld() && !In.down.isHeld()) {
            facingList.add(Facing.up);
        }

        if (In.down.isHeld() && !In.up.isHeld()) {
            facingList.add(Facing.down);
        }

        Facing lowestPriority = movement.getLastMovementFacing();
        if(!lowestPriority.isDiagonal) {
            for (int i = 0; i < facingList.size(); ++i) {
                if (Facing.same(lowestPriority, facingList.get(i))) {
                    facingList.remove(i);
                    facingList.add(lowestPriority);
                    break;
                }
            }
        }



        return facingList;
    }
}
