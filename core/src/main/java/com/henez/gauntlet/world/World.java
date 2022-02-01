package com.henez.gauntlet.world;

import com.henez.gauntlet.datastructures.Facing;
import com.henez.gauntlet.datastructures.GameList;
import com.henez.gauntlet.misc.Timer;
import com.henez.gauntlet.singletons.Camera;
import com.henez.gauntlet.singletons.ScreenLoading;
import com.henez.gauntlet.world.map.gamemap.GameMap;
import com.henez.gauntlet.world.map.gamemap.MapName;
import com.henez.gauntlet.world.map.minimap.Minimap;
import com.henez.gauntlet.world.mapobjects.MapActor;
import com.henez.gauntlet.world.mapobjects.actions.ControlledPlayer;
import com.henez.gauntlet.world.teleport.StepTeleport;
import com.henez.gauntlet.world.teleport.TeleportDestination;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public class World {
    public static int MAP_GW;
    public static int MAP_GH;
    public static Timer globalTimer = new Timer();

    private Camera camera = Camera.getInstance();
    private ScreenLoading screenLoading = ScreenLoading.getInstance();
    private GameMap currentMap;
    private GameList<MapActor> objects;
    private GameList<StepTeleport> stepTeleports;
    private ControlledPlayer player;

    private boolean openMenuWhenMovingStops = false;

    public World() {
        beginNewMap(MapName.grass_dark.create(), 30,32, Facing.down);
    }

    public void update() {
        updateWorld();
        Minimap.getInstance().update(player);

        globalTimer.update();
    }

    private void updateWorld() {
        if(playerInputIsAllowed()) {
            //checkMenuOpen();
            //if (!playerMenuControl.isOpen()) {
                player.checkMovementIsPressed(currentMap);
            //}
        }
        player.update();
        objects.forEach(MapActor::update);
        camera.setPosCenteredOnMapObject(player,currentMap.getPixelW(), currentMap.getPixelH());

        if(player.getMovement().isJustFinishedMovement()) {
            camera.snapMapBack();
            if(isStandingOnTeleport()) {

            } if(hasTriggeredBoundsTeleport()) {

            } else {
                /*EncounterSteps steps = playerData.getEncounterSteps();
                steps.takeStep();
                if(player.getMovement().getLastMovementFacing().isDiagonal) {
                    steps.takeStep();
                }
                if(steps.isEncounterReady()) {
                    steps.reset();
                    startBattleLoading();
                }*/
            }
        }

        if(screenLoading.isTransitionReady()) {
            screenLoading.setTransitionReady(false);
            TeleportDestination dest = screenLoading.getDest();
            beginNewMap(dest.getMapName().create(), dest.getGx(), dest.getGy(), dest.getFacing());
        }
    }

    private boolean playerInputIsAllowed() {
        return !screenLoading.isInProgress();
    }

    private boolean isStandingOnTeleport() {
        Optional<StepTeleport> step = stepTeleports.stream().filter(st -> st.getGx()==player.getGx() && st.getGy()==player.getGy()).findFirst();
        if(step.isPresent()) {
            screenLoading.beginNewTeleport(step.get().getDest());
            return true;
        }
        return false;
    }

    private boolean hasTriggeredBoundsTeleport() {
        if(getCurrentMap().getBoundsTeleport().isHasBounds()) {
            TeleportDestination dest = getCurrentMap().getBoundsTeleport().playerExceedsBounds(player);
            if(dest != null) {
                screenLoading.beginNewTeleport(dest);
                return true;
            }
        }
        return false;
    }

    private void beginNewMap(GameMap gameMap, int playerGx, int playerGy, Facing playerFacing) {
        currentMap = gameMap;

        camera.setMapBack(gameMap.getMapBack());
        clearWorld();

        if(player==null) {
            player = new ControlledPlayer();
        }
        player.setPosition(playerGx,playerGy);
        player.setFacing(playerFacing);
        //playerData.getEncounterSteps().reset();
        player.setSprite(gameMap.getMapName()==MapName.world);

        Minimap.getInstance().createMinimap(gameMap, player);
        Camera.getInstance().initialPosCenteredOnMapObject(player,currentMap.getPixelW(), currentMap.getPixelH());

        addToWorld(currentMap.getStepTiles());
    }

    public void addToWorld(MapActor... objectsToAdd) {
        objects.addAll(Arrays.asList(objectsToAdd));
    }

    public void addToWorld(GameList<StepTeleport> objectsToAdd) {
        stepTeleports.addAll(objectsToAdd);
    }

    private void clearWorld() {
        objects = new GameList<>();
        stepTeleports = new GameList<>();
    }
}
