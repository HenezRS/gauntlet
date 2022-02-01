package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgNodes {
    cursor, back, big, biggest, empty, start, hp
    ;

    private final int x;
    private final int y;

    ImgNodes() {
        this.x = this.ordinal() % (512/15);
        this.y = this.ordinal() / (512/15);
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
