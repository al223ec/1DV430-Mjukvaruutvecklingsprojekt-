package com.me.platformer.states;

import static com.me.platformer.handlers.B2DVars.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
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
	
	public Test(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(gcl = new GContactListener()); 
		b2dr = new Box2DDebugRenderer(); 
		
		createPlatform(160,120); 
		createPlatform(160,700); 
		createBox(); 
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
				if(GInput.x < Gdx.graphics.getWidth() / 2){
					player.getBody().applyForceToCenter(0, 270, true);
				}else{
					player.getBody().applyForceToCenter(0, 100, true);
				}
			}
		
		}
		
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
	
	public void update(float dt) {		
		handleInput(); 
		world.step(dt, 6, 2); 
	}
	
	public void render() {
		//Rensa 
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT); 
	
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
	private void createBox(){ //PLAYER
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
}
