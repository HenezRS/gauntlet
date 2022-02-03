package com.henez.gauntlet.world.map.gamemap.instances;

import lombok.Getter;
import lombok.Setter;

@Getter
public class InstanceDoor {
    private boolean open;

    public InstanceDoor() {
        closeDoor();
    }

    public void openDoor() {
        open = true;
    }

    public void closeDoor() {
        open = false;
    }
}
