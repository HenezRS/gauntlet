package com.henez.gauntlet.world.map.minimap;

import com.henez.gauntlet.datastructures.Numbers;
import com.henez.gauntlet.world.mapobjects.actions.ControlledPlayer;
import lombok.Getter;

@Getter
public class MinimapCamera {
    private final int screenX = 8;
    private final int screenY = 8;
    private int x;
    private int y;
    private final int w = 54;
    private final int h = 36;

    public MinimapCamera() {

    }

    public void update(Minimap m, ControlledPlayer player) {
        x = Numbers.clamp(player.getGx() - w/2,m.getBX(),m.getBXX()-(w-1));
        y = Numbers.clamp(player.getGy() - h/2,m.getBY(),m.getBYY()-(h-1));
    }
}
