package com.mygdx.pixelJump.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.pixelJump.PixelJump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PixelJump.WIDTH; 
		config.height = PixelJump.HEIGHT; 
		config.title = PixelJump.TITLE; 
		new LwjglApplication(new PixelJump(), config);
	}
}
