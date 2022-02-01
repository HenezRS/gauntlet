package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import com.henez.gauntlet.constants.Constants;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgWeps {
    sword, axe, knife
    ;

    private final int x;
    private final int y;

    ImgWeps() {
        this.x = this.ordinal() % Constants.tilesDocumentWidthInTiles;
        this.y = this.ordinal() / Constants.tilesDocumentWidthInTiles;
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }

    public TextureRegion asTexFlip() {
        return Atlas.toTexFlip(this);
    }
}
