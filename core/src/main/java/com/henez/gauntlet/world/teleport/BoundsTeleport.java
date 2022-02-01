package com.henez.gauntlet.world.teleport;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.mapobjects.actions.ControlledPlayer;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class BoundsTeleport {
    private int minGx = -1;
    private int minGy = -1;
    private int maxGx = -1;
    private int maxGy = -1;
    private boolean hasBounds;

    private Map<Facing,TeleportDestination> destMap;

    public BoundsTeleport() {
        destMap = new HashMap<>();
        hasBounds = false;
    }

    public void setBounds(int minGx,int minGy,int maxGx,int maxGy) {
        this.minGx = minGx;
        this.maxGx = maxGx;
        this.minGy = minGy;
        this.maxGy = maxGy;
        hasBounds = true;
    }

    public void setBoundsX(int minGx, int maxGx) {
        this.minGx = minGx;
        this.maxGx = maxGx;
        hasBounds = true;
    }

    public void setBoundsY(int minGy, int maxGy) {
        this.minGy = minGy;
        this.maxGy = maxGy;
        hasBounds = true;
    }

    public void setBoundsX(GameMap map) {
        this.minGx = 0;
        this.maxGx = map.getMapData().getWidth()-1;
        hasBounds = true;
    }

    public void setBoundsY(GameMap map) {
        this.minGy = 0;
        this.maxGy = map.getMapData().getHeight()-1;
        hasBounds = true;
    }

    public void addDest(Facing facing, TeleportDestination dest) {
        destMap.put(facing, dest);
    }

    public void addDestFacingAll(TeleportDestination dest) {
        Arrays.stream(Facing.get4Ways()).forEach(f -> {
            destMap.put(f, dest);
        });
    }

    public TeleportDestination playerExceedsBounds(ControlledPlayer player) {
        if(destMap.containsKey(Facing.right) && maxGx!=-1 && player.getGx()>=maxGx) {
            return destMap.get(Facing.right);
        } else if(destMap.containsKey(Facing.left) && minGx!=-1 && player.getGx()<=minGx) {
            return destMap.get(Facing.left);
        } else if(destMap.containsKey(Facing.down) && maxGy!=-1 && player.getGy()>=maxGy) {
            return destMap.get(Facing.down);
        } else if(destMap.containsKey(Facing.up) && minGy!=-1 && player.getGy()<=minGy) {
            return destMap.get(Facing.up);
        }
        return null;
    }
}
