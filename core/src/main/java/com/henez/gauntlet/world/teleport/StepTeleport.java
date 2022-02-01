package com.henez.gauntlet.world.teleport;

import lombok.Getter;

@Getter
public class StepTeleport {
    private int gx;
    private int gy;

    private TeleportDestination dest;

    public StepTeleport(int gx, int gy) {
        this.gx = gx;
        this.gy = gy;
    }

    public StepTeleport withDestination(TeleportDestination dest) {
        this.dest = dest;
        return this;
    }
}
