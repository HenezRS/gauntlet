package com.henez.gauntlet.misc;

import lombok.Getter;
import lombok.Setter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
@Setter
public class TextureRegionE {
    private TextureRegion tex;
    private float alpha = 1.0f;
    private int addX = 0;
    private int addY = 0;

    public TextureRegionE(TextureRegion tex) {
        this.tex = tex;
    }

    public TextureRegionE withAdd(int addX, int addY) {
        this.addX = addX;
        this.addY = addY;
        return this;
    }

    public TextureRegionE withAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public TextureRegionE centered() {
        return withAdd(-8,-8);
    }
}
