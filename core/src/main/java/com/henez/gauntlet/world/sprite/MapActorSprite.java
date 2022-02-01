package com.henez.gauntlet.world.sprite;

import com.henez.gauntlet.drawers.TextureDrawer;
import com.henez.gauntlet.atlas.imgset.ImgSetActors;
import com.henez.gauntlet.datastructures.Facing;
import lombok.Getter;
import org.mini2Dx.core.graphics.Graphics;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MapActorSprite {
    private Map<Facing,SpriteAnimation> animations;
    private Facing facing;
    private boolean downFacingOnly = false;

    public MapActorSprite(ImgSetActors imgSetActors) {
        animations = new HashMap<>();
        animations.put(Facing.right, new SpriteAnimation(AnimationDynamicFactory.toActorRight(imgSetActors)));
        animations.put(Facing.up, new SpriteAnimation(AnimationDynamicFactory.toActorUp(imgSetActors)));
        animations.put(Facing.left, new SpriteAnimation(AnimationDynamicFactory.toActorLeft(imgSetActors)));
        animations.put(Facing.down, new SpriteAnimation(AnimationDynamicFactory.toActorDown(imgSetActors)));
        facing = Facing.down;
    }

    public void update(boolean isSprinting) {
        animations.get(facing).update(isSprinting ? 2 : 1);
    }

    public void draw(Graphics g, float x, float y) {
        TextureDrawer.getInstance().drawToWorld(g, animations.get(facing).getCurrent(), x, y);
    }

    public void setFacing(Facing facing) {
        if(!downFacingOnly) {
            this.facing = facing;
        }
    }
}
