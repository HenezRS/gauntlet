package com.henez.gauntlet.world.sprite.playerfighter;

import com.henez.gauntlet.atlas.imgset.ImgSetFighter;
import com.henez.gauntlet.world.sprite.AnimationDynamic;

import static com.henez.gauntlet.constants.Constants.*;

public final class PlayerFighterAnimationDynamicFactory {
    private PlayerFighterAnimationDynamicFactory() {
    }

    public static AnimationDynamic toIdle(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC, 0, imgSetFighter.getIdle0(), imgSetFighter.getIdle1());
    }

    public static AnimationDynamic toMove(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC, 0, imgSetFighter.getIdle0(), imgSetFighter.getIdle1());
    }

    public static AnimationDynamic toAttack(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC12, 0, imgSetFighter.getAttack0(), imgSetFighter.getAttack1(), imgSetFighter.getAttack1());
    }

    public static AnimationDynamic toAttackFists(ImgSetFighter imgSetFighter) {
        //return new AnimationDynamic(SEC10, 1, imgSetFighter.getAttack0(), imgSetFighter.getIdle1(), imgSetFighter.getCastFinish(), imgSetFighter.getIdle1());
        return new AnimationDynamic(SEC12, 0, imgSetFighter.getAttack0(), imgSetFighter.getAttack1(), imgSetFighter.getAttack1());
    }

    public static AnimationDynamic toHit(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC6, 0, imgSetFighter.getHit0(), imgSetFighter.getHit1(), imgSetFighter.getHit0(), imgSetFighter.getHit1());
    }

    public static AnimationDynamic toWin(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC2, 0, imgSetFighter.getIdle0(), imgSetFighter.getVictory());
    }

    public static AnimationDynamic toDead(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC4, 0, imgSetFighter.getDead());
    }

    public static AnimationDynamic toLow(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC4, 0, imgSetFighter.getLow());
    }

    public static AnimationDynamic toPrecast(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC4, 0, imgSetFighter.getCastFinish());
    }

    public static AnimationDynamic toCasting(ImgSetFighter imgSetFighter) {
        return new AnimationDynamic(SEC2, 0, imgSetFighter.getCast0(), imgSetFighter.getCast1());
    }
}
