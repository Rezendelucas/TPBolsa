package com.mygdx.game.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


import com.mygdx.game.GameMain;

public class DesktopLauncher {
	private static  boolean drawDebugOutline = false;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GameMain";
		config.useGL30 = false;
		config.width  = 1280;
		config.height = 720;
		new LwjglApplication(new GameMain(), config);
	}
}
