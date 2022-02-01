package com.henez.gauntlet.world.sprite;

import com.henez.gauntlet.atlas.imgset.ImgSetActors;

import static com.henez.gauntlet.constants.Constants.SEC10;

public final class AnimationDynamicFactory {
    private AnimationDynamicFactory() {
    }

    public static AnimationDynamic toActorRight(ImgSetActors imgSetActors) {
        return new AnimationDynamic(SEC10, 0, imgSetActors.getRight0(), imgSetActors.getRight1());
    }

    public static AnimationDynamic toActorUp(ImgSetActors imgSetActors) {
        return new AnimationDynamic(SEC10, 0, imgSetActors.getUp0(), imgSetActors.getUp1());
    }

    public static AnimationDynamic toActorLeft(ImgSetActors imgSetActors) {
        return new AnimationDynamic(SEC10, 0, imgSetActors.getLeft0(), imgSetActors.getLeft1());
    }

    public static AnimationDynamic toActorDown(ImgSetActors imgSetActors) {
        return new AnimationDynamic(SEC10, 0, imgSetActors.getDown0(), imgSetActors.getDown1());
    }

    public static AnimationDynamic toBomb(ImgSetActors imgSetActors) {
        return new AnimationDynamic(SEC10, 0, imgSetActors.getDown0(), imgSetActors.getDown1());
    }
}
