package com.me.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.platformer.handlers.Content;
import com.me.platformer.handlers.GInputProcessor;
import com.me.platformer.handlers.GameStateManager;

public class PlatformerGame implements ApplicationListener {
	public static final String TITLE = "The Game";
	
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;

	public static final float STEP = 1 / 60f;
	private float accum;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;
	
	public static Content resources; 
	
	public void create() {
		Gdx.input.setInputProcessor(new GInputProcessor());
		Texture.setEnforcePotImages(false); //Utan dett får jag felmeddelandet
		/*
		* Exception in thread "LWJGL Application" com.badlogic.gdx.utils.GdxRuntimeException: Texture width and height must be powers of two: 96x32
		* at com.badlogic.gdx.graphics.GLTexture.uploadImageData(GLTexture.java:241)
		* förstår inte varför alls, som det är nu har jag ju inga texturer äns FFS!! OMG
		*/
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		gsm = new GameStateManager(this);		
	}
	
	public void render() {
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}
	
	public void dispose() {
		
	}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
}
