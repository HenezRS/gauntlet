package com.henez.gauntlet.enums;

import com.badlogic.gdx.graphics.Color;

public enum Colors {
    white(new Color(0xffffffff)),
    black(new Color(0x08090bff)),
    black_75(Colors.black.withAlpha(0.75f)),
    black_50(Colors.black.withAlpha(0.5f)),
    black_25(Colors.black.withAlpha(0.25f)),
    black_12(Colors.black.withAlpha(0.12f)),

    purple_raw(new Color(0xff00ffff)),
    red_raw(new Color(0xff0000ff)),
    red_raw_50(red_raw.withAlpha(0.5f)),

    window_back(black.color),
    window_back_darker(black.color),
    window_outer_border(new Color(0x858585ff)),
    window_inner_border(new Color(0x242418ff)),

    ui_bar_back(new Color(0x2b2b1cff)),
    ui_bar_front(new Color(0xf8e8d8ff)),
    ui_bar_front_full(new Color(0xf6da57ff)),
    ui_bar_front_casting(new Color(0x86a6cfff)),

    hp(new Color(0x86cf8bff)),
    hp_bar_back(new Color(0x704950ff)),
    mp(new Color(0x86a6cfff)),
    xp(new Color(0xac91baff)),

    text_default(new Color(0xc9c9afff)),
    text_dim(new Color(0x8f8a8aff)),
    text_dimmer(new Color(0x1f1f13ff)),
    text_hover(new Color(0xf6da57ff)),
    text_system(new Color(0xcc9944ff)),
    text_plus(hp.color),
    text_minus(new Color(0xcf868bff)),

    node_back(text_dimmer.color),
    node_grey(text_dim.color),
    node_connected(text_default.color),
    node_allocated(text_hover.color),

    player_blue(new Color(0x5d6e75ff)),
    enemy_red(new Color(0x94695dff)),

    grass(new Color(0x00b800ff)),
    ;

    public Color color;

    Colors(Color color) {
        this.color = color;
    }

    Colors(Colors colors) {
        this.color = colors.color;
    }

    public Color withAlpha(float a) {
        return new Color(color.r, color.g, color.b, a);
    }

    public Color create() {
        return new Color(color.r, color.g, color.b, color.a);
    }

    public Color mul(float mul, float a) {
        return new Color(color.r * mul, color.g * mul, color.b * mul, a);
    }

    public float[] getAsFloats3() {
        return new float[] { color.r, color.g, color.b };
    }
}
