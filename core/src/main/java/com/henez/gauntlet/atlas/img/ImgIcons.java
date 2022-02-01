package com.henez.gauntlet.atlas.img;

import com.henez.gauntlet.atlas.Atlas;
import lombok.Getter;
import org.mini2Dx.core.graphics.TextureRegion;

@Getter
public enum ImgIcons {
    sword, bow, black, white, item, thrown, missing, plate, leather, cloth, ring, shield, hat, helm, bandana, rune, materia_slot,
    staff, dagger, swordtwo, axe, axetwo, wand, mshield, gloves, stat_wepatk, stat_wepspd, stat_wepcrit, stat_arm, stat_marm, stat_spellmul, stat_hp, stat_mp,
    stat_str, stat_def, stat_dex, stat_mag, stat_res, stat_mind, stat_spd, stat_cspd, arrow_plus, arrow_minus, maxed, rune2,
    ;

    private final int x;
    private final int y;

    ImgIcons() {
        this.x = this.ordinal() % (512/7);
        this.y = this.ordinal() / (512/7);
    }

    public TextureRegion asTex() {
        return Atlas.toTex(this);
    }
}
