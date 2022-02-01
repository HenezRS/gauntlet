package com.henez.gauntlet.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.henez.gauntlet.GauntletGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(GauntletGame.GAME_IDENTIFIER);
		config.vSyncEnabled = false;
		config.title = "Gauntlet";
		config.width = 1440;
		config.height = 810;
		config.resizable = true;
		config.foregroundFPS = 60;
		new DesktopMini2DxGame(new GauntletGame(), config);
	}
}
