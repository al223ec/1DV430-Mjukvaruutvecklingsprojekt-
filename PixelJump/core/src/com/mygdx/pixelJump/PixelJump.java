package com.mygdx.pixelJump;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pixelJump.handlers.Content;
import com.mygdx.pixelJump.handlers.GInputProcessor;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.handlers.PlayerSettings;


public class PixelJump implements ApplicationListener {
public static final String TITLE = "Pixel Jump";
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static final float STEP = 1 / 60f;
	
	private SpriteBatch sb;
	private OrthographicCamera cam; 
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;

	public static Content cont; 
	public static PlayerSettings playerSettings;
	public void create() {
		Gdx.input.setInputProcessor(new GInputProcessor());
		loadTextures(); 
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.setToOrtho(false, WIDTH, HEIGHT);
		
		hudCam = new OrthographicCamera(WIDTH, HEIGHT);
		hudCam.setToOrtho(false, WIDTH, HEIGHT);
		
		gsm = new GameStateManager(this);		
		playerSettings = new PlayerSettings(); 
	}
	
	private void loadTextures(){
		cont = new Content();
		cont.loadTextures("res/images/player/run.png", "runSprites"); 
		cont.loadTextures("res/images/splash.png", "splash");
		cont.loadTextures("res/images/editSplash.png", "editSplash");
		cont.loadTextures("res/images/background.png", "background");
		cont.loadTextures("res/images/hud.png", "hud");
		
		cont.loadTextures("res/images/player/hats/dreddGame.png", "dredd"); 
		cont.loadTextures("res/images/player/hats/roboGame.png", "robo"); 
		cont.loadTextures("res/images/player/hats/spiderGame.png", "spider"); 
		
		cont.loadTextures("res/maps/gameBackground.png", "gameBackground"); 
		cont.loadTextures("res/maps/gameBackgroundFront.png", "gameBackgroundFront"); 
		cont.loadTextureAtlas("res/buttons/buttons.pack", "buttons"); 
		cont.loadTextureAtlas("res/images/player/hats/hats.pack", "hats"); 
		
		cont.loadMaps("res/maps/level1.tmx", "level1"); 
		cont.loadMaps("res/maps/level2.tmx", "level2"); 
		
	}
	
	public void render() {
		Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}
	
	public void dispose() {
		System.out.println("Game disposed"); 
		cont.disposeAll(); 
	}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHudCamera() { return hudCam; }
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
}

