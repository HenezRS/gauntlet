package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgBb {
    forest("forest"),
    ;

    private final String pathName;

    ImgBb(String pathName) {
        this.pathName = pathName;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
