package com.me.platformer.states;

import static com.me.platformer.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.me.platformer.PlatformerGame;
import com.me.platformer.gameObjects.Player;
import com.me.platformer.handlers.B2DVars;
import com.me.platformer.handlers.GContactListener;
import com.me.platformer.handlers.GInput;
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
	
	private Player player; 
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(gcl = new GContactListener()); 
		b2dr = new Box2DDebugRenderer(); 
	
		createPlayer();
		createTiles();
		
		// Set up box2d Cam
		b2dCam = new OrthographicCamera(); 
		b2dCam.setToOrtho(false, PlatformerGame.V_WIDTH / PPM, PlatformerGame.V_HEIGHT / PPM); 
		
	}
	
	public void handleInput() {
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			if(gcl.isOnGround()){
				player.getBody().applyForceToCenter(0, 270, true);
			}
		}
	}
	
	public void update(float dt) {
		handleInput(); //Inputhanterare
		world.step(dt, 6, 2); //Tar världen ett steg framåt el dt steg framåt
		//6 och 2 är antalet punkter som kommer kontrolleras for collision
		//Rekommenderat 6 eller 8 och 2 eller 3
		
		player.update(dt); 
	}
	
	public void render() {
		//Rensa 
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		//rita mapen
		tmr.setView(cam);
		tmr.render(); 
		//rita spelaren
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
		
		//Rita box2d värld
		if(debug) //Endast om debug läget är aktuellt
		b2dr.render(world, b2dCam.combined); 
	}
	
	public void dispose() {}
	
	private void createPlayer() {
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fDef = new FixtureDef();
		
		//Skapa Player box
		bdef.position.set(100 / PPM, 200 / PPM); 
		bdef.type = BodyType.DynamicBody; 
		//bdef.linearVelocity.set(0.1f,0);//Rörelse i sidled
		Body body = world.createBody(bdef); 
		
		shape.setAsBox(13 /PPM, 13 /PPM); 
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_BALL; 
		//fDef.restitution = 0.15f; //Elastitet bouncing 
		body.createFixture(fDef).setUserData("player"); 
		
		//Fot sensor
		shape.setAsBox(13/PPM, 2/PPM, new Vector2(0, -13/PPM), 0);
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND; 
		fDef.isSensor = true; 
		body.createFixture(fDef).setUserData("foot");
		
		player = new Player(body); 
		body.setUserData(player); 
		
		if(debug) {
			createTestBall(152, 220); 
			createTestBall(141, 230);
			createTestBall(144, 200);
			createTestBall(142, 182);
		}
	}
	
	private void createTestBall(float x, float y){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		//Skapa boll
		bdef.position.set(x/PPM, y/ PPM);
		bdef.type = BodyType.DynamicBody; 
		Body body = world.createBody(bdef);
		
		CircleShape cShape = new CircleShape(); 
		cShape.setRadius(5/PPM);
		
		fdef.shape = cShape; 
		fdef.restitution = 0.15f;
		
		fdef.filter.categoryBits = B2DVars.BIT_BALL; 
		fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_BALL | B2DVars.BIT_PLAYER; 
		fdef.isSensor = false; 
		body.createFixture(fdef).setUserData("ball");;		
	}
	
	private void createTiles(){
		//Load map
		tileMap = new TmxMapLoader().load("res/maps/test.tmx"); 
		tmr = new OrthogonalTiledMapRenderer(tileMap); 
		//tileSize = (int) tileMap.getProperties().get("tilewidth"); //Varför fungerar inte detta? 
		
		//TiledMapTileLayer layer;
		
	}
	
	//Bits är vilken typ detta lagret ska vara vad det ska maska måt
	private void createLayer(TiledMapTileLayer layer, short bits){

		BodyDef bdef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		bdef.type = BodyType.StaticBody; 
		fDef.isSensor = false; 
		
		for(int row = 0; row < layer.getHeight(); row++){
			for(int col = 0; col < layer.getWidth(); col++){
				Cell cell = layer.getCell(col, row); 
				
				if(cell == null) continue; //IFall det inte finns något här skippa
				if(cell.getTile() == null) continue; 
				bdef.position.set
				(
					(col + 0.5f) * tileSize /PPM,
					(row + 0.5f) * tileSize /PPM
				);
				
				ChainShape cs = new ChainShape(); 
				Vector2[] v = new Vector2[4]; 
				v[0] = new Vector2( //topright
						-tileSize/2/PPM, -tileSize/2/PPM);
				v[1] = new Vector2( //topleft
						-tileSize/2/PPM, tileSize/2/PPM);
				v[2] = new Vector2( //bottomleft
					 	tileSize/2/PPM, tileSize/2/PPM);
				v[3] = new Vector2( //bottomright
						tileSize/2/PPM, -tileSize/2/PPM ); 
				cs.createLoop(v);					 					
				fDef.friction = 0;
				fDef.shape = cs; 
				fDef.filter.categoryBits = bits;
				fDef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALL; 
				world.createBody(bdef).createFixture(fDef);	
			}
		}
	}
}
