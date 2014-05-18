package com.me.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.platformer.handlers.Content;
import com.me.platformer.handlers.GInputProcessor;
import com.me.platformer.handlers.GameStateManager;

public class PlatformerGame implements ApplicationListener {  //Kan använda Game då behövs inte GSM
	public static final String TITLE = "The Game";
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static final float STEP = 1 / 60f;
	
	private SpriteBatch sb;
	private OrthographicCamera cam; 
	
	private GameStateManager gsm;

	public static Content cont; 
	private GL10 gl; 
	
	public void create() {
		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new GInputProcessor());
		loadTextures(); 
		sb = new SpriteBatch();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);

		gsm = new GameStateManager(this);		

		//Kamera
		gl = Gdx.graphics.getGL10(); 
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		gl.glViewport(0, 0, WIDTH, HEIGHT); 
	}
	
	private void loadTextures(){
		cont = new Content();
		cont.loadTextures("res/images/player/run.png", "runSprites"); 
		cont.loadTextures("res/images/player/hats/hat.png", "hat"); 
		cont.loadTextures("res/images/splash.jpg", "splash");
		cont.loadTextures("res/images/bg.png", "bg");
	}
	
	public void render() {
		Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(Gdx.graphics.getDeltaTime());
	}
	
	public void dispose() {
		
	}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
}
