package com.henez.gauntlet.world.sprite.playerfighter;

import com.henez.gauntlet.drawers.TextureDrawer;
import com.henez.gauntlet.atlas.imgset.ImgSetFighter;
import com.henez.gauntlet.world.sprite.SpriteAnimation;
import lombok.Getter;
import org.mini2Dx.core.graphics.Graphics;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PlayerFighterSprite {
    private Map<PlayerFighterAnimation, SpriteAnimation> animations;
    private PlayerFighterAnimation currentAnimation;
    private PlayerFighterAnimation playOnceAnimation;
    private PlayerFighterAnimation overrideAnimation;
    //@Setter private ImgWeps wep;

    public PlayerFighterSprite(ImgSetFighter imgSetFighter) {
        animations = new HashMap<>();
        animations.put(PlayerFighterAnimation.idle, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toIdle(imgSetFighter)));
        //animations.put(PlayerFighterAnimation.move, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toMove(imgSetFighter)));
        animations.put(PlayerFighterAnimation.attack, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toAttack(imgSetFighter)));
        animations.put(PlayerFighterAnimation.attack_fists, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toAttackFists(imgSetFighter)));
        animations.put(PlayerFighterAnimation.hit, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toHit(imgSetFighter)));
        animations.put(PlayerFighterAnimation.win, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toWin(imgSetFighter)));
        animations.put(PlayerFighterAnimation.dead, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toDead(imgSetFighter)));
        animations.put(PlayerFighterAnimation.low, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toLow(imgSetFighter)));
        animations.put(PlayerFighterAnimation.precast, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toPrecast(imgSetFighter)));
        animations.put(PlayerFighterAnimation.casting, new SpriteAnimation(PlayerFighterAnimationDynamicFactory.toCasting(imgSetFighter)));
        currentAnimation = PlayerFighterAnimation.idle;
        playOnceAnimation = null;
        overrideAnimation = null;
    }

    public void update() {
        if(currentAnimation != PlayerFighterAnimation.idle) {
            updateIdle();
        }

        animations.get(currentAnimation).update();
        if(animations.get(currentAnimation).isDonePlaying() && animations.get(currentAnimation).isPlayOnce()) {
            playOnceAnimation = null;
        }
    }

    public void updateIdle() {
        animations.get(PlayerFighterAnimation.idle).update();
    }

    public boolean hasPlayOnceAnim() {
        if(playOnceAnimation != null) {
            currentAnimation = playOnceAnimation;
            return true;
        }
        return false;
    }

    public boolean hasOverrideAnim() {
        if(overrideAnimation != null) {
            currentAnimation = overrideAnimation;
            return true;
        }
        return false;
    }

    public void resetOverrideAnim() {
        overrideAnimation = null;
    }

    public void playOnce(PlayerFighterAnimation animation, float speedMul) {
        playOnceAnimation = animation;
        animations.get(playOnceAnimation).resetAndPlayOnce(speedMul);
    }

    public void setOverrideAnim(PlayerFighterAnimation animation) {
        overrideAnimation = animation;
    }

    public void setCurrent(PlayerFighterAnimation animation) {
        currentAnimation = animation;
    }

    public boolean isKeyFrameDoneThisFrame(PlayerFighterAnimation animation) {
        return animations.get(animation).isKeyFrameDoneThisFrame();
    }

    public void draw(Graphics g, float x, float y) {
        /*if(currentAnimation == PlayerFighterAnimation.attack && wep != null) {
            if(animations.get(currentAnimation).getCurrentFrame()==0) {
                TextureDrawer.getInstance().drawToCamera(g, wep.asTexFlip(), x, y);
            } else {
                TextureDrawer.getInstance().drawToCamera(g, wep.asTex(), x-6, y+13);
            }
        }*/
        TextureDrawer.getInstance().drawToCamera(g, animations.get(currentAnimation).getCurrent(), x, y);
    }
}
