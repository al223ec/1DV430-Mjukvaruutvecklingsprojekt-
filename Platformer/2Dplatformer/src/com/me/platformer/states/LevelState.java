package com.me.platformer.states;

import static com.me.platformer.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.me.platformer.PlatformerGame;
import com.me.platformer.gameObjects.Background;
import com.me.platformer.gameObjects.Player;
import com.me.platformer.handlers.GContactListener;
import com.me.platformer.handlers.GInputProcessor;
import com.me.platformer.handlers.GameStateManager;
import com.me.platformer.handlers.MapManager;

public abstract class LevelState extends GameState{
	protected boolean debug = true; 
	protected World world; 

	protected MapManager mapManager; 
	protected Box2DDebugRenderer b2dr; 
	protected OrthographicCamera b2dCam;
	
	protected Player player; 
	protected float hitBoxSize = 45; 
	protected Background gameBackground; 
	protected OrthogonalTiledMapRenderer tmr; 
	
	public LevelState(GameStateManager gsm, String mapPath) {
		super(gsm);
		
		Gdx.input.setInputProcessor(new GInputProcessor());
		world = new World(new Vector2(0, -9.81f), true);
		createPlayer(); 
		world.setContactListener(new GContactListener(player));
		
		b2dr = new Box2DDebugRenderer(); 
		if(debug){
			b2dCam = new OrthographicCamera(); 
			b2dCam.setToOrtho(false, PlatformerGame.WIDTH / PPM, PlatformerGame.HEIGHT / PPM); 		
		}		
		mapManager = new MapManager(world, mapPath); 
		tmr = new OrthogonalTiledMapRenderer(mapManager.getTiledMap()); 
	}
	
	public abstract void resetLevel();
	protected abstract void createPlayer();
}
