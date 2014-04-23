package com.me.platformer.states;

import static com.me.platformer.handlers.B2DVars.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.platformer.PlatformerGame;
import com.me.platformer.gameObjects.Player;
import com.me.platformer.handlers.B2DVars;
import com.me.platformer.handlers.GContactListener;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class Test extends GameState {
	
	private BitmapFont font = new BitmapFont(); 
	private World world; 

	private Box2DDebugRenderer b2dr; 
	private OrthographicCamera b2dCam;
	private GContactListener gcl;
	
	private Player player; 

	private TiledMap tileMap;
	private float tileSize; 
	
	private OrthogonalTiledMapRenderer tmr; 
	
	public Test(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(gcl = new GContactListener()); 
		b2dr = new Box2DDebugRenderer(); 
		
		//createPlatform(160,120); 
		//createPlatform(160,700); 
		createTiles(); 
		
		createPlayer(); 
		createBall(); 

		b2dCam = new OrthographicCamera(); 
		b2dCam.setToOrtho(false, PlatformerGame.V_WIDTH / PPM, PlatformerGame.V_HEIGHT / PPM); 
		
	}

	public void handleInput() {
		// keyboard input
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			if(gcl.isOnGround()){
				player.getBody().applyForceToCenter(0, 100, true);
			}
		}
		//Touch inputs
		if(GInput.isPressed()){
			if(gcl.isOnGround()){
				if(GInput.x < Gdx.graphics.getWidth() / 2){//Om hen toucher vänstra delen hoppa högt 
					player.getBody().applyForceToCenter(0, 270, true);
				}else{
					player.getBody().applyForceToCenter(0, 100, true);//Annars hoppa lågt
				}
			}
		}
	}
	
	public void update(float dt) {
		handleInput(); 
		world.step(dt, 6, 2); 		
	}
	
	public void render() {
		//Rensa 
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT); 
	
		tmr.setView(cam); 
		tmr.render(); 
		//Rita
		b2dr.render(world, b2dCam.combined); 
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		font.draw(sb, "Teststate", 10, 500);
		sb.end();
	}
	
	public void dispose() {}
	
	private void createBall(){
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody; 

		Body body = world.createBody(bdef);

		bdef.position.set(152/PPM, 420/ PPM);
		body = world.createBody(bdef);
		
		CircleShape cShape = new CircleShape(); 
		cShape.setRadius(45/PPM);
		FixtureDef fDef = new FixtureDef();
		fDef.restitution = 0.5f; //Elastitet bouncing 
		fDef.shape = cShape; 

		fDef.filter.categoryBits = B2DVars.BIT_BALL; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND; 
		body.createFixture(fDef).setUserData("ball");
	
	}
	
	private void createPlayer(){
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM, 400 / PPM); 
		bdef.type = BodyType.DynamicBody; 
		
		Body body = world.createBody(bdef);
		PolygonShape shape = new PolygonShape();
		
		shape.setAsBox(45 /PPM, 45 /PPM); 
		FixtureDef fDef = new FixtureDef();
		
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND; 
		
		body.createFixture(fDef).setUserData("player"); 
		
		//Fot sensor
		shape.setAsBox(45/PPM, 4/PPM, new Vector2(0, -45/PPM), 0);
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND; 
		fDef.isSensor = true; 
		body.createFixture(fDef).setUserData("foot");
		
		player = new Player(body); 
	}

	private void createPlatform(float posX, float posY){	
		BodyDef bdef = new BodyDef();
		bdef.position.set(posX / PPM, posY /PPM);  
		bdef.type = BodyType.StaticBody;
		
		Body body = world.createBody(bdef);
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(500 / PPM, 50/ PPM); 
		FixtureDef fDef = new FixtureDef();
		fDef.shape = boxShape; 
		fDef.filter.categoryBits = B2DVars.BIT_GROUND; 
		fDef.filter.maskBits = B2DVars.BIT_BALL | B2DVars.BIT_PLAYER; 
		body.createFixture(fDef).setUserData("ground"); 
	}
	
	private void playerJump(){ }
	private void worldFlip(){ 
		/*
		if(GInput.isDown(GInput.BUTTONFLIP)){
			player.getBody().applyForceToCenter(0, 270, true);
			System.out.println("Flip is pressed");
			world.setGravity(new Vector2(0, 9.81f));
			System.out.println("Flip to inverted");
			player.getBody().setTransform(player.getBody().getPosition(), 3.1415f); 
			worldIsFlipped = true; 
			/*
			 * Skapa world flip 
			 * function som ser 
			 * till att spelaren är 
			 * de fakto på marken innan man kan flippa igen
			if(worldIsFlipped){
				world.setGravity(new Vector2(0, -9.81f));
				System.out.println("Flip to normal");
				player.getBody().setTransform(player.getBody().getPosition(), 3.1415f);
				worldIsFlipped = false; 
			} else{
				world.setGravity(new Vector2(0, 9.81f));
				System.out.println("Flip to inverted");
				player.getBody().setTransform(player.getBody().getPosition(), 3.1415f); 
				worldIsFlipped = true; 
			}*/
	//	}
	}
	
	private void createTiles(){
		//Load map
		tileMap = new TmxMapLoader().load("res/maps/test.tmx"); 
		tmr = new OrthogonalTiledMapRenderer(tileMap); 		
		tileSize = (int) tileMap.getProperties().get("tilewidth");
		
		if(tileMap == null){
			System.out.println("tilemap Null why??????? ");
		}
		TiledMapTileLayer layer;
		
		layer = (TiledMapTileLayer) tileMap.getLayers().get("ground");

		if(layer == null){
			System.out.println("layer is?? Null why??????? ");
		}else{
		createLayer(layer, B2DVars.BIT_GROUND);		
		}
	}
	
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
			
				//skapae en body def och en box
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
