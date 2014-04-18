package com.me.platformer.states;

import static com.me.platformer.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GContactListener;
import com.me.platformer.handlers.GameStateManager;

public class Play extends GameState {
	
	private boolean debug = true; 
	private World world; 
	private Box2DDebugRenderer b2dr; 
	private OrthographicCamera b2dCam; 
	
	private GContactListener gcl; 
	private TiledMap tileMap;
	private float tileSize; 
	private OrthogonalTiledMapRenderer tmr; 
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		
		world = new World(new Vector2(0, -9.81f), true); //-9.81f är gravitationen
		gcl = new GContactListener(); //Instanceraca kollisionslystnare
		world.setContactListener(gcl); //Referera till den 
		b2dr = new Box2DDebugRenderer(); 
	
		createTiles();
		
		// Set up box2d Cam
		b2dCam = new OrthographicCamera(); 
		b2dCam.setToOrtho(false, PlatformerGame.V_WIDTH / PPM, PlatformerGame.V_HEIGHT / PPM); 
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void createTiles(){
		
	}
}