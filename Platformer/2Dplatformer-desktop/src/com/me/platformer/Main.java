package com.me.platformer;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = PlatformerGame.TITLE;
		cfg.useGL20 = false;
		
		cfg.width = PlatformerGame.V_WIDTH;
		cfg.height = PlatformerGame.V_HEIGHT;
		
		new LwjglApplication(new PlatformerGame(), cfg);
	/*
		cfg.width = OrthographicCameraController.WIDTH;
		cfg.height = OrthographicCameraController.HEIGHT;
		
		new LwjglApplication(new OrthographicCameraController(), cfg);
	*/
	}
}
