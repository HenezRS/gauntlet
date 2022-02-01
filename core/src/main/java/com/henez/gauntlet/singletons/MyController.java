package com.henez.gauntlet.singletons;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyController {
    private static MyController myController = null;

    private Map<XboxButtons, State> buttons;

    private MyController() {
        buttons = new HashMap<>();
        Arrays.stream(XboxButtons.values()).forEach(button -> {
            buttons.put(button,new State());
        });
        /*set(up, Input.Keys.UP);
        set(down, Input.Keys.DOWN);
        set(left, Input.Keys.LEFT);
        set(right, Input.Keys.RIGHT);
        set(a, Input.Keys.Z, controller.getButton(0));
        set(b, Input.Keys.X);
        set(map, Input.Keys.SHIFT_LEFT);
        set(menu, Input.Keys.ESCAPE);
        set(start, Input.Keys.ENTER);
        set(select, Input.Keys.SPACE);
        set(r1, Input.Keys.S);
        set(l1, Input.Keys.A);


            append(a, controller.getButton(0));
            append(b, controller.getButton(1));

            append(map, controller.getButton(2));
            append(menu, controller.getButton(3));
            append(start, controller.getButton(7));
            append(select, controller.getButton(6));
            append(l1, controller.getButton(4));
            append(r1, controller.getButton(5));

            udAxis = controller.getAxis(0);
            lrAxis = controller.getAxis(1);

            append(down, udAxis>0.5 || povIsDown(controller.getPov(0)));
            append(up, udAxis<-0.5 || povIsUp(controller.getPov(0)));
            append(right, lrAxis>0.5 || povIsRight(controller.getPov(0)));
            append(left, lrAxis<-0.5 || povIsLeft(controller.getPov(0)));
        */
    }

    public void capture() {
        if(Controllers.getControllers().size>0) {
            com.badlogic.gdx.controllers.Controller controller = Controllers.getControllers().first();
            float udAxis = controller.getAxis(0);
            float lrAxis = controller.getAxis(1);
            Arrays.stream(XboxButtons.values()).forEach(button -> {
                if(button.code>=0) {
                    process(buttons.get(button), controller.getButton(button.code));
                } else {
                    switch (button) {
                    case down: process(buttons.get(button), udAxis>0.5 || povIsDown(controller.getPov(0))); break;
                    case up: process(buttons.get(button), udAxis<-0.5 || povIsUp(controller.getPov(0))); break;
                    case right: process(buttons.get(button), lrAxis>0.5 || povIsRight(controller.getPov(0))); break;
                    case left: process(buttons.get(button), lrAxis<-0.5 || povIsLeft(controller.getPov(0))); break;
                    }
                }
            });
        }
    }

    private void process(State state, boolean held) {
        if (held) {
            state.setPressed(false);
            if(!state.held) {
                state.setPressed(true);
            }
            state.setHeld(true);
            state.setReleased(false);
        } else {
            if(state.isHeld()) {
                state.setReleased(true);
            } else {
                state.setReleased(false);
            }
            state.setPressed(false);
            state.setHeld(false);
        }
    }

    public boolean isPressed(XboxButtons button) {
        return buttons.get(button).isPressed();
    }

    public boolean isHeld(XboxButtons button) {
        return buttons.get(button).isHeld();
    }

    public boolean isReleased(XboxButtons button) {
        return buttons.get(button).isReleased();
    }

    private boolean povIsDown(PovDirection povDir) {
        return povDir.toString().toLowerCase().contains("south");
    }

    private boolean povIsUp(PovDirection povDir) {
        return povDir.toString().toLowerCase().contains("north");
    }

    private boolean povIsLeft(PovDirection povDir) {
        return povDir.toString().toLowerCase().contains("west");
    }

    private boolean povIsRight(PovDirection povDir) {
        return povDir.toString().toLowerCase().contains("east");
    }

    public enum XboxButtons {
        a(0),b(1),menu(3),map(2),up,down,left,right,start(7),select(6),r1(5),l1(4);

        public int code;

        XboxButtons() {
            this.code = -1;
        }

        XboxButtons(int code) {
            this.code = code;
        }
    }

    private enum StateName {
        pressed, held, released;
    }

    @Getter
    @Setter
    private class State {
        private boolean pressed;
        private boolean held;
        private boolean released;
        public State() {
            pressed = false;
            held = false;
            released = false;
        }
    }

    public static MyController getInstance() {
        if(myController ==null) {
            myController = new MyController();
        }
        return myController;
    }
}
