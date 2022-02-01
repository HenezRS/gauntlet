package com.henez.gauntlet.world.sprite.playerfighter;

public enum PlayerFighterAnimation {
    idle, move, attack, attack_fists, hit, win, dead, low, precast, casting;

    /*public static void determineAnimation(PlayerFighter fighter, boolean victory) {
        PlayerFighterSprite sprite = fighter.getSprite();
        TransformManager tm = fighter.getTransformManager();

        if(sprite.hasPlayOnceAnim() || sprite.hasOverrideAnim()) {

        } else if(fighter.getStatSheet().isDead()) {
            sprite.setCurrent(dead);
        } else if(victory) {
            sprite.setCurrent(win);
        } *//*else if(tm.existsAndNotComplete(TransformName.playerForward) || tm.existsAndNotComplete(TransformName.playerBackward)) {
            sprite.setCurrent(move);
        }*//* else if(fighter.getSkillCast().isCasting()) {
            sprite.setCurrent(casting);
        } else if(fighter.getStatSheet().isLow()) {
            sprite.setCurrent(low);
        } else {
            sprite.setCurrent(idle);
        }
    }*/
}