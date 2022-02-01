package com.henez.gauntlet.drawers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.Mini2DxFreeTypeFontGenerator;
import com.henez.gauntlet.enums.Colors;
import lombok.Getter;
import org.mini2Dx.core.font.BitmapFont;

@Getter
public class Font {
    private BitmapFont fontMain;
    private BitmapFont fontLowRes;
    private BitmapFont fontDamage;

    public Font() {
        loadFontMain();
        loadFontLowRes();
        loadFontDamage();
    }

    private void loadFontMain() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.spaceY = 6;
        parameter.flip = true;
        parameter.shadowColor = Colors.black_50.color;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.borderColor = Colors.black_50.color;
        parameter.borderWidth = 1;

        //The following settings allow the font to scale smoothly
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;

        Mini2DxFreeTypeFontGenerator generator = new Mini2DxFreeTypeFontGenerator(Gdx.files.internal("font/novem___.ttf"));
        Mini2DxFreeTypeFontGenerator.Mini2DxFreeTypeBitmapFontData data = generator.generateFontData(parameter);

        fontMain = new BitmapFont(data, data.getRegions(), true);
        fontMain.setUseIntegerPositions(true);
        generator.dispose();
    }

    private void loadFontLowRes() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.flip = true;
        parameter.shadowColor = Colors.black.color;
        parameter.shadowOffsetX = 1;
        //parameter.shadowOffsetY = 1;

        Mini2DxFreeTypeFontGenerator generator = new Mini2DxFreeTypeFontGenerator(Gdx.files.internal("font/HenezTheBestFontUpper2.ttf"));
        Mini2DxFreeTypeFontGenerator.Mini2DxFreeTypeBitmapFontData data = generator.generateFontData(parameter);

        fontLowRes = new BitmapFont(data, data.getRegions(), true);
        fontLowRes.setUseIntegerPositions(true);
        generator.dispose();
    }

    private void loadFontDamage() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 9;
        parameter.flip = true;
        parameter.borderColor = Colors.black.color;
        parameter.borderWidth = 1;

        //The following settings allow the font to scale smoothly
        /*parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;*/

        Mini2DxFreeTypeFontGenerator generator = new Mini2DxFreeTypeFontGenerator(Gdx.files.internal("font/ponderosa.ttf"));
        Mini2DxFreeTypeFontGenerator.Mini2DxFreeTypeBitmapFontData data = generator.generateFontData(parameter);

        fontDamage = new BitmapFont(data, data.getRegions(), true);
        fontDamage.setUseIntegerPositions(true);
        generator.dispose();
    }
}
