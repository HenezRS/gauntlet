package com.henez.gauntlet.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.henez.gauntlet.constants.Constants;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.misc.Timer;
import com.henez.gauntlet.singletons.MyController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.henez.gauntlet.constants.Constants.SEC2;

public class In {
    //todo: fix key release
    public static Key up = new Key("UP");
    public static Key down = new Key("DOWN");
    public static Key left = new Key("LEFT");
    public static Key right = new Key("RIGHT");
    public static Key a = new Key("A");
    public static Key b = new Key("B");
    public static Key map = new Key("MAP");
    public static Key menu = new Key("MENU");
    public static Key start = new Key("START");
    public static Key select = new Key("SELECT");
    public static Key r1 = new Key("R1");
    public static Key l1 = new Key("L1");
    public static Key debug = new Key("DEBUGKEY");

    public static Mouse mouse = new Mouse();

    public static GameList<Key> keys;
    public static Map<Key, Timer> keyThrottle;

    private static MyController controller = MyController.getInstance();
    private static float udAxis = 0.0f;
    private static float lrAxis = 0.0f;

    public In() {
        keys = Stream.of(right, up, left, down, a, b, map, menu, start, select, r1, l1, debug).collect(Collectors.toCollection(GameList::new));
        keyThrottle = new HashMap<>();
        Stream.of(right, up, left, down, a, r1, l1).forEach(key -> {
            keyThrottle.put(key, new Timer());
        });
    }

    public static String showHeld() {
        StringBuilder held = new StringBuilder("[ ");
        keys.stream().filter(Key::isHeld).forEach(key -> held.append(key.getName()).append(" "));
        held.append("]");
        /*held.append("[");
        held.append("U/D: ").append(Numbers.roundDecimal2(udAxis));
        held.append("L/R: ").append(Numbers.roundDecimal2(lrAxis));
        held.append("]");*/

        return held.toString();
    }

    public void capture() {
        controller.capture();

        set(up, Input.Keys.UP, Input.Keys.W, MyController.XboxButtons.up);
        set(down, Input.Keys.DOWN, Input.Keys.S, MyController.XboxButtons.down);
        set(left, Input.Keys.LEFT, Input.Keys.A, MyController.XboxButtons.left);
        set(right, Input.Keys.RIGHT, Input.Keys.D, MyController.XboxButtons.right);
        set(a, Input.Keys.Z, Input.Keys.E, MyController.XboxButtons.a);
        set(b, Input.Keys.X, Input.Keys.Q, MyController.XboxButtons.b);
        set(map, Input.Keys.SHIFT_LEFT, Input.Keys.TAB, MyController.XboxButtons.map);
        set(menu, Input.Keys.ESCAPE, Input.Keys.CONTROL_LEFT, MyController.XboxButtons.menu);
        set(start, Input.Keys.ENTER, Input.Keys.ENTER, MyController.XboxButtons.start);
        set(select, Input.Keys.SPACE, Input.Keys.SPACE, MyController.XboxButtons.select);
        set(r1, Input.Keys.NUM_2, Input.Keys.NUM_2, MyController.XboxButtons.r1);
        set(l1, Input.Keys.NUM_1, Input.Keys.NUM_1, MyController.XboxButtons.l1);
        setDebug(debug, Input.Keys.APOSTROPHE);

        recordHeldDirections();

        mouse.setClicked(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT));
        mouse.setHeld(Gdx.input.isButtonPressed(Input.Buttons.LEFT));
        boolean wasClicked = mouse.isClicked();
        mouse.setReleased(!(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && wasClicked));

        mouse.setClicked2(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
        mouse.setHeld2(Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
        wasClicked = mouse.isClicked2();
        mouse.setReleased2(!(Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && wasClicked));

        mouse.setPos(Gdx.input.getX() / Constants.gameScale, Gdx.input.getY() / Constants.gameScale);
        //mouse.setPosWorld((Gdx.input.getX() / Constants.gameScale) + Constants.renderer.getX(), (Gdx.input.getY() / Constants.gameScale) + Constants.renderer.getY());
        mouse.setPosGrid(mouse.getWx() / Constants.tilePixelSize, mouse.getWy() / Constants.tilePixelSize);
    }

    private void recordHeldDirections() {
        Stream.of(right, up, left, down, a, r1, l1).forEach(dir -> {
            if(dir.isHeld()) {
                keyThrottle.get(dir).update();
            } else {
                keyThrottle.get(dir).reset();
            }
        });
    }

    public static boolean dirUp(boolean onlyAllowPress) {
        return dir(up,onlyAllowPress);
    }
    public static boolean dirDown(boolean onlyAllowPress) {
        return dir(down,onlyAllowPress);
    }
    public static boolean dirLeft(boolean onlyAllowPress) {
        return dir(left,onlyAllowPress);
    }
    public static boolean dirRight(boolean onlyAllowPress) {
        return dir(right,onlyAllowPress);
    }
    public static boolean throttleA(boolean onlyAllowPress) {
        return dir(a,onlyAllowPress);
    }
    public static boolean throttleR1(boolean onlyAllowPress) {
        return dir(r1,onlyAllowPress);
    }
    public static boolean throttleL1(boolean onlyAllowPress) {
        return dir(l1,onlyAllowPress);
    }

    private static boolean dir(Key key, boolean onlyAllowPress) {
        if(onlyAllowPress) {
            return key.isPressed();
        }
        int tick = keyThrottle.get(key).getTick();
        return key.isPressed() || (key.isHeld() && tick>0 && tick%12==0) || (key.isHeld() && tick>SEC2 && tick%4==0);
    }

    private void set(Key key, int code, int code2, MyController.XboxButtons b) {
       // key.set(Gdx.input.isKeyJustPressed(code) || controller.isPressed(b), Gdx.input.isKeyPressed(code)|| controller.isHeld(b), (!Gdx.input.isKeyPressed(code) && wasPressed) || controller.isReleased(b));
        key.set(Gdx.input.isKeyJustPressed(code) || Gdx.input.isKeyJustPressed(code2) || controller.isPressed(b), Gdx.input.isKeyPressed(code) || Gdx.input.isKeyPressed(code2) || controller.isHeld(b), controller.isReleased(b));
    }

    private void setDebug(Key key, int code) {
        // key.set(Gdx.input.isKeyJustPressed(code) || controller.isPressed(b), Gdx.input.isKeyPressed(code)|| controller.isHeld(b), (!Gdx.input.isKeyPressed(code) && wasPressed) || controller.isReleased(b));
        key.set(Gdx.input.isKeyJustPressed(code), Gdx.input.isKeyPressed(code), false);
    }
}
