package com.henez.gauntlet.enums;

import com.badlogic.gdx.graphics.Color;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.world.World;
import lombok.Getter;

@Getter
public enum ColorsStrobe {
    target_red(Colors.hp_bar_back),
    ;

    private Colors color;
    private GameList<Color> colorStrobe;

    ColorsStrobe(Colors color) {
        this.color = color;

        colorStrobe = new GameList<>();
        colorStrobe.add(color.withAlpha(0.5f));
        colorStrobe.add(color.withAlpha(0.6f));
        colorStrobe.add(color.withAlpha(0.7f));
        colorStrobe.add(color.withAlpha(0.8f));
        colorStrobe.add(color.withAlpha(0.9f));
        colorStrobe.add(color.withAlpha(1.0f));
        colorStrobe.add(color.withAlpha(1.0f));
        colorStrobe.add(color.withAlpha(0.9f));
        colorStrobe.add(color.withAlpha(0.8f));
        colorStrobe.add(color.withAlpha(0.7f));
        colorStrobe.add(color.withAlpha(0.6f));
        colorStrobe.add(color.withAlpha(0.5f));
    }

    public Color get() {
        return colorStrobe.get((World.globalTimer.getTick()/3) % colorStrobe.size());
    }
}
