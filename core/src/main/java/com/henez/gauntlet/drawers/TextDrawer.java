package com.henez.gauntlet.drawers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.enums.Colors;
import com.henez.gauntlet.singletons.Camera;
import lombok.Getter;
import lombok.Setter;
import org.mini2Dx.core.graphics.Graphics;

@Getter
public class TextDrawer {
    private static TextDrawer texDrawer = null;

    public static Color COLOR_TEXT_DEFAULT = Colors.text_default.color;
    public static Color COLOR_TEXT_GREY = Colors.text_dim.color;
    public static Color COLOR_TEXT_HOVER = Colors.text_hover.color;
    public static Color COLOR_TEXT_SYSTEM = Colors.text_system.color;
    public static Color COLOR_TEXT_TITLE = Colors.text_hover.color;
    public static Color COLOR_TEXT_PLUS = Colors.text_plus.color;
    public static Color COLOR_TEXT_MINUS = Colors.text_minus.color;


    public static int TEXT_MARGIN_X = 8;
    public static int TEXT_MARGIN_WITH_CURSOR_X = 24;
    public static int TEXT_MARGIN_Y = 9;
    public static int TEXT_MARGIN_YY = 6;
    public static int TEXT_MARGIN_ICON = 9;
    public static int TEXT_MARGIN_USED_BY = 74;
    public static int TEXT_MARGIN_LEVEL = 48;
    public static int TEXT_MARGIN_COMMAND_2_COLUMNS = 90;
    public static int TEXT_MARGIN_WITH_ICON_X = TEXT_MARGIN_X + TEXT_MARGIN_ICON;

    public static int TEXT_H = 16;
    public static int TEXT_HH = 12;
    public static int TEXT_SENTENCE_H = 10;
    public static int TEXT_MAIN_PRECISE_H = TEXT_SENTENCE_H;

    public static String TEXT_MISSING = "- - -";

    private Camera camera;
    private Font font;
    @Setter private boolean isLowResMode;

    private TextDrawer() {
        camera = Camera.getInstance();
        font = new Font();
        isLowResMode = false;
    }

    public void drawToCamera(Graphics g, String str, float x, float y) {
        g.setColor(COLOR_TEXT_DEFAULT);
        if(isLowResMode) {
            drawTextLowResToCamera(g, str, x, y, 999, Align.left);
        } else {
            drawTextMainToCamera(g, str, x, y, 999, Align.left);
        }
    }

    public void drawToCamera(Graphics g, String str, float x, float y, Color color) {
        g.setColor(color);
        if(isLowResMode) {
            drawTextLowResToCamera(g, str, x, y, 999, Align.left);
        } else {
            drawTextMainToCamera(g, str, x, y, 999, Align.left);
        }
    }

    public void drawToCamera(Graphics g, String str, float x, float y, Color color, int w, int align) {
        g.setColor(color);
        if(isLowResMode) {
            drawTextLowResToCamera(g, str, x, y, w, align);
        } else {
            drawTextMainToCamera(g, str, x, y, w*3, align);
        }
    }

    private void drawTextMainToCamera(Graphics g, String str, float x, float y, int w, int align) {
        g.setFont(font.getFontMain());
        g.drawString(str, x * Constants.gameScale, y * Constants.gameScale, w, align);
    }

    private void drawTextLowResToCamera(Graphics g, String str, float x, float y, int w, int align) {
        g.setFont(font.getFontLowRes());
        g.drawString(str,x,y, w, align);
    }

    public void drawTextDamageToCamera(Graphics g, String str, float x, float y, Color color) {
        g.setColor(color);
        g.setFont(font.getFontDamage());
        g.drawString(str,x,y);
    }

    public int getTextW(String line) {
        GlyphLayout gl = new GlyphLayout();
        if(isLowResMode) {
            gl.setText(font.getFontLowRes(), line);
        } else {
            gl.setText(font.getFontMain(), line);
        }
        return (int)gl.width;
    }

    public int getTextLineH(String text, int maxW) {
        return 1+(getTextW(text) / maxW);
    }

    public static TextDrawer getInstance() {
        if(texDrawer ==null) {
            texDrawer = new TextDrawer();
        }
        return texDrawer;
    }
}
