package com.mygdx.pixelJump.states.levels;

import static com.mygdx.pixelJump.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.gameObjects.Background;
import com.mygdx.pixelJump.gameObjects.Player;
import com.mygdx.pixelJump.handlers.GContactListener;
import com.mygdx.pixelJump.handlers.GInputProcessor;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.handlers.MapManager;
import com.mygdx.pixelJump.states.GameState;


public abstract class LevelState extends GameState{
	protected boolean debug = false; 
	protected World world; 

	protected MapManager mapManager; 
	protected Box2DDebugRenderer b2dr; 
	protected OrthographicCamera b2dCam;
	
	protected Player player; 
	protected float hitBoxSize = 45; 
	protected Background gameBackground; 
	protected OrthogonalTiledMapRenderer tmr;
	
	protected OrthographicCamera  hudCam;
	protected Texture hudTexture; 
	
	protected float elapsedTime; 
	
	protected Background background; 
	
	public LevelState(GameStateManager gsm, String mapPath) {
		super(gsm);
		
		Gdx.input.setInputProcessor(new GInputProcessor());
		world = new World(new Vector2(0, -9.81f), true);
		createPlayer(); 
		world.setContactListener(new GContactListener(player));
		
		b2dr = new Box2DDebugRenderer(); 
		if(debug){
			b2dCam = new OrthographicCamera(); 
			b2dCam.setToOrtho(false, PixelJump.WIDTH / PPM, PixelJump.HEIGHT / PPM); 		
		}		
		mapManager = new MapManager(world, mapPath); 
		tmr = new OrthogonalTiledMapRenderer(mapManager.getTiledMap()); 
		hudCam = gsm.getHudCamera(); 
		hudTexture = PixelJump.cont.getTexture("hud"); 
	}
	
	public abstract void resetLevel();
	protected abstract void createPlayer();
}
