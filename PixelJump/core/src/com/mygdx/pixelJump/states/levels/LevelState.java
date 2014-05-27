package com.mygdx.pixelJump.states.levels;

import static com.mygdx.pixelJump.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
	protected boolean debug = true; 
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
	protected GContactListener contactListener;

	protected BitmapFont timeFont;
	public LevelState(GameStateManager gsm, String mapPath) {
		super(gsm);
		
		Gdx.input.setInputProcessor(new GInputProcessor());
		
		world = new World(new Vector2(0, -9.81f), true);
		
		createPlayer(); 
		contactListener = new GContactListener(player); 
		world.setContactListener(contactListener);
		
		b2dr = new Box2DDebugRenderer(); 
		if(debug){
			b2dCam = new OrthographicCamera(); 
			b2dCam.setToOrtho(false, PixelJump.WIDTH / PPM, PixelJump.HEIGHT / PPM); 		
		}		
		mapManager = new MapManager(world, mapPath); 
		tmr = new OrthogonalTiledMapRenderer(mapManager.getTiledMap()); 
		hudCam = gsm.getHudCamera(); 
		hudTexture = PixelJump.cont.getTexture("hud");
		timeFont = PixelJump.cont.getFont();
	}
	
	public void render(){
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		if(!player.isPlayerDead()){
			cam.position.lerp(new Vector3(player.getX() * PPM + PixelJump.WIDTH/4, player.getY() * PPM + (PixelJump.HEIGHT)/6, 0f), 0.08f);
			cam.update();
		}
		//Rita bakgrunden 
		sb.setProjectionMatrix(hudCam.combined);
		background.draw(sb);
		
		tmr.setView(cam);
		tmr.render();
		
		//rita spelare
		sb.setProjectionMatrix(cam.combined);//Sätter vad som ska renderas 
		player.render(sb);

		//Rita timer
		sb.setProjectionMatrix(hudCam.combined);
		sb.begin();
			sb.draw(hudTexture, 0, 0);
			timeFont.draw(sb, Float.toString((float) (Math.round(elapsedTime*1000.0)/1000.0)), PixelJump.WIDTH - 300, PixelJump.HEIGHT - 20); 
		sb.end();
		
		//Rita box2d
		if(debug){
			if(!player.isPlayerDead()){
				b2dCam.position.lerp(new Vector3(player.getX() + PixelJump.WIDTH/4/PPM, player.getY() + (PixelJump.HEIGHT)/6/PPM, 0f), 0.08f);	
			}
			b2dCam.update();
			b2dr.render(world, b2dCam.combined); 
		}
	}
	public abstract void resetLevel();
	protected abstract void createPlayer();
	public abstract void updateWorldAndPlayer(float dt); 
}
